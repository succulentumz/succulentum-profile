# Используем официальный образ OpenJDK
FROM openjdk:22-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR файл в контейнер
COPY build/libs/Succulentum-0.0.3-SNAPSHOT.jar app.jar

# Указываем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
