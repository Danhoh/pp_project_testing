# Устанавливаем базовый образ
FROM amazoncorretto:11
LABEL name="CRUD_APP"

# Устанавливаем директорию приложения в контейнере
WORKDIR /app

# Копируем скомпилированный JAR файл в контейнер
COPY target/spring-boot_security-demo-0.0.1.jar /app/spring-boot_security-demo-0.0.1.jar

EXPOSE 8080

ENV DB_HOST="host.docker.internal"

# Запускаем JAR файл при старте контейнера
CMD ["java", "-jar", "spring-boot_security-demo-0.0.1.jar"]