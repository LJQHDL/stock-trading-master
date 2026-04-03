<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="股票名字" prop="name">
              <el-input v-model="queryParams.cname" placeholder="请输入股票名字" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="代码" prop="code">
              <el-input v-model="queryParams.symbol" placeholder="请输入代码" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>
    <div class="SymbolandDate">
      <!-- 下拉框 -->
      <div class="flex flex-wrap items-center">
        <el-dropdown @command="handleSymbolSelect">
          <el-button type="primary">
            {{ selectedSymbol || 'Select Symbol' }}<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu class="symbol-dropdown-menu">
              <el-dropdown-item v-for="item in symbolList" :key="item.symbol" :command="item.symbol">
                {{ item.symbol }}
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-button v-if="selectedSymbol" @click="clearSymbolFilter" type="info" plain style="margin-left: 8px"> 清除筛选 </el-button>
      </div>
      <!-- 日期筛选 -->
      <div class="block">
        <!-- <span class="demonstration">日期范围筛选</span> -->
        <div style="display: flex; align-items: center; gap: 10px">
          <el-date-picker
            v-model="dateRange"
            type="datetimerange"
            :shortcuts="shortcuts"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD HH:mm:ss"
            @change="handleDateChange"
          />
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </div>
      </div>
    </div>

    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['market:market:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['market:market:edit']"
              >修改</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['market:market:remove']"
              >删除</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['market:market:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" :data="stockList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="主键ID" align="center" prop="id" /> -->
        <el-table-column label="公司名称" align="center" prop="cname" />
        <el-table-column label="行业板块" align="center" prop="category" />
        <el-table-column label="股票代码" align="center" prop="symbol" />
        <el-table-column label="最新价" align="center" prop="price" />
        <el-table-column label="涨跌额" align="center" prop="diff" />
        <el-table-column label="涨跌幅(%)" align="center" prop="chg" />
        <el-table-column label="昨收价" align="center" prop="preclose" />
        <el-table-column label="今开价" align="center" prop="open" />
        <el-table-column label="最高价" align="center" prop="high" />
        <el-table-column label="最低价" align="center" prop="low" />
        <el-table-column label="振幅(%)" align="center" prop="amplitude" />
        <el-table-column label="成交量" align="center" prop="volume" />
        <el-table-column label="市值" align="center" prop="mktcap" />
        <el-table-column label="上市地" align="center" prop="market" />
        <el-table-column label="更新时间" align="center" prop="updateTime" />
        <el-table-column label="创建部门" align="center" prop="createDept" />
        <el-table-column label="创建者" align="center" prop="createBy" />
        <el-table-column label="创建时间" align="center" prop="createTime" />
        <el-table-column label="更新者" align="center" prop="updateBy" />
        <el-table-column label="当前时间" align="center" prop="currentLdate" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['market:market:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['market:market:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改股票信息对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="stockFormRef" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="主键ID" prop="id">
          <el-input v-model="form.id" placeholder="请输入主键ID" />
        </el-form-item> -->
        <el-form-item label="公司名称" prop="cname">
          <el-input v-model="form.cname" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="行业板块" prop="category">
          <el-input v-model="form.category" placeholder="请输入行业板块" />
        </el-form-item>
        <el-form-item label="股票代码" prop="symbol">
          <el-input v-model="form.symbol" placeholder="请输入股票代码" />
        </el-form-item>
        <el-form-item label="最新价" prop="price">
          <el-input v-model="form.price" placeholder="请输入最新价" />
        </el-form-item>
        <el-form-item label="涨跌额" prop="diff">
          <el-input v-model="form.diff" placeholder="请输入涨跌额" />
        </el-form-item>
        <el-form-item label="涨跌幅(%)" prop="chg">
          <el-input v-model="form.chg" placeholder="请输入涨跌幅(%)" />
        </el-form-item>
        <el-form-item label="昨收价" prop="preclose">
          <el-input v-model="form.preclose" placeholder="请输入昨收价" />
        </el-form-item>
        <el-form-item label="今开价" prop="open">
          <el-input v-model="form.open" placeholder="请输入今开价" />
        </el-form-item>
        <el-form-item label="最高价" prop="high">
          <el-input v-model="form.high" placeholder="请输入最高价" />
        </el-form-item>
        <el-form-item label="最低价" prop="low">
          <el-input v-model="form.low" placeholder="请输入最低价" />
        </el-form-item>
        <el-form-item label="振幅(%)" prop="amplitude">
          <el-input v-model="form.amplitude" placeholder="请输入振幅(%)" />
        </el-form-item>
        <el-form-item label="成交量" prop="volume">
          <el-input v-model="form.volume" placeholder="请输入成交量" />
        </el-form-item>
        <el-form-item label="市值" prop="mktcap">
          <el-input v-model="form.mktcap" placeholder="请输入市值" />
        </el-form-item>
        <el-form-item label="上市地" prop="market">
          <el-input v-model="form.market" placeholder="请输入上市地" />
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTime">
          <el-input v-model="form.updateTime" placeholder="请输入更新时间" />
        </el-form-item>
        <el-form-item label="创建部门" prop="createDept">
          <el-input v-model="form.createDept" placeholder="请输入创建部门" />
        </el-form-item>
        <el-form-item label="创建者" prop="createBy">
          <el-input v-model="form.createBy" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="form.createTime" placeholder="请输入创建时间" />
        </el-form-item>
        <el-form-item label="更新者" prop="updateBy">
          <el-input v-model="form.updateBy" placeholder="请输入更新者" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Market" lang="ts">
import { listStock, getStock, delStock, addStock, updateStock, getStockSymbols } from '@/api/market/market';
import { StockUsaVo, StockUsaForm, StockUsaQuery } from '@/api/market/market/types';
import { ArrowDown } from '@element-plus/icons-vue';
import { ref } from 'vue';

const value1 = ref<[Date, Date]>([new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)]);
const dateRange = ref<[string, string]>(['', '']);

const shortcuts = [
  {
    text: 'Last week',
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setDate(start.getDate() - 7);
      return [start, end];
    }
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setMonth(start.getMonth() - 1);
      return [start, end];
    }
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setMonth(start.getMonth() - 3);
      return [start, end];
    }
  }
];

