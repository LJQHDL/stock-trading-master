<template>
  <div class="app-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>AI股票分析</span>
        </div>
      </template>

      <!-- 股票选择器 -->
      <div class="stock-selector">
        <el-form :inline="true" class="demo-form-inline">
          <el-form-item label="选择股票代码:">
            <el-select
              v-model="selectedSymbol"
              placeholder="请选择股票代码"
              filterable
              @change="onSymbolChange"
              style="width: 200px"
            >
              <el-option
                v-for="symbol in symbolList"
                :key="symbol"
                :label="symbol"
                :value="symbol"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              type="primary"
              @click="analyzeStock"
              :loading="loading"
              :disabled="!selectedSymbol"
            >
              开始分析
            </el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- AI分析结果展示区域 -->
      <div class="analysis-result" v-if="analysisResult">
        <el-divider content-position="left">
          <span class="result-title">
            <el-icon><TrendCharts /></el-icon>
            AI分析结果
          </span>
        </el-divider>
        <el-card shadow="hover" class="result-card">
          <div class="result-header">
            <el-tag type="success" size="large">{{ selectedSymbol }}</el-tag>
            <span class="analysis-time">分析时间: {{ new Date().toLocaleString() }}</span>
          </div>
          <div class="result-content">
            <div class="content-wrapper" v-html="formattedResult"></div>
          </div>
        </el-card>
      </div>

      <!-- 空状态 -->
      <div class="empty-state" v-if="!analysisResult && !loading">
        <el-empty description="请选择股票代码并点击分析按钮获取AI分析结果" />
      </div>

      <!-- 加载状态 -->
      <div class="loading-state" v-if="loading">
        <el-card shadow="never">
          <div style="text-align: center; padding: 40px;">
            <el-icon class="is-loading" size="32"><Loading /></el-icon>
            <p style="margin-top: 16px; color: #666;">AI正在分析股票数据，请稍候...</p>
          </div>
        </el-card>
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { Loading, TrendCharts } from '@element-plus/icons-vue';
import { aiChatModel } from '@/api/aichat/aichat';
import { getStockSymbols } from '@/api/market/market';
import { marked } from 'marked';

// 响应式数据
const selectedSymbol = ref<string>('');
const symbolList = ref<string[]>([]);
const analysisResult = ref<string>('');
const loading = ref<boolean>(false);

// 计算属性：格式化分析结果（Markdown渲染）
const formattedResult = computed(() => {
  if (!analysisResult.value) return '';
  
  // 配置marked选项
  marked.setOptions({
    breaks: true, // 支持换行
    gfm: true, // 支持GitHub风格的Markdown
    sanitize: false // 允许HTML标签
  });
  
  try {
    // 将Markdown转换为HTML
    return marked(analysisResult.value);
  } catch (error) {
    console.error('Markdown渲染失败:', error);
    // 如果渲染失败，回退到简单的换行处理
    return analysisResult.value.replace(/\n/g, '<br>');
  }
});

// 获取股票代码列表
const getSymbolList = async () => {
  try {
    console.log('开始获取股票代码列表...');
    const response = await getStockSymbols();
    console.log('股票代码API响应:', response);

    let symbols = [];
    // 处理不同的响应数据结构
    if (response && response.result && Array.isArray(response.result)) {
      symbols = response.result;
    } else if (response && response.rows && Array.isArray(response.rows)) {
      symbols = response.rows;
    } else if (response && response.data && Array.isArray(response.data)) {
      symbols = response.data;
    } else if (Array.isArray(response)) {
      symbols = response;
    }

    // 如果是对象数组，提取symbol字段
    if (symbols.length > 0 && typeof symbols[0] === 'object') {
      symbols = symbols.map(item => item.symbol || item.code || item.name || item);
    }

    symbolList.value = symbols;
    console.log('股票代码列表加载完成:', symbolList.value);

    // 如果没有获取到数据，使用备用列表
    if (symbolList.value.length === 0) {
      console.warn('未获取到股票代码，使用备用列表');
      symbolList.value = ['AAPL', 'GOOGL', 'MSFT', 'TSLA', 'AMZN', 'ABBV', 'ABT', 'ACN', 'ADBE', 'AMAT', 'AMD', 'AMGN', 'NVDA', 'META', 'NFLX'];
    }
  } catch (error) {
    console.error('获取股票代码列表失败:', error);
    ElMessage.error('获取股票代码列表失败，使用默认列表');
    // 使用备用的股票代码列表
    symbolList.value = ['AAPL', 'GOOGL', 'MSFT', 'TSLA', 'AMZN', 'ABBV', 'ABT', 'ACN', 'ADBE', 'AMAT', 'AMD', 'AMGN', 'NVDA', 'META', 'NFLX'];
  }
};

// 股票代码变化处理
const onSymbolChange = (symbol: string) => {
  console.log('选择的股票代码:', symbol);
  // 清空之前的分析结果
  analysisResult.value = '';
};

