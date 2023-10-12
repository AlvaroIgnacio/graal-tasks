# Using Oracle GraalVM for JDK 17
FROM container-registry.oracle.com/graalvm/native-image:17-ol8 AS builder

WORKDIR /build

# Copy the source code into the image for building
COPY . /build

# Build
RUN ./mvnw --no-transfer-progress native:compile -Pnative

# The deployment Image
#FROM container-registry.oracle.com/os/oraclelinux:8-slim
FROM seepine/alpine-glibc

EXPOSE 8080
ARG APP_FILE

# Copy the native executable into the containers
COPY --from=builder /build/target/${APP_FILE} app
ENTRYPOINT ["/app"]
