<template>
  <div>
    <!-- 股票Symbol筛选器 -->
    <div class="stock-selector">
      <el-select v-model="selectedSymbol" placeholder="请选择股票代码" clearable @change="onSymbolChange" style="width: 200px">
        <el-option v-for="symbol in symbolList" :key="symbol" :label="symbol" :value="symbol" />
      </el-select>
      <el-button @click="refreshChart" type="primary" style="margin-left: 10px">刷新图表</el-button>
    </div>
    <!-- K线图容器 -->
    <div class="chart-container">
      <div ref="chartRef" style="width: 100%; height: 600px"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, watch } from 'vue';
import * as echarts from 'echarts';
import { listStock, getStockBySymbol } from '@/api/market/market';

const chartRef = ref<HTMLDivElement>();
let myChart: echarts.ECharts | null = null;
const selectedSymbol = ref<string>('AAPL');
const symbolList = ref<string[]>([]);

const upColor = '#00da3c';
const downColor = '#ec0000';

function splitData(rawData: number[][]) {
  const categoryData = [];
  const values = [];
  const volumes = [];
  for (let i = 0; i < rawData.length; i++) {
    // 不要修改原数组，直接提取数据
    const date = rawData[i][0];
    const open = rawData[i][1];
    const close = rawData[i][2];
    const low = rawData[i][3];
    const high = rawData[i][4];

    categoryData.push(date);
    values.push([open, close, low, high]);
    volumes.push([i, high, open > close ? 1 : -1]);
  }

  return {
    categoryData: categoryData,
    values: values,
    volumes: volumes
  };
}

function calculateMA(dayCount: number, data: { values: number[][] }) {
  const result = [];
  for (let i = 0, len = data.values.length; i < len; i++) {
    if (i < dayCount) {
      result.push('-');
      continue;
    }
    let sum = 0;
    for (let j = 0; j < dayCount; j++) {
      sum += data.values[i - j][1];
    }
    result.push(+(sum / dayCount).toFixed(3));
  }
  return result;
}



// 获取股票Symbol列表
const getSymbolList = async () => {
  try {
    console.log('开始获取股票Symbol列表...');
    // 由于 getStockBySymbol 需要 symbol 参数，我们使用预定义的股票代码列表
    const predefinedSymbols = ['AAPL', 'GOOGL', 'MSFT', 'TSLA', 'AMZN', 'ABBV', 'ABT', 'ACN', 'ADBE', 'AMAT', 'AMD', 'AMGN'];

    console.log('使用预定义的Symbol列表:', predefinedSymbols);
    symbolList.value = predefinedSymbols;
  } catch (error) {
    console.error('获取Symbol列表失败:', error);
    console.log('使用模拟数据作为备选');
    symbolList.value = ['AAPL', 'GOOGL', 'MSFT', 'TSLA', 'AMZN'];
  }
};

// Symbol变化处理
const onSymbolChange = async (symbol: string) => {
  console.log('Symbol变化事件触发:', symbol);
  console.log('当前selectedSymbol.value:', selectedSymbol.value);
  console.log('传入的symbol参数:', symbol);
  console.log('symbol类型:', typeof symbol);
  await refreshChart();
};

