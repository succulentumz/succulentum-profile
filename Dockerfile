# Используем официальный образ OpenJDK
FROM openjdk:22-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR файл в контейнер
COPY build/libs/succulentum-0.0.2-SNAPSHOT.jar app.jar

# Указываем точку входа для запуска приложения
ENTRYPOINT ["java", "-cp", "app.jar", "com.example.succulentum.SucculentumApplication"]
