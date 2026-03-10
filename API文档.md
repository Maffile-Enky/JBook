# JBook 图书管理API文档

## 项目概述
JBook是一个基于Spring Boot的图书管理系统，提供RESTful API接口用于图书的增删改查操作。

## 技术架构
- **后端框架**: Spring Boot 3.1.0
- **数据库**: MySQL + JPA/Hibernate
- **数据格式**: JSON
- **端口**: 8080

## 数据模型

### Book实体
```json
{
  "bookId": "string",      // 图书ID（主键）
  "bookName": "string",    // 图书名称
  "bookPrice": "string"    // 图书价格
}
```

## API接口详解

### 1. 健康检查接口
**用途**: 检查应用程序是否正常运行

- **URL**: `/api/books/health`
- **方法**: GET
- **参数**: 无
- **响应**: 
  ```
  Application is running!
  ```
- **状态码**: 200 OK

---

### 2. 获取所有图书
**用途**: 获取系统中所有图书的列表

- **URL**: `/api/books`
- **方法**: GET
- **参数**: 无
- **响应示例**:
  ```json
  [
    {
      "bookId": "B001",
      "bookName": "Java编程思想",
      "bookPrice": "89.00"
    },
    {
      "bookId": "B002", 
      "bookName": "Spring Boot实战",
      "bookPrice": "69.00"
    }
  ]
  ```
- **状态码**: 200 OK

---

### 3. 根据ID获取图书
**用途**: 根据图书ID获取特定图书的详细信息

- **URL**: `/api/books/{id}`
- **方法**: GET
- **路径参数**: 
  - `id` (必需): 图书ID
- **响应示例**:
  ```json
  {
    "bookId": "B001",
    "bookName": "Java编程思想",
    "bookPrice": "89.00"
  }
  ```
- **状态码**: 
  - 200 OK: 找到图书
  - 404 Not Found: 图书不存在

---

### 4. 创建新图书
**用途**: 在系统中添加新的图书

- **URL**: `/api/books`
- **方法**: POST
- **请求体**:
  ```json
  {
    "bookId": "B003",
    "bookName": "Python基础教程",
    "bookPrice": "59.00"
  }
  ```
- **响应**: 返回创建的图书对象
- **状态码**: 200 OK

---

### 5. 更新图书信息
**用途**: 更新现有图书的信息

- **URL**: `/api/books/{id}`
- **方法**: PUT
- **路径参数**: 
  - `id` (必需): 要更新的图书ID
- **请求体**:
  ```json
  {
    "bookId": "B001",
    "bookName": "Java编程思想（第4版）",
    "bookPrice": "99.00"
  }
  ```
- **响应**: 返回更新后的图书对象
- **状态码**: 
  - 200 OK: 更新成功
  - 404 Not Found: 图书不存在

---

### 6. 删除图书
**用途**: 从系统中删除指定的图书

- **URL**: `/api/books/{id}`
- **方法**: DELETE
- **路径参数**: 
  - `id` (必需): 要删除的图书ID
- **响应**: 无内容
- **状态码**: 
  - 204 No Content: 删除成功
  - 404 Not Found: 图书不存在

---

### 7. 搜索图书
**用途**: 根据图书名称进行模糊搜索

- **URL**: `/api/books/search`
- **方法**: GET
- **查询参数**: 
  - `name` (必需): 搜索关键词
- **响应示例**:
  ```json
  [
    {
      "bookId": "B001",
      "bookName": "Java编程思想",
      "bookPrice": "89.00"
    },
    {
      "bookId": "B002",
      "bookName": "Java核心技术",
      "bookPrice": "79.00"
    }
  ]
  ```
- **状态码**: 200 OK

## 使用示例

### 创建图书
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "bookId": "B001",
    "bookName": "Java编程思想",
    "bookPrice": "89.00"
  }'
```

### 搜索图书
```bash
curl "http://localhost:8080/api/books/search?name=Java"
```

### 更新图书
```bash
curl -X PUT http://localhost:8080/api/books/B001 \
  -H "Content-Type: application/json" \
  -d '{
    "bookId": "B001",
    "bookName": "Java编程思想（第5版）",
    "bookPrice": "99.00"
  }'
```

## 错误处理

所有API在发生错误时都会返回适当的HTTP状态码：

- **200 OK**: 请求成功
- **201 Created**: 资源创建成功
- **204 No Content**: 操作成功但无返回内容
- **400 Bad Request**: 请求参数错误
- **404 Not Found**: 请求的资源不存在
- **500 Internal Server Error**: 服务器内部错误

## 注意事项

1. 图书ID（bookId）是主键，不能重复
2. 价格字段使用字符串格式存储，支持各种价格格式
3. 搜索功能支持模糊匹配，不区分大小写
4. 所有API都返回JSON格式的数据
5. 数据库连接信息在`application.properties`中配置

## 部署说明

确保MySQL数据库已安装并运行，然后执行：
```bash
mvn spring-boot:run
```

应用程序将在`http://localhost:8080`启动。