// 加载股票数据
const loadStockData = async (symbol: string) => {
  try {
    console.log('开始加载股票数据，Symbol:', symbol);
    const response = await getStockBySymbol(symbol);
    console.log('股票数据API响应:', response);

    let stockData = null;

    // 检查不同的响应数据结构 - getStockBySymbol 返回单个股票对象
    if (response && response.result) {
      stockData = response.result;
    } else if (response && response.data) {
      stockData = response.data;
    } else if (response) {
      stockData = response;
    }

    console.log('处理后的股票数据:', stockData);

    if (!stockData) {
      console.warn('未获取到股票数据');
      return [];
    }

    // 由于 getStockBySymbol 返回单个股票对象，我们需要生成模拟的历史数据
    // 这里生成30天的模拟K线数据
    const klineData = [];
    const basePrice = parseFloat(stockData.currentPrice || stockData.price || 100);
    const today = new Date();

    for (let i = 29; i >= 0; i--) {
      const date = new Date(today);
      date.setDate(date.getDate() - i);
      const dateStr = date.toISOString().split('T')[0];

      // 生成模拟的OHLC数据，基于当前价格波动
      const variation = (Math.random() - 0.5) * 0.1; // ±5%的波动
      const dayPrice = basePrice * (1 + variation);
      const open = dayPrice * (1 + (Math.random() - 0.5) * 0.02);
      const close = dayPrice * (1 + (Math.random() - 0.5) * 0.02);
      const high = Math.max(open, close) * (1 + Math.random() * 0.02);
      const low = Math.min(open, close) * (1 - Math.random() * 0.02);

      klineData.push([
        dateStr,
        parseFloat(open.toFixed(2)),
        parseFloat(close.toFixed(2)),
        parseFloat(low.toFixed(2)),
        parseFloat(high.toFixed(2))
      ]);
    }

    console.log('转换后的K线数据:', klineData.slice(0, 5)); // 只显示前5条
    return klineData;
  } catch (error) {
    console.error('加载股票数据失败:', error);
    console.error('错误详情:', {
      message: error?.message || '未知错误',
      status: error?.response?.status || '无状态码',
      statusText: error?.response?.statusText || '无状态文本',
      data: error?.response?.data || '无响应数据',
      config: {
        url: error?.config?.url || '无URL',
        method: error?.config?.method || '无方法'
      }
    });
    return [];
  }
};

// 刷新图表
const refreshChart = async () => {
  if (!myChart) return;

  // 检查是否有选中的股票代码
  if (!selectedSymbol.value) {
    console.warn('没有选中的股票代码');
    return;
  }

  const rawData = await loadStockData(selectedSymbol.value);

  if (!rawData || rawData.length === 0) {
    // 清空图表，不显示任何提示文字
    const noDataOption = {
      xAxis: { show: false },
      yAxis: { show: false },
      series: []
    };
    myChart.setOption(noDataOption);
    return;
  }

  const data = splitData(rawData);

  const option = {
    animation: false,
    legend: {
      bottom: 10,
      left: 'center',
      data: ['股票价格', 'MA5', 'MA10', 'MA20', 'MA30']
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross'
      },
      borderWidth: 1,
      borderColor: '#ccc',
      padding: 10,
      textStyle: {
        color: '#000'
      },
      position: function (pos: number[], params: any, el: any, elRect: any, size: any) {
        const obj: Record<string, number> = {
          top: 10
        };
        obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
        return obj;
      }
    },
    axisPointer: {
      link: [
        {
          xAxisIndex: 'all'
        }
      ],
      label: {
        backgroundColor: '#777'
      }
    },
    toolbox: {
      feature: {
        dataZoom: {
          yAxisIndex: false
        },
        brush: {
          type: ['lineX', 'clear']
        }
      }
    },
    brush: {
      xAxisIndex: 'all',
      brushLink: 'all',
      outOfBrush: {
        colorAlpha: 0.1
      }
    },
    visualMap: {
      show: false,
      seriesIndex: 5,
      dimension: 2,
      pieces: [
        {
          value: 1,
          color: downColor
        },
        {
          value: -1,
          color: upColor
        }
      ]
    },
    grid: [
      {
        left: '10%',
        right: '8%',
        height: '50%'
      },
      {
        left: '10%',
        right: '8%',
        top: '63%',
        height: '16%'
      }
    ],
    xAxis: [
      {
        type: 'category',
        data: data.categoryData,
        boundaryGap: false,
        axisLine: { onZero: false },
        splitLine: { show: false },
        min: 'dataMin',
        max: 'dataMax',
        axisPointer: {
          z: 100
        }
      },
      {
        type: 'category',
        gridIndex: 1,
        data: data.categoryData,
        boundaryGap: false,
        axisLine: { onZero: false },
        axisTick: { show: false },
        splitLine: { show: false },
        axisLabel: { show: false },
        min: 'dataMin',
        max: 'dataMax'
      }
    ],
    yAxis: [
      {
        scale: true,
        splitArea: {
          show: true
        }
      },
      {
        scale: true,
        gridIndex: 1,
        splitNumber: 2,
        axisLabel: { show: false },
        axisLine: { show: false },
        axisTick: { show: false },
        splitLine: { show: false }
      }
    ],
    dataZoom: [
      {
        type: 'inside',
        xAxisIndex: [0, 1],
        start: 98,
        end: 100
      },
      {
        show: true,
        xAxisIndex: [0, 1],
        type: 'slider',
        top: '85%',
        start: 98,
        end: 100
      }
    ],
    series: [
      {
        name: '股票价格',
        type: 'candlestick',
        data: data.values,
        itemStyle: {
          color: upColor,
          color0: downColor,
          borderColor: undefined,
          borderColor0: undefined
        }
      },
      {
        name: 'MA5',
        type: 'line',
        data: calculateMA(5, data),
        smooth: true,
        lineStyle: {
          opacity: 0.5
        }
      },
      {
        name: 'MA10',
        type: 'line',
        data: calculateMA(10, data),
        smooth: true,
        lineStyle: {
          opacity: 0.5
        }
      },
      {
        name: 'MA20',
        type: 'line',
        data: calculateMA(20, data),
        smooth: true,
        lineStyle: {
          opacity: 0.5
        }
      },
      {
        name: 'MA30',
        type: 'line',
        data: calculateMA(30, data),
        smooth: true,
        lineStyle: {
          opacity: 0.5
        }
      },
      {
        name: 'Volume',
        type: 'bar',
        xAxisIndex: 1,
        yAxisIndex: 1,
        data: data.volumes
      }
    ]
  };

  myChart.setOption(option, true);
};

