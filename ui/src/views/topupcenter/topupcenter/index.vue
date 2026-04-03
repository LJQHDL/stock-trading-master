<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="用户ID" prop="userId">
              <el-input v-model="queryParams.userId" placeholder="请输入用户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="账户余额" prop="amount">
              <el-input v-model="queryParams.amount" placeholder="请输入账户余额" clearable @keyup.enter="handleQuery" />
            </el-form-item>

            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
              <el-button type="info" icon="List" @click="handleQueryAll">查询全部</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>

    <!-- 充值提现操作区域 -->
    <div class="operation-area">
      <el-row :gutter="20">
        <!-- 充值操作 -->
        <el-col :span="12">
          <el-card shadow="hover" class="operation-card">
            <template #header>
              <div class="card-header">
                <span>账户充值</span>
              </div>
            </template>

            <el-form ref="rechargeFormRef" :model="rechargeForm" :rules="rechargeRules" label-width="80px">
              <el-form-item label="用户ID" prop="userId">
                <el-input v-model="rechargeForm.userId" placeholder="请输入用户ID" />
              </el-form-item>
              <el-form-item label="充值金额" prop="amount">
                <el-input-number
                  v-model="rechargeForm.amount"
                  :min="1"
                  :max="100000"
                  :precision="2"
                  placeholder="请输入充值金额"
                  style="width: 100%"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="handleRecharge" :loading="rechargeLoading"> 确认充值 </el-button>
                <el-button @click="resetRechargeForm">重置</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>

        <!-- 提现操作 -->
        <el-col :span="12">
          <el-card shadow="hover" class="operation-card">
            <template #header>
              <div class="card-header">
                <span>账户提现</span>
              </div>
            </template>

            <el-form ref="withdrawFormRef" :model="withdrawForm" :rules="withdrawRules" label-width="80px">
              <el-form-item label="用户ID" prop="userId">
                <el-input v-model="withdrawForm.userId" placeholder="请输入用户ID" />
              </el-form-item>
              <el-form-item label="提现金额" prop="amount">
                <el-input-number
                  v-model="withdrawForm.amount"
                  :min="1"
                  :max="100000"
                  :precision="2"
                  placeholder="请输入提现金额"
                  style="width: 100%"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="warning" @click="handleWithdraw" :loading="withdrawLoading"> 确认提现 </el-button>
                <el-button @click="resetWithdrawForm">重置</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 操作按钮区域 -->
    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['topupcenter:topupcenter:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" @click="handleUpdate()" v-hasPermi="['topupcenter:topupcenter:edit']"> 修改 </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" @click="handleDelete()" v-hasPermi="['topupcenter:topupcenter:remove']"> 删除 </el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>
    </el-card>

    <!-- 添加或修改用户余额对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="账户余额" prop="amount">
          <el-input-number v-model="form.amount" :min="0" :precision="2" placeholder="请输入账户余额" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 查询结果弹窗 -->
    <el-dialog v-model="queryDialog.visible" :title="queryDialog.title" width="60%" append-to-body>
      <el-table :data="queryResultList" style="width: 100%" border>
        <el-table-column label="用户ID" align="center" prop="userId" />
        <el-table-column label="账户余额" align="center" prop="amount">
          <template #default="scope">
            <span class="amount-text">{{ scope.row.amount }}</span>
          </template>
        </el-table-column>
        <el-table-column label="当前查询时间" align="center">
          <template #default="scope">
            <span>{{ new Date().toLocaleString() }}</span>
          </template>
        </el-table-column>
      </el-table>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="queryDialog.visible = false">关 闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="TopupCenter" lang="ts">
import {
  listUserBalance,
  getUserBalance,
  delUserBalance,
  addUserBalance,
  updateUserBalance,
  recharge,
  withdraw
} from '@/api/topupcenter/topupcenter';
import { UserBalanceVo, UserBalanceBo, UserBalanceQuery, RechargeRequest, WithdrawRequest } from '@/api/topupcenter/topupcenter/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const userBalanceList = ref<UserBalanceVo[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const total = ref(0);
const rechargeLoading = ref(false);
const withdrawLoading = ref(false);

const queryFormRef = ref<ElFormInstance>();
const formRef = ref<ElFormInstance>();
const rechargeFormRef = ref<ElFormInstance>();
const withdrawFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

// 查询结果弹窗
const queryDialog = reactive<DialogOption>({
  visible: false,
  title: '查询结果'
});

// 查询结果数据
const queryResultList = ref<UserBalanceVo[]>([]);

// 充值表单
const rechargeForm = ref<RechargeRequest>({
  userId: '',
  amount: 0
});

// 提现表单
const withdrawForm = ref<WithdrawRequest>({
  userId: '',
  amount: 0
});

// 充值表单验证规则
const rechargeRules = {
  userId: [{ required: true, message: '用户ID不能为空', trigger: 'blur' }],
  amount: [{ required: true, message: '充值金额不能为空', trigger: 'blur' }]
};

// 提现表单验证规则
const withdrawRules = {
  userId: [{ required: true, message: '用户ID不能为空', trigger: 'blur' }],
  amount: [{ required: true, message: '提现金额不能为空', trigger: 'blur' }]
};

const initFormData: UserBalanceBo = {
  userId: undefined,
  amount: undefined,
  isDelete: 0
};

const data = reactive<PageData<UserBalanceBo, UserBalanceQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: undefined,
    amount: undefined,
    isDelete: undefined,
    beginTime: undefined,
    endTime: undefined,
    params: {}
  },
  rules: {
    userId: [{ required: true, message: '用户ID不能为空', trigger: 'blur' }],
    amount: [{ required: true, message: '账户余额不能为空', trigger: 'blur' }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询用户余额列表 */
const getList = async () => {
  loading.value = true;
  const res = await listUserBalance(queryParams.value);
  userBalanceList.value = res.rows;
  total.value = res.total;
  loading.value = false;
};

/** 处理充值 */
const handleRecharge = () => {
  rechargeFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      rechargeLoading.value = true;
      try {
        await recharge(rechargeForm.value);
        proxy?.$modal.msgSuccess('充值成功');
        resetRechargeForm();
        await getList();
      } catch (error) {
        console.error('充值失败:', error);
      } finally {
        rechargeLoading.value = false;
      }
    }
  });
};

