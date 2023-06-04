FROM maven:latest as build

# Setting up work directory
WORKDIR /build

# Copy the pom file into our build
COPY ./pom.xml .

# Get dependency
RUN mvn dependency:go-offline

# Copy the source code into our build
COPY ./src ./src

# Compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true

# Run stage
# Fetching 17 version of Java
FROM openjdk:17

# Setting up work directory
WORKDIR /app

# Copy the jar file from build stage into our app
COPY --from=build /build/target/address-correction.jar /app

# Starting the application
CMD ["java", "-jar", "/app/address-correction.jar"]