// 分析股票
const analyzeStock = async () => {
  if (!selectedSymbol.value) {
    ElMessage.warning('请先选择股票代码');
    return;
  }

  loading.value = true;
  analysisResult.value = '';

  try {
    console.log('开始分析股票:', selectedSymbol.value);
    const response = await aiChatModel(selectedSymbol.value);
    console.log('AI分析响应:', response);

    // 处理响应数据
    let result = '';
    if (response && response.data) {
      result = response.data;
    } else if (typeof response === 'string') {
      result = response;
    } else {
      result = JSON.stringify(response);
    }

    // 如果返回的不是Markdown格式，则包装成Markdown格式
    if (result && !result.includes('#') && !result.includes('**') && !result.includes('*')) {
      result = `# ${selectedSymbol.value} 股票分析报告\n\n## 📊 分析结果\n\n${result}\n\n---\n\n*分析时间: ${new Date().toLocaleString()}*`;
    }

    analysisResult.value = result;
    ElMessage.success('AI分析完成');
  } catch (error) {
    console.error('AI分析失败:', error);
    ElMessage.error('AI分析失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};

// 组件挂载时初始化
onMounted(() => {
  getSymbolList();
});
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.box-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: bold;
}

.stock-selector {
  margin-bottom: 20px;
}

.demo-form-inline .el-form-item {
  margin-right: 20px;
}

.analysis-result {
  margin-top: 20px;
}

.result-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-weight: 600;
  color: #409eff;
}

.result-card {
  background: rgba(255, 255, 255, 0.95);
  border: 1px solid rgba(228, 231, 237, 0.6);
  border-radius: 12px;
  overflow: hidden;
  transition: all 0.3s ease;
  backdrop-filter: blur(8px);
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.result-card:hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  border-color: rgba(64, 158, 255, 0.3);
}

.result-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 20px;
  background: rgba(248, 250, 252, 0.8);
  border-bottom: 1px solid rgba(228, 231, 237, 0.4);
  backdrop-filter: blur(4px);
}

.analysis-time {
  font-size: 12px;
  color: #909399;
  font-weight: 500;
}

.result-content {
  padding: 20px;
  background: rgba(255, 255, 255, 0.6);
}

.content-wrapper {
  line-height: 1.8;
  color: #2c3e50;
  font-size: 15px;
  word-wrap: break-word;
  background: rgba(255, 255, 255, 0.9);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.02);
  border-left: 3px solid rgba(64, 158, 255, 0.6);
  position: relative;
  backdrop-filter: blur(2px);
}

.content-wrapper::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 1px;
  background: linear-gradient(90deg, rgba(64, 158, 255, 0.3), rgba(103, 194, 58, 0.3), rgba(230, 162, 60, 0.3), rgba(245, 108, 108, 0.3));
  border-radius: 1px 1px 0 0;
}

/* Markdown样式优化 */
.content-wrapper :deep(h1) {
  font-size: 24px;
  font-weight: 700;
  color: #1f2937;
  margin: 20px 0 16px 0;
  padding-bottom: 8px;
  border-bottom: 2px solid #e5e7eb;
}

.content-wrapper :deep(h2) {
  font-size: 20px;
  font-weight: 600;
  color: #374151;
  margin: 18px 0 14px 0;
  padding-bottom: 6px;
  border-bottom: 1px solid #e5e7eb;
}

.content-wrapper :deep(h3) {
  font-size: 18px;
  font-weight: 600;
  color: #4b5563;
  margin: 16px 0 12px 0;
}

.content-wrapper :deep(h4) {
  font-size: 16px;
  font-weight: 600;
  color: #6b7280;
  margin: 14px 0 10px 0;
}

.content-wrapper :deep(p) {
  margin: 12px 0;
  line-height: 1.7;
}

.content-wrapper :deep(ul), .content-wrapper :deep(ol) {
  margin: 12px 0;
  padding-left: 24px;
}

.content-wrapper :deep(li) {
  margin: 6px 0;
  line-height: 1.6;
}

.content-wrapper :deep(ul li) {
  list-style-type: disc;
}

.content-wrapper :deep(ol li) {
  list-style-type: decimal;
}

.content-wrapper :deep(blockquote) {
  margin: 16px 0;
  padding: 12px 16px;
  background: #f8fafc;
  border-left: 4px solid #3b82f6;
  border-radius: 0 4px 4px 0;
  font-style: italic;
  color: #64748b;
}

.content-wrapper :deep(code) {
  background: #f1f5f9;
  color: #e11d48;
  padding: 2px 6px;
  border-radius: 4px;
  font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
  font-size: 13px;
}

.content-wrapper :deep(pre) {
  background: #1e293b;
  color: #e2e8f0;
  padding: 16px;
  border-radius: 8px;
  overflow-x: auto;
  margin: 16px 0;
  font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
  font-size: 13px;
  line-height: 1.5;
}

.content-wrapper :deep(pre code) {
  background: transparent;
  color: inherit;
  padding: 0;
  border-radius: 0;
}

.content-wrapper :deep(table) {
  width: 100%;
  border-collapse: collapse;
  margin: 16px 0;
  background: #ffffff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.content-wrapper :deep(th), .content-wrapper :deep(td) {
  padding: 12px 16px;
  text-align: left;
  border-bottom: 1px solid #e5e7eb;
}

.content-wrapper :deep(th) {
  background: #f8fafc;
  font-weight: 600;
  color: #374151;
}

.content-wrapper :deep(tr:hover) {
  background: #f9fafb;
}

.content-wrapper :deep(strong) {
  font-weight: 600;
  color: #1f2937;
}

.content-wrapper :deep(em) {
  font-style: italic;
  color: #6b7280;
}

.content-wrapper :deep(hr) {
  border: none;
  height: 2px;
  background: linear-gradient(90deg, #e5e7eb, #d1d5db, #e5e7eb);
  margin: 24px 0;
  border-radius: 1px;
}

.content-wrapper :deep(a) {
  color: #3b82f6;
  text-decoration: none;
  border-bottom: 1px solid transparent;
  transition: all 0.2s ease;
}

.content-wrapper :deep(a:hover) {
  color: #1d4ed8;
  border-bottom-color: #3b82f6;
}

.empty-state {
  margin-top: 40px;
}

.loading-state {
  margin-top: 20px;
}

.is-loading {
  animation: rotating 2s linear infinite;
}

@keyframes rotating {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