/** 处理提现 */
const handleWithdraw = () => {
  withdrawFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      withdrawLoading.value = true;
      try {
        await withdraw(withdrawForm.value);
        proxy?.$modal.msgSuccess('提现成功');
        resetWithdrawForm();
        await getList();
      } catch (error) {
        console.error('提现失败:', error);
      } finally {
        withdrawLoading.value = false;
      }
    }
  });
};

/** 重置充值表单 */
const resetRechargeForm = () => {
  rechargeForm.value = {
    userId: '',
    amount: 0
  };
  rechargeFormRef.value?.resetFields();
};

/** 重置提现表单 */
const resetWithdrawForm = () => {
  withdrawForm.value = {
    userId: '',
    amount: 0
  };
  withdrawFormRef.value?.resetFields();
};

/** 取消按钮 */
const cancel = () => {
  reset();
  dialog.visible = false;
};

/** 表单重置 */
const reset = () => {
  form.value = { ...initFormData };
  formRef.value?.resetFields();
};

/** 搜索按钮操作 */
const handleQuery = async () => {
  try {
    loading.value = true;
    const res = await listUserBalance(queryParams.value);
    queryResultList.value = res.rows;
    queryDialog.visible = true;
    proxy?.$modal.msgSuccess(`查询完成，共找到 ${res.rows.length} 条记录`);
  } catch (error) {
    console.error('查询失败:', error);
    proxy?.$modal.msgError('查询失败，请重试');
  } finally {
    loading.value = false;
  }
};

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields();
  handleQuery();
};

/** 查询全部按钮操作 */
const handleQueryAll = async () => {
  try {
    loading.value = true;
    // 清空查询条件，查询全部数据
    const allQueryParams = {
      pageNum: 1,
      pageSize: 1000, // 设置较大的页面大小以获取所有数据
      userId: undefined,
      amount: undefined,
      isDelete: undefined,
      beginTime: undefined,
      endTime: undefined,
      params: {}
    };
    const res = await listUserBalance(allQueryParams);
    queryResultList.value = res.rows;
    queryDialog.visible = true;
    proxy?.$modal.msgSuccess(`查询全部完成，共找到 ${res.rows.length} 条记录`);
  } catch (error) {
    console.error('查询全部失败:', error);
    proxy?.$modal.msgError('查询全部失败，请重试');
  } finally {
    loading.value = false;
  }
};

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = '添加用户余额';
};

/** 修改按钮操作 */
const handleUpdate = async (row?: UserBalanceVo) => {
  let userId = row?.userId;
  if (!userId) {
    userId = await proxy?.$modal
      .prompt('请输入要修改的用户ID', '修改用户余额', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^.+$/,
        inputErrorMessage: '用户ID不能为空'
      })
      .then(({ value }) => value)
      .catch(() => null);

    if (!userId) return;
  }

  try {
    reset();
    const res = await getUserBalance(userId as string);
    Object.assign(form.value, res.data);
    dialog.visible = true;
    dialog.title = '修改用户余额';
  } catch (error) {
    proxy?.$modal.msgError('获取用户信息失败，请检查用户ID是否正确');
  }
};

/** 提交按钮 */
const submitForm = () => {
  formRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      // 新增时确保删除标记默认为正常状态
      if (!form.value.userId) {
        form.value.isDelete = 0;
      }
      if (form.value.userId) {
        await updateUserBalance(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addUserBalance(form.value).finally(() => (buttonLoading.value = false));
      }
      proxy?.$modal.msgSuccess('操作成功');
      dialog.visible = false;
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: UserBalanceVo) => {
  let userId = row?.userId;
  if (!userId) {
    userId = await proxy?.$modal
      .prompt('请输入要删除的用户ID', '删除用户余额', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^.+$/,
        inputErrorMessage: '用户ID不能为空'
      })
      .then(({ value }) => value)
      .catch(() => null);

    if (!userId) return;
  }

  try {
    await proxy?.$modal.confirm('是否确认删除用户余额编号为"' + userId + '"的数据项？');
    await delUserBalance(userId as string);
    proxy?.$modal.msgSuccess('删除成功');
    await getList();
  } catch (error) {
    if (error !== 'cancel') {
      proxy?.$modal.msgError('删除失败，请重试');
    }
  }
};

onMounted(() => {
  getList();
});
</script>

<style scoped>
.operation-area {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 20px;
  margin-bottom: 16px;
  padding: 16px;
  border-radius: 8px;
}

.operation-card {
  border-radius: 8px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.amount-text {
  font-weight: bold;
  color: var(--el-color-success);
}

:deep(.el-input-number) {
  width: 200px;
}
</style>
