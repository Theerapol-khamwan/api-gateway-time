FROM openjdk:8-jdk-alpine

# กำหนด port ที่ Spring Boot application จะรัน
EXPOSE 8081

# กำหนดตัวแปรสำหรับไฟล์ JAR และคัดลอกไฟล์ JAR ลงใน Docker image
ADD target/spring-boot-docker-time.jar.jar spring-boot-docker-time.jar

# กำหนดคำสั่งเริ่มต้นสำหรับ Docker container
ENTRYPOINT ["java", "-jar", "/spring-boot-docker-time.jar"]