const handleClick = () => {
  alert('button click');
};

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const stockList = ref<StockUsaVo[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const symbolList = ref<any[]>([]);
const selectedSymbol = ref('');

const queryFormRef = ref<ElFormInstance>();
const stockFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: StockUsaForm = {
  id: undefined,
  cname: undefined,
  category: undefined,
  symbol: undefined,
  price: undefined,
  diff: undefined,
  chg: undefined,
  preclose: undefined,
  open: undefined,
  high: undefined,
  low: undefined,
  amplitude: undefined,
  volume: undefined,
  mktcap: undefined,
  market: undefined,
  updateTime: undefined,
  createDept: undefined,
  createBy: undefined,
  createTime: undefined,
  updateBy: undefined,
  isDelete: 0,
  currentLdate: undefined
};
const data = reactive<PageData<StockUsaForm, StockUsaQuery>>({
  form: { ...initFormData },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    cname: undefined,
    id: undefined,
    category: undefined,
    symbol: undefined,
    price: undefined,
    diff: undefined,
    chg: undefined,
    preclose: undefined,
    open: undefined,
    high: undefined,
    low: undefined,
    amplitude: undefined,
    volume: undefined,
    mktcap: undefined,
    market: undefined,
    params: {},
    isDelete: 0,
    currentLdate: undefined
  },
  rules: {
    cname: [{ required: true, message: '名字不能为空', trigger: 'blur' }],
    symbol: [{ required: true, message: '股票代码不能为空', trigger: 'blur' }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询股票信息列表 */
const getList = async () => {
  loading.value = true;
  // 添加日期范围参数
  const params = proxy?.addDateRange(queryParams.value, dateRange.value);
  const res = await listStock(params);
  stockList.value = res.rows;
  total.value = res.total;
  loading.value = false;
};

/** 获取股票Symbol列表 */
const getSymbolList = async () => {
  try {
    const res = await getStockSymbols();
    console.log('API响应:', res);
    symbolList.value = res.result || [];
  } catch (error) {
    console.error('获取股票Symbol失败:', error);
  }
};

/** 处理Symbol选择 */
const handleSymbolSelect = (symbol: string) => {
  selectedSymbol.value = symbol;
  queryParams.value.symbol = symbol;
  handleQuery();
};

/** 清除Symbol筛选 */
const clearSymbolFilter = () => {
  selectedSymbol.value = '';
  queryParams.value.symbol = undefined;
  handleQuery();
};

/** 处理日期变化 */
const handleDateChange = (value: [string, string] | null) => {
  dateRange.value = value || ['', ''];
  handleQuery();
};

/** 取消按钮 */
const cancel = () => {
  reset();
  dialog.visible = false;
};

/** 表单重置 */
const reset = () => {
  form.value = { ...initFormData };
  stockFormRef.value?.resetFields();
};

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
};

/** 重置按钮操作 */
const resetQuery = () => {
  dateRange.value = ['', ''];
  queryFormRef.value?.resetFields();
  handleQuery();
};

/** 多选框选中数据 */
const handleSelectionChange = (selection: StockUsaVo[]) => {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
};

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = '添加股票信息';
};

/** 修改按钮操作 */
const handleUpdate = async (row?: StockUsaVo) => {
  reset();
  const _id = row?.id || ids.value[0];
  const res = await getStock(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = '修改股票信息';
};
/** 提交按钮 */
const submitForm = () => {
  stockFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateStock(form.value).finally(() => (buttonLoading.value = false));
      } else {
        await addStock(form.value).finally(() => (buttonLoading.value = false));
      }
      proxy?.$modal.msgSuccess('操作成功');
      dialog.visible = false;
      await getList();
    }
  });
};

/** 删除按钮操作 */
const handleDelete = async (row?: StockUsaVo) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除股票信息编号为"' + _ids + '"的数据项？').finally(() => (loading.value = false));
  await delStock(_ids);
  proxy?.$modal.msgSuccess('删除成功');
  await getList();
};

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download(
    'market/export',
    {
      ...queryParams.value
    },
    `stock_${new Date().getTime()}.xlsx`
  );
};

onMounted(() => {
  getList();
  getSymbolList();
});
</script>
<style scoped>
.example-showcase .el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}

/* 限制下拉菜单高度 */
:deep(.symbol-dropdown-menu .el-dropdown-menu__item) {
  max-height: 200px;
  overflow-y: auto;
}

:deep(.el-dropdown-menu) {
  max-height: 200px;
  overflow-y: auto;
}

.symbol-dropdown-menu {
  max-height: 200px !important;
  overflow-y: auto !important;
}

/* 以下是日期 */
.block {
  text-align: left;
}
.block .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 5px;
}
.SymbolandDate {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 20px;
  margin-bottom: 16px;
  padding: 16px;
  border-radius: 8px;
}

.SymbolandDate > div {
  display: flex;
  align-items: center;
  gap: 8px;
}
</style>
