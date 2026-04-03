# 股票交易管理系统

> 基于 `RuoYi-Vue-Plus` 二次开发的前后端分离项目，聚焦于股票基础管理、美国股票行情同步和 AI 股票分析。

## 项目简介

`stock-trading` 是一个基于 `RuoYi-Vue-Plus` 二次开发的前后端分离项目，主题聚焦于股票数据管理、美国股票行情同步与 AI 分析。

项目在保留若依体系通用能力的基础上，扩展了以下核心场景：

- 股票基础信息管理
- 美国股票行情同步与查询
- AI 股票分析
- 系统管理、权限管理、字典管理、代码生成等后台能力
- 工作流相关页面与模块预留

## 项目亮点

- 基于成熟后台框架改造，具备较完整的权限、日志、代码生成和系统管理能力
- 自定义业务集中在 `market` 和 `aichat`，业务入口相对清晰
- 接入外部股票数据源，支持美股行情同步与批量抓取
- 集成 LangChain4j、DeepSeek 等大模型能力，可直接输出股票分析报告
- 前后端分离，便于继续扩展策略分析、交易记录、用户资产等后续能力

## 技术架构

### 后端

- Java 17
- Spring Boot 3.4.x
- Maven 多模块
- MyBatis-Plus
- Sa-Token
- Redis + Redisson
- Undertow
- SpringDoc
- LangChain4j
- DeepSeek

### 前端

- Vue 3
- TypeScript
- Vite
- Element Plus
- Pinia
- Vue Router
- Axios
- ECharts

## 目录结构

```text
stock-trading-master/
├── api/                        后端工程
│   ├── ruoyi-admin/            Spring Boot 启动模块与自定义业务
│   ├── ruoyi-common/           公共基础模块
│   ├── ruoyi-extend/           扩展模块
│   ├── ruoyi-modules/          系统、任务、代码生成、工作流等业务模块
│   └── script/                 SQL、Docker、脚本资源
├── ui/                         前端工程
├── README.en.md                英文说明
└── README.md                   当前说明文档
```

## 核心业务模块

### 1. 股票基础管理

- 路径：`/stock/stock`
- 功能：股票基础信息的增删改查、导出
- 适合用于维护内部股票主数据

### 2. 美国股票行情模块

- 路径：`/market`
- 功能：
- 美股分页查询
- 按股票代码查询历史数据
- 查询股票代码列表
- 导出行情数据
- 从外部接口同步美股数据
- 多线程批量同步

### 3. AI 股票分析模块

- 前端页面：`ui/src/views/aichat/aichat`
- 后端接口：`/market/AiChatModel/{symbol}`
- 功能：
- 读取指定股票最近一段时间的数据
- 结合大模型生成分析报告
- 前端将 Markdown 结果渲染为可视化分析内容

### 4. 系统基础能力

项目继承了 `RuoYi-Vue-Plus` 的大部分后台能力：

- 用户、角色、菜单、部门、岗位管理
- 登录认证与权限控制
- 字典、参数、公告、日志管理
- 文件上传配置
- 代码生成器
- 监控扩展能力

## 运行环境

建议本地开发环境：

- JDK 17
- Maven 3.9+
- Node.js 18.18+
- MySQL 8.x
- Redis 6.x 或 7.x

## 启动前检查

在首次启动前，建议先确认以下内容：

- 已创建 `stock` 数据库，并导入基础 SQL 脚本
- 本机已安装并启动 MySQL、Redis
- 后端配置中的数据库地址、账号、密码可正常连接
- 前端依赖已安装完成，Node.js 版本满足要求
- 如需使用 AI 分析，已配置可用的大模型 Key 和相关服务
- 如需使用聚合数据同步，已配置 `juhe.stock` 接口参数

## 快速启动

### 1. 初始化数据库

先创建数据库，例如：

```sql
CREATE DATABASE stock DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

然后根据需要导入 SQL：

- 基础表结构：`api/script/sql/ry_vue_5.X.sql`
- 工作流相关表：`api/script/sql/ry_workflow.sql`
- 定时任务相关表：`api/script/sql/ry_job.sql`

说明：

- 当前默认开发配置连接的数据库名是 `stock`
- 如果你修改了数据库名，请同步更新后端配置

### 2. 配置后端

修改文件：

- `api/ruoyi-admin/src/main/resources/application-dev.yml`
- `api/ruoyi-admin/src/main/resources/application.yml`

重点检查以下配置：

- MySQL 地址、库名、用户名、密码
- Redis 地址与端口
- 聚合数据 `juhe.stock` 接口配置
- 大模型配置，例如 `deepseek`、`langchain4j`

建议将密钥、密码等敏感信息迁移到本地环境变量或私有配置中，不要直接提交到仓库。

### 3. 启动后端

进入后端目录：

```bash
cd api
```

使用 Maven 启动：

```bash
mvn -pl ruoyi-admin -am spring-boot:run
```

或者直接在 IDEA 中运行启动类：

```text
org.dromara.DromaraApplication
```

默认后端地址：

```text
http://localhost:8080
```

### 4. 启动前端

进入前端目录：

```bash
cd ui
```

安装依赖：

```bash
npm install
```

启动开发环境：

```bash
npm run dev
```

默认前端访问地址：

```text
http://localhost:80
```

前端开发环境会将 `/dev-api` 代理到 `http://localhost:8080`。

### 5. Docker 资源

项目提供了 Docker 编排示例，位于：

```text
api/script/docker/docker-compose.yml
```

其中包含以下服务示例：

- MySQL
- Redis
- Nginx
- MinIO
- 后端服务
- 监控服务
- SnailJob

这些配置更适合作为部署参考，实际使用时建议根据本机路径和环境重新调整。

## 默认访问地址

- 前端开发地址：`http://localhost:80`
- 后端接口地址：`http://localhost:8080`
- 前端代理前缀：`/dev-api`
- 监控地址配置：`http://localhost:9090/admin/applications`
- SnailJob 地址配置：`http://localhost:8800/snail-job`

## 主要接口示例

### 股票基础接口

- `GET /stock/stock/list`
- `GET /stock/stock/{id}`
- `POST /stock/stock`
- `PUT /stock/stock`
- `DELETE /stock/stock/{ids}`

### 美股行情接口

- `GET /market/list`
- `GET /market/symbols`
- `GET /market/symbol/{symbol}`
- `GET /market/sync?page=1`
- `GET /market/sync/multi?startPage=1&endPage=5&threadPoolSize=3`
- `GET /market/AiChatModel/{symbol}`

### AI 与实验接口

- `GET /lowChat`
- `GET /highChat`
- `GET /chat`
- `GET /highchat`
- `GET /models`
- `GET /customerSupportAgent`

说明：

- 以上接口中有一部分更偏向演示或实验用途
- 如果用于正式环境，建议补充鉴权、限流、异常处理与接口文档说明

## 开发说明

### 后端模块说明

- `ruoyi-admin`：启动模块、自定义控制器、股票和 AI 相关逻辑
- `ruoyi-common`：通用基础设施
- `ruoyi-modules/ruoyi-system`：系统管理能力
- `ruoyi-modules/ruoyi-generator`：代码生成器
- `ruoyi-modules/ruoyi-workflow`：工作流模块

### 前端模块说明

- `src/views/stock`：股票基础管理页面
- `src/views/market`：美股行情页面
- `src/views/aichat`：AI 股票分析页面
- `src/views/system`：系统管理页面
- `src/views/workflow`：工作流页面

## 当前状态说明

根据当前仓库代码，项目现状大致如下：

- `market` 模块是当前最完整、最清晰的主业务模块
- `aichat` 模块已完成前后端基本打通，可用于股票分析展示
- 系统管理能力大部分沿用若依底座，可直接作为后台能力使用
- 工作流前后端代码已存在，但是否在当前运行配置中完全启用，仍需要结合实际依赖和菜单配置确认
- 部分页面或接口仍带有实验性质，正式上线前建议梳理边界与权限

## 注意事项

- 当前仓库中包含本地化开发配置，接手项目后请优先替换数据库密码、API Key、模型 Key 等敏感信息
- 如果启用工作流，请确认相关 Maven 依赖、数据库脚本和前端菜单是否一致
- 某些页面可能仍处于开发或预留状态，建议先以 `market` 与 `aichat` 作为主业务入口理解项目

## 已知待完善项

- 根目录说明文档已补齐，但模块级文档仍可继续完善
- 敏感配置仍存在于项目配置文件中，建议尽快脱敏
- 部分实验型 AI 接口缺少统一的权限控制与生产化约束
- 某些前端页面与后端接口的对齐情况，仍建议在联调时再次确认
- 工作流、充值中心等扩展模块建议单独补充状态说明

## 后续建议

如果你准备继续维护这个项目，建议优先做以下几件事：

1. 整理并脱敏配置文件
2. 统一完善根目录与模块文档
3. 明确哪些功能是正式业务，哪些是实验接口
4. 核对前后端接口是否完全对齐
5. 补充启动、部署和测试说明