// 监听selectedSymbol变化
watch(selectedSymbol, (newValue, oldValue) => {
  console.log('selectedSymbol watch触发:');
  console.log('  旧值:', oldValue);
  console.log('  新值:', newValue);
  console.log('  新值类型:', typeof newValue);
});

onMounted(async () => {
  if (chartRef.value) {
    // 基于准备好的dom，初始化echarts实例
    myChart = echarts.init(chartRef.value);

    // 获取Symbol列表
    await getSymbolList();

    // 初始化图表
    await refreshChart();

    // 监听窗口大小变化
    window.addEventListener('resize', () => {
      if (myChart) {
        myChart.resize();
      }
    });
  }
});

onUnmounted(() => {
  // 销毁图表实例
  if (myChart) {
    myChart.dispose();
    myChart = null;
  }
  // 移除事件监听
  window.removeEventListener('resize', () => {
    myChart?.resize();
  });
});
</script>

<style scoped>
/* K线图组件样式 */
.stock-selector {
  background: #1a1a1a;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
}

.stock-selector .el-select {
  background: #2d2d2d;
}

.stock-selector .el-select .el-input__inner {
  background: #2d2d2d;
  border: 1px solid #404040;
  color: #ffffff;
  border-radius: 4px;
}

.stock-selector .el-select .el-input__inner:hover {
  border-color: #606060;
}

.stock-selector .el-select .el-input__inner:focus {
  border-color: #409eff;
}

.stock-selector .el-button {
  background: #409eff;
  border-color: #409eff;
  color: #ffffff;
}

.stock-selector .el-button:hover {
  background: #66b1ff;
  border-color: #66b1ff;
}

/* 图表容器样式 */
.chart-container {
  background: #1a1a1a;
  border-radius: 8px;
  padding: 10px;
}

/* 下拉框选项样式 */
:deep(.el-select-dropdown) {
  background: #2d2d2d !important;
  border: 1px solid #404040 !important;
}

:deep(.el-select-dropdown .el-select-dropdown__item) {
  background: #2d2d2d !important;
  color: #ffffff !important;
}

:deep(.el-select-dropdown .el-select-dropdown__item:hover) {
  background: #404040 !important;
}

:deep(.el-select-dropdown .el-select-dropdown__item.selected) {
  background: #409eff !important;
  color: #ffffff !important;
}

/* 输入框图标样式 */
:deep(.el-select .el-input__suffix) {
  color: #ffffff;
}

:deep(.el-select .el-input__suffix .el-input__icon) {
  color: #ffffff;
}
</style>
