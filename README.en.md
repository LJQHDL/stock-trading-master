# Stock Trading Management System

> A front-end and back-end separated project built on top of `RuoYi-Vue-Plus`, focused on stock master data management, U.S. stock market synchronization, and AI-based stock analysis.

## Overview

`stock-trading` is a secondary development project based on `RuoYi-Vue-Plus`. Its main focus is stock data management, U.S. stock market synchronization, and AI-powered stock analysis.

On top of the original RuoYi system capabilities, this project extends the following core scenarios:

- Stock master data management
- U.S. stock market synchronization and query
- AI stock analysis
- System management, permission control, dictionary management, code generation, and other admin capabilities
- Reserved workflow-related pages and modules

## Highlights

- Built on a mature admin framework with complete capabilities for permissions, logs, code generation, and system management
- Custom business logic is mainly concentrated in `market` and `aichat`, making the main business entry points relatively clear
- Integrates external stock data sources and supports U.S. stock synchronization and batch fetching
- Integrates large-model capabilities such as LangChain4j and DeepSeek to generate stock analysis reports directly
- Front-end and back-end are decoupled, making it easier to extend strategy analysis, trading records, user assets, and other future features

## Technology Stack

### Backend

- Java 17
- Spring Boot 3.4.x
- Maven multi-module project
- MyBatis-Plus
- Sa-Token
- Redis + Redisson
- Undertow
- SpringDoc
- LangChain4j
- DeepSeek

### Frontend

- Vue 3
- TypeScript
- Vite
- Element Plus
- Pinia
- Vue Router
- Axios
- ECharts

## Project Structure

```text
stock-trading-master/
├── api/                        Backend project
│   ├── ruoyi-admin/            Spring Boot entry module and custom business logic
│   ├── ruoyi-common/           Shared common modules
│   ├── ruoyi-extend/           Extension modules
│   ├── ruoyi-modules/          System, job, code generator, workflow, and other business modules
│   └── script/                 SQL, Docker, and script resources
├── ui/                         Frontend project
├── README.en.md                English documentation
└── README.md                   Chinese documentation
```

## Core Business Modules

### 1. Stock Master Data Management

- Path: `/stock/stock`
- Features: CRUD operations and export for stock master data
- Suitable for maintaining internal stock master records

### 2. U.S. Stock Market Module

- Path: `/market`
- Features:
- U.S. stock pagination query
- Historical data query by stock symbol
- Stock symbol list query
- Market data export
- Synchronization from external stock APIs
- Multi-threaded batch synchronization

### 3. AI Stock Analysis Module

- Front-end page: `ui/src/views/aichat/aichat`
- Back-end endpoint: `/market/AiChatModel/{symbol}`
- Features:
- Read recent stock data for a selected symbol
- Generate analysis reports with large language models
- Render Markdown results into visual analysis content on the frontend

### 4. System Capabilities

The project keeps most of the built-in admin capabilities provided by `RuoYi-Vue-Plus`, including:

- User, role, menu, department, and post management
- Login authentication and permission control
- Dictionary, parameter, announcement, and log management
- File upload configuration
- Code generator
- Monitoring-related extension capabilities

## Environment Requirements

Recommended local development environment:

- JDK 17
- Maven 3.9+
- Node.js 18.18+
- MySQL 8.x
- Redis 6.x or 7.x

## Pre-Startup Checklist

Before running the project for the first time, it is recommended to confirm the following:

- The `stock` database has been created and the required SQL scripts have been imported
- MySQL and Redis are installed and running locally
- Database host, username, and password in the backend configuration are valid
- Frontend dependencies have been installed and the Node.js version meets the requirement
- If AI analysis is needed, valid model keys and related services are configured
- If stock synchronization is needed, the `juhe.stock` API configuration is ready

## Quick Start

### 1. Initialize the Database

Create the database first, for example:

```sql
CREATE DATABASE stock DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

Then import SQL files as needed:

- Base schema: `api/script/sql/ry_vue_5.X.sql`
- Workflow-related tables: `api/script/sql/ry_workflow.sql`
- Scheduled job tables: `api/script/sql/ry_job.sql`

Notes:

- The default development configuration connects to a database named `stock`
- If you change the database name, make sure to update the backend configuration accordingly

### 2. Configure the Backend

Update the following files:

- `api/ruoyi-admin/src/main/resources/application-dev.yml`
- `api/ruoyi-admin/src/main/resources/application.yml`

Pay attention to these configurations:

- MySQL host, database name, username, and password
- Redis host and port
- `juhe.stock` API configuration
- Large-model configuration such as `deepseek` and `langchain4j`

It is recommended to move secrets, passwords, and API keys to local environment variables or private configuration files instead of committing them to the repository.

### 3. Start the Backend

Go to the backend directory:

```bash
cd api
```

Start with Maven:

```bash
mvn -pl ruoyi-admin -am spring-boot:run
```

Or run the main class directly in IntelliJ IDEA:

```text
org.dromara.DromaraApplication
```

Default backend address:

```text
http://localhost:8080
```

### 4. Start the Frontend

Go to the frontend directory:

```bash
cd ui
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Default frontend address:

```text
http://localhost:80
```

In development mode, the frontend proxies `/dev-api` to `http://localhost:8080`.

### 5. Docker Resources

The project includes a Docker Compose example at:

```text
api/script/docker/docker-compose.yml
```

It contains example services such as:

- MySQL
- Redis
- Nginx
- MinIO
- Backend services
- Monitoring services
- SnailJob

These configurations are better treated as deployment references. In actual use, you should adjust paths and environment settings based on your machine.

## Default Access Addresses

- Frontend development URL: `http://localhost:80`
- Backend API URL: `http://localhost:8080`
- Frontend proxy prefix: `/dev-api`
- Monitoring URL: `http://localhost:9090/admin/applications`
- SnailJob URL: `http://localhost:8800/snail-job`

## Main API Examples

### Stock Master Data APIs

- `GET /stock/stock/list`
- `GET /stock/stock/{id}`
- `POST /stock/stock`
- `PUT /stock/stock`
- `DELETE /stock/stock/{ids}`

### U.S. Stock Market APIs

- `GET /market/list`
- `GET /market/symbols`
- `GET /market/symbol/{symbol}`
- `GET /market/sync?page=1`
- `GET /market/sync/multi?startPage=1&endPage=5&threadPoolSize=3`
- `GET /market/AiChatModel/{symbol}`

### AI and Experimental APIs

- `GET /lowChat`
- `GET /highChat`
- `GET /chat`
- `GET /highchat`
- `GET /models`
- `GET /customerSupportAgent`

Notes:

- Some of the endpoints above are more demo-oriented or experimental
- For production use, it is recommended to add authentication, rate limiting, exception handling, and clearer API documentation

## Development Notes

### Backend Modules

- `ruoyi-admin`: startup module, custom controllers, stock and AI-related logic
- `ruoyi-common`: common infrastructure
- `ruoyi-modules/ruoyi-system`: system management capabilities
- `ruoyi-modules/ruoyi-generator`: code generator
- `ruoyi-modules/ruoyi-workflow`: workflow module

### Frontend Modules

- `src/views/stock`: stock master data pages
- `src/views/market`: U.S. stock market pages
- `src/views/aichat`: AI stock analysis pages
- `src/views/system`: system management pages
- `src/views/workflow`: workflow pages

## Current Project Status

Based on the current codebase, the project status is roughly as follows:

- The `market` module is currently the most complete and clearest core business module
- The `aichat` module has already connected frontend and backend and can be used for stock analysis display
- Most system management capabilities come directly from the RuoYi base framework and are ready to use
- Workflow-related code exists on both frontend and backend, but whether it is fully enabled in the current runtime setup still needs to be verified against dependencies and menu configuration
- Some pages and APIs are still experimental in nature and should be reviewed before production rollout

## Notes

- The repository currently contains localized development configuration. After taking over the project, replace database passwords, API keys, model keys, and other sensitive values first
- If workflow is enabled, confirm that Maven dependencies, database scripts, and frontend menus are all aligned
- Some pages may still be under development or reserved for future use. It is recommended to understand the project from `market` and `aichat` first

## Known Improvements Needed

- The root-level documentation is now completed, but module-level documentation can still be improved
- Sensitive configurations are still stored in project config files and should be sanitized as soon as possible
- Some experimental AI endpoints still lack unified access control and production-grade constraints
- Frontend and backend alignment for some pages should still be rechecked during integration testing
- Workflow, recharge center, and other extension modules should have their own status documentation

## Recommended Next Steps

If you plan to continue maintaining this project, it is recommended to prioritize the following:

1. Clean up and sanitize configuration files
2. Improve root-level and module-level documentation consistently
3. Clarify which features are official business functions and which are experimental
4. Verify that frontend and backend APIs are fully aligned
5. Add clearer startup, deployment, and testing documentation
