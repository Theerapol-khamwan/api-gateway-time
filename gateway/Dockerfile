FROM openjdk:8-jdk-alpine

# กำหนด port ที่ Spring Boot application จะรัน
EXPOSE 8080

# กำหนดตัวแปรสำหรับไฟล์ JAR และคัดลอกไฟล์ JAR ลงใน Docker image
ADD target/spring-boot-docker-gateway.jar.jar spring-boot-docker-gateway.jar

# กำหนดคำสั่งเริ่มต้นสำหรับ Docker container
ENTRYPOINT ["java", "-jar", "/spring-boot-docker-gateway.jar"]
