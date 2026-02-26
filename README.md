## Northwind - Spring Boot REST API

This project is a **Spring Boot REST API** built around the classic Northwind database scenario using modern technologies. The goal is to demonstrate widely used technologies, layered architecture, and best practices in an enterprise-grade Java backend application.

- Designed and implemented RESTful APIs with Spring Web and HATEOAS.
- Secured endpoints with Spring Security using JWT and role-based access control (RBAC).
- Reduced response times by introducing Redis-based caching layers.
- Increased reliability with comprehensive unit and integration test coverage.
- Automated build and deployment using GitHub Actions CI/CD pipelines.

### Features

- **Tech stack**
    - **Java**: 21
    - **Framework**: Spring Boot 3.5.x
    - **Database**: PostgreSQL
    - **ORM**: Spring Data JPA (Hibernate)
    - **Database migrations**: Flyway
    - **Security**: Spring Security + JWT
    - **Caching**: Spring Cache + Redis + Redisson
    - **API documentation**: springdoc-openapi (Swagger UI)
    - **Mapper**: MapStruct
    - **Auditing**: JaVers

- **Architecture**
    - Layered structure (controller, service, repository, dto, mapper, etc.)
    - Clear separation of DTOs and entities
    - Validation, error handling and response modeling

### Project Structure

```text
src/main/java/com/deveyk/northwind/
├── auth/        # Authentication, authorization, security config, JWT, admin/auth APIs
├── product/     # Products, categories, suppliers, pricing, stock
├── order/       # Orders and order details, order workflows
├── customer/    # Customer-facing APIs and views of products/orders
├── employee/    # Employees and HR operations (public and HR endpoints)
├── common/      # Shared configuration, base entities, utilities
└── NorthwindApplication.java  # Spring Boot application entry point
```

### API Endpoints

Below is a high-level overview of the main REST endpoints. For full details (request/response models, parameters, status codes), refer to Swagger UI.

- **Authentication**
    - `POST /api/auth/login` – Authenticate user and obtain JWT access token.
    - `POST /api/auth/register` – Register a new user account.
    - `GET /api/auth/me` – Get details of the currently authenticated user.
    - `POST /api/auth/refresh` – Refresh JWT token.

- **Admin**
    - `GET /api/admin/users` – List users (paginated).
    - `GET /api/admin/users/{id}` – Get user by id.
    - `POST /api/admin/users` – Create a new user.
    - `PUT /api/admin/users/{id}` – Update an existing user.
    - `DELETE /api/admin/users/{id}` – Delete user.
    - `PATCH /api/admin/users/{id}/enable` – Enable user.
    - `PATCH /api/admin/users/{id}/disable` – Disable user.
    - `PATCH /api/admin/users/{id}` – Change user password.
    - `GET /api/admin/system` – System/health information for admins.

- **Products**
    - `GET /api/products` – List products (paginated, cached).
    - `GET /api/products/{id}` – Get product by id.
    - `POST /api/products` – Create product.
    - `PUT /api/products/{id}` – Update product.
    - `DELETE /api/products/{id}` – Delete product.
    - `GET /api/products/category/{categoryId}` – List products by category id.
    - `GET /api/products/category?categoryName=` – List products by category name.
    - `GET /api/products/supplier/{supplierId}` – List products by supplier id.
    - `GET /api/products/supplier?companyName=` – List products by supplier company name.
    - `GET /api/products/low-stock` – List low stock products.
    - `GET /api/products/discontinued` – List discontinued products.
    - `PATCH /api/products/{id}/stock?quantityChange=` – Adjust product stock.
    - `PATCH /api/products/{id}/discontinue` – Mark product as discontinued.
    - `PATCH /api/products/{id}/continue` – Re-activate a discontinued product.

- **Categories**
    - `GET /api/categories` – List categories (paginated, cached).
    - `GET /api/categories/{id}` – Get category by id.
    - `POST /api/categories` – Create category.
    - `PUT /api/categories/{id}` – Update category.
    - `DELETE /api/categories/{id}` – Delete category.
    - `GET /api/categories/{id}/product-count` – Get number of products in a category.
    - `GET /api/categories/popular` – List popular categories.

- **Suppliers**
    - `GET /api/suppliers` – List suppliers (paginated, cached).
    - `GET /api/suppliers/{id}` – Get supplier by id.
    - `POST /api/suppliers` – Create supplier.
    - `PUT /api/suppliers/{id}` – Update supplier.
    - `DELETE /api/suppliers/{id}` – Delete supplier.
    - `GET /api/suppliers/country?country=` – List suppliers by country.
    - `GET /api/suppliers/{id}/product-count` – Get number of products for a supplier.
    - `GET /api/suppliers/popular` – List popular suppliers.

- **Orders**
    - `GET /api/orders` – List orders (paginated, cached).
    - `GET /api/orders/{id}` – Get order by id.
    - `POST /api/orders` – Create order.
    - `PUT /api/orders/{id}` – Update order.
    - `DELETE /api/orders/{id}` – Delete order.

- **Employees (public information)**
    - `GET /api/employees` – List employees (paginated).
    - `GET /api/employees/{id}` – Get employee public info by id.
    - `GET /api/employees/{id}/territories` – Get manager/related info for employee.

- **HR (internal employee management)**
    - `GET /api/hr/employees` – List employees for HR (paginated).
    - `GET /api/hr/employees/{id}` – Get employee HR details.
    - `POST /api/hr/employees` – Hire new employee.
    - `DELETE /api/hr/employees/{id}` – Fire employee.
    - `PUT /api/hr/employees/{id}` – Update employee.
    - `PUT /api/hr/employees/{employeeId}/details` – Update employee details.
    - `PATCH /api/hr/employees/{id}/bonus?bonusRate=` – Update employee bonus.

- **Customers**
    - `GET /api/customers/info` – Get info for the authenticated customer.
    - `GET /api/customers` – List customers (paginated, admin/sales).
    - `GET /api/customers/{id}` – Get customer by id (admin/sales).
    - `GET /api/customers/products` – List products for customers (paginated).
    - `GET /api/customers/products/{id}` – Get product info for customers.
    - `GET /api/customers/orders` – List orders for the authenticated customer (paginated).
    - `GET /api/customers/orders/{id}` – Get order details for the authenticated customer.
    - `PATCH /api/customers/orders/{id}/cancel` – Cancel customer order.

### Security and Authentication

- The application uses **Spring Security** and **JWT** based authentication.
- Typical flow:
    - Send username/password to an authentication endpoint.
    - On success, a JWT token is returned.
    - Subsequent requests use the header `Authorization: Bearer <token>`.

Details of the flow are implemented in the `security` configuration and `auth` controller/service classes.

### Caching and Redis

- Caching is enabled via `@EnableCaching`.
- Redis can be used for frequently accessed queries and distributed locking (Redisson).
- Redis configuration is defined in the application configuration files.

### Testing

The project integrates the following main testing tools:

- **Spring Boot Test**
- **Testcontainers** (PostgreSQL, JUnit Jupiter integration)
- **Spring Security Test**
- **WireMock** for mocking external HTTP dependencies
- **H2** in-memory database (for test profile)
- **Spring Cloud Contract** for contract testing

### License

This project is intended for educational and example purposes. You are free to use it as a reference or adapt it to your own needs.