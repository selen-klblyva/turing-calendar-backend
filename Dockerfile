FROM openjdk:17
COPY build/libs/turing-calendar-backend-0.0.1-SNAPSHOT.jar turing-calendar-backend.jar
CMD ["java", "-jar", "turing-calendar-backend.jar"]



