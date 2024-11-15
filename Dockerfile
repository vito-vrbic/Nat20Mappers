FROM openjdk:21-jdk

WORKDIR /user/src/app

COPY . .

EXPOSE 5000

ENTRYPOINT [ "java", "-jar", "src/TTRPGBE-1/target/TTRPGBE-1-0.0.1-SNAPSHOT.jar" ]