🎬 DisneyApp

DisneyApp es una API REST que gestiona usuarios, roles, géneros, películas y personajes.

Los usuarios autenticados con JWT pueden consultar información.

Solo ADMIN puede crear o actualizar entidades.
Un usuario con rol ADMIN puede crear más usuarios y entidades.
Los usuarios USER solo tienen acceso a GET.

✅ Incluye MapStruct para el mapeo con DTOs, excepciones personalizadas y contraseñas encriptadas con BCrypt.

✨ Características

🔹 CRUD de usuarios, roles, géneros, películas y personajes

🔹 Sistema de autenticación con JWT

🔹 Roles: ADMIN y USER

🔹 Uso de Lombok y MapStruct

🔹 Manejo de errores con excepciones personalizadas

🔹 Contraseñas encriptadas con BCrypt

🔹 Documentación Swagger / OpenAPI

🔹 Base de datos H2 con datos precargados



⚙️ Tecnologías

💻 Java 24

🌱 Spring Boot 3.5.5

🗃️ Spring Data JPA

🔐 Spring Security (JWT y BCrypt)

🌐 Spring Web

🛢️ H2 Database

🔗 MySQL Connector (runtime)

📝 Lombok

🔄 MapStruct

📖 SpringDoc OpenAPI / Swagger

🛠️ DevTools

🧪 JUnit

🐳 Docker


🚀 Instalación

git clone https://github.com/RobertoTorre96/API-PeliculasDisney.git
cd DisneyApp
./mvnw spring-boot:run


También desde IntelliJ o Eclipse.

🔐 Autenticación

POST /auth/login

{
  "email": "string",
  "password": "string"
}


Usuarios de prueba:
  
| Usuario | Password | Rol   |
|---------|----------|-------|
| admin   | 1234    | ADMIN |
| user    | 1234     | USER  |

Un ADMIN puede crear más usuarios.

🧪 Base de datos H2

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa
Contraseña: (vacío)

📚 Swagger

URL: http://localhost:8080/swagger-ui.html
Desde ahí se pueden consultar y probar todos los endpoints.

📸 Ejemplo de uso

Login:
POST /auth/login
Content-Type: application/json

{
  "email": "admin",
  "password": "1234"
}


Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "roles": ["ADMIN"]
}


Crear personaje (solo ADMIN):

POST /personajes
Authorization: Bearer <token>
Content-Type: application/json

{
  "nombre": "Mickey",
  "edad": 95,
  "peso": 15.5,
  "historia": "Ratón icónico de Disney",
  "peliculas": [1,2]
}


Respuesta:

{
  "id": 10,
  "nombre": "Mickey",
  "edad": 95,
  "peso": 15.5,
  "historia": "Ratón icónico de Disney",
  "peliculas": [1,2]
}

Para el resto de endpoints, consultar Swagger.

👥 Autor
Edmundo Roberto Torre

GitHub
https://github.com/RobertoTorre96

✉️ Contacto: torreroberto1996@gmail.com
