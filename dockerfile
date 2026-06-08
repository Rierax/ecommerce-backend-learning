FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копирование Maven Wrapper
COPY mvnw mvnw.cmd ./
RUN chmod +x mvnw

# Копирование pom.xml и исходного кода
COPY pom.xml .
COPY src ./src

# Сборка проекта внутри контейнера
RUN ./mvnw clean package -DskipTests

# Запуск приложения (если это Spring Boot)
CMD ["java", "-jar", "target/ecommerce-backend-learning-1.0.0.jar"]