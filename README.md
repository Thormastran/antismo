- thay đổi tên project com.example.dothis -> antismo.backend (maybe)
- thêm sẵn các package để dễ dàng theo dõi các tiến trình

src/
└── main/

    ├── java/

    │     └── antismo/

    │           └── backend/

    │               ├── CaidautienApplication.java

    │               ├── config/           // Cấu hình bảo mật, Swagger, JWT, ... 

    │               ├── controller/       // REST API Controllers (UserController, AuthController, ...)

    │               ├── dto/              // DTOs (UserDTO, AuthRequest, AuthResponse, ...)

    │               ├── entity/           // Entity (User, Role, UserRole, ...)

    │               ├── exception/        // Custom Exception + Handler

    │               ├── mapper/           // MapStruct hoặc manual mapper (Entity <-> DTO)

    │               ├── repository/       // JPA Repositories

    │               ├── security/         // JWT filter, provider, user details, ... (nếu dùng Spring Security)

    │               ├── service/          // Business Logic Services (UserService, AuthService, ...)

    │               └── util/             // Tiện ích chung (helper, constants, validators,...)

    └── resources/

