FROM openjdk:17
ADD target/calc-0.0.1-SNAPSHOT.jar calc.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar","calc.jar" ]