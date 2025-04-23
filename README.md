# 📦 Package System - Spring Boot Project

This is a Spring Boot-based web application for uploading and downloading software packages with version control. It was built as part of a technical assignment for a job application.

## 🚀 Features

- Authors can:
  - Upload packages with metadata (e.g., name, version, description)
- Users can:
  - Download selected packages

## 💠 Tech Stack

- **Backend**: Spring Boot
  - Spring Web
  - Spring Data JPA
  - Lombok
  - PostgreSQL Driver
  - Spring Boot DevTools
- **Database**: PostgreSQL

## ⚙️ Configuration

Make sure to update `application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/assignment
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🛄 File Upload Flow

1. Authors upload a `.zip` or similar file using a REST API.
2. Metadata like package name and version is stored in the database.
3. File is saved to a local directory or cloud storage.

## 🛅 File Download Flow

1. Users send a request with the package author/name/version.
2. The file is fetched from local storage.
3. The file is returned as a download response.


## 👨‍💼 Author

This project was developed by F. Emre Gokyar as part of a job application assignment.  
You can contact me at: fegokyar@gmail.com

