# Tasks
Proyecto para construir un ejecutable usando GraalVM
Se trata de un proyecto de webservice que expone un endpoint para crear tareas y otro para recuperarlas.
También cuenta con un servicio que ante una petición ping responde pong
POST /tasks/{task}
GET /tasks/{id}
GET /tasks/ping

Donde se define Task como (Long id, String title, String description)

# Ejecutando usando un jar

## Construcción del jar
./mvnw clean package
Esto deja un jar ejecutable en la carpeta target

## Ejecutar el jar
java -jar target/graal-tasks-0.0.1-SNAPSHOT.jar
Esto levanta un microservicio en el puerto 8080

al cual se puede acceder con

curl http://localhost:8080/tasks/ping

La aplicación arranca en 1.2 segundos

# Construyendo un ejecutable nativo

## Usando Maven
./mvnw -Pnative spring-boot:build-image

Esto genera una imagen en 

docker.io/library/graal-tasks:0.0.1-SNAPSHOT 

que se puede ejecutar con

docker run --rm -p 8080:8080 docker.io/library/graal-tasks:0.0.1-SNAPSHOT

Esto levanta un microservicio en el puerto 8080 al cual se puede acceder usando

curl http://localhost:8080/tasks/ping

## Usando Native Build Tools
./mvnw -Pnative native:compile

Esto produce un ejecutable en la carpeta target que podemos ejecutar con
target/graal-tasks

Esto levanta un microservicio en el puerto 8080 al cual se puede acceder con
curl http://localhost:8080/tasks/ping

La aplicación arranca en 0.03 segundos, que es un tiempo drásticamente menor a la versión con jar ejecutable.


# Construyendo contenedores con Docker

## Contenedor para el Jar

Podemos crear un contenedor Docker para el jar generado en primer término usando el comando

docker build -f Dockerfile.jvm --build-arg APP_FILE=graal-tasks-0.0.1-SNAPSHOT.jar -t graal-tasks:jvm1 .

Esto genera una imagen de aprox 186MB
Se puede ejecutar con

docker run --rm --name graal -p 8080:8080 graal-tasks:jvm1

que arranca en 1.1 segundos aprox, y consume 110MB de memoria y 0,15% de CPU  

## Contenedor para el ejecutable (Linux)
En Linux podemos crear un contenedor para el ejecutable creado en pasos previos con el comando:

docker build -f Dockerfile.native --build-arg APP_FILE=graal-tasks -t graal-tasks:native1 .

Esto genera un contenedor de aprox. 100 MB, que se puede ejecutar con

docker run --rm --name native -p 8080:8080 graal-tasks:native1

Arranca en 0.03 segundos aprox., un tiempo mucho menor que el del contenedor para jar.
Además consume 26MB de memoria y 0,02% de CPU, mucho menos que el contenedor con jar.

## Contenedor para el ejecutable (Windows y Mac)

Podemos crear un contenedor para el ejecutable creado en pasos previos con el comando:

docker build -f Dockerfile --build-arg APP_FILE=graal-tasks -t graal-tasks:native2 .

Esto genera una imagen de aprox. 100MB, que se puede ejecutar con

docker run --rm --name native2 -p 8080:8080 graal-tasks:native2

que arranca en 0.02 segundos aprox., un tiempo mucho menor que el del contenedor para jar.
Además consume cerca de 16MB de memoria y 0,02% de CPU, mucho menos que el contenedor con jar.
 

### Referencias
Para más información se pueden consultar las siguientes referencias:
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html)
* [GraalVM Demos - Spring Native Image](https://github.com/graalvm/graalvm-demos/tree/master/spring-native-image)


