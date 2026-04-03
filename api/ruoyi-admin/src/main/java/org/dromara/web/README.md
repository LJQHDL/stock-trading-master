# 美国股票数据同步API使用说明

## 概述

本模块提供了美国股票数据的同步功能，基于聚合数据API实现。参考了聚合数据官方示例代码进行优化，提供了更稳定和高效的数据同步服务。

## API配置

### 1. 申请聚合数据API Key

访问 [聚合数据官网](https://www.juhe.cn/) 申请美国股票数据API的访问密钥。

### 2. 配置文件设置

在 `application.yml` 或 `application.properties` 中配置：

```yaml
juhe:
  stock:
    api-key: 你申请的key
    url: http://web.juhe.cn/finance/stock/usaall
```

或者在 properties 文件中：

```properties
juhe.stock.api-key=你申请的key
juhe.stock.url=http://web.juhe.cn/finance/stock/usaall
```

## API接口说明

### 1. 同步美国股票数据

**接口地址：** `GET /market/sync`

**参数：**
- `page`: 页码（可选，默认为1）

**示例请求：**
```bash
GET /market/sync?page=1
```

**响应示例：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "totalCount": "1000",
    "page": "1",
    "num": "20",
    "data": [
      {
        "id": 1,
        "cname": "苹果公司",
        "symbol": "AAPL",
        "price": 150.25,
        "diff": 2.15,
        "chg": 1.45,
        "preclose": 148.10,
        "open": 149.00,
        "high": 151.20,
        "low": 148.80,
        "amplitude": 1.62,
        "volume": 45678900,
        "mktcap": 2500000000000,
        "market": "NASDAQ",
        "category": "Technology"
      }
    ]
  }
}
```

### 2. 多线程同步数据

**接口地址：** `GET /market/sync/multi`

**参数：**
- `startPage`: 起始页码（可选，默认为1）
- `endPage`: 结束页码（可选，默认为5）
- `threadPoolSize`: 线程池大小（可选，默认为3，最大为10）

**示例请求：**
```bash
GET /market/sync/multi?startPage=1&endPage=10&threadPoolSize=5
```

## 代码实现说明

### 1. 工具类使用

项目中提供了 `JuheApiUtil` 工具类，参考聚合数据官方示例代码实现：

```java
// 调用聚合数据API
String apiResponse = JuheApiUtil.callUsaStockApi(apiKey, apiUrl, page.toString());

// 格式化请求参数
String params = JuheApiUtil.formatParams(paramMap);
```

### 2. 服务层实现

`MarketServiceImpl` 类中的 `syncStockDataFromApi` 方法提供了两种调用方式：

- **方式一（推荐）：** 使用 `JuheApiUtil` 工具类
- **方式二（备选）：** 直接HTTP调用（已注释，保留作为备选方案）

### 3. 数据转换

使用 MapStruct Plus 进行对象转换：
- `StockUsaBo` -> `StockUsa` (Entity)
- `StockUsaBo` -> `StockUsaVo` (View Object)

## 错误处理

### 常见错误码

- `101`: KEY ERROR - API密钥错误
- `10001`: 参数错误
- `10002`: 请求频率超限
- `10003`: 余额不足

### 错误处理机制

1. **API调用失败：** 抛出 `RuntimeException` 并记录详细错误信息
2. **数据插入失败：** 记录错误日志但不中断整个同步流程
3. **参数编码失败：** 使用原始参数值并记录警告日志

## 性能优化

1. **多线程支持：** 提供多线程同步接口，可并发处理多个页面
2. **连接池管理：** 使用线程池控制并发数量，避免资源耗尽
3. **错误隔离：** 单个页面同步失败不影响其他页面
4. **日志记录：** 详细的同步过程日志，便于问题排查

## 注意事项

1. **API限制：** 请注意聚合数据API的调用频率限制
2. **数据重复：** 当前实现为每日新增模式，不检查重复数据
3. **异常处理：** 建议在生产环境中添加更完善的异常处理和重试机制
4. **数据校验：** 建议添加数据有效性校验逻辑

## 示例代码

### Java调用示例

```java
// 参考聚合数据官方示例
public class JavaGet {
    public static void main(String[] args) throws Exception {
        String apiKey = "你申请的key";
        String apiUrl = "http://web.juhe.cn/finance/stock/usaall";

        HashMap<String, String> map = new HashMap<>();
        map.put("key", apiKey);
        map.put("page", "1");

        URL url = new URL(String.format("%s?%s", apiUrl, JuheApiUtil.formatParams(map)));
        BufferedReader in = new BufferedReader(new InputStreamReader((url.openConnection()).getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);
    }
}
```

## 更新日志

- **2025-01-30：** 参考聚合数据官方示例代码重写API调用逻辑
- **2025-01-30：** 添加 `JuheApiUtil` 工具类
- **2025-01-30：** 优化错误处理和日志记录
- **2025-01-30：** 完善API文档和使用说明