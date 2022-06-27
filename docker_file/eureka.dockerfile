#La línea de abajo indica que basaremos nuestra imagen  
FROM openjdk:11

#Identificar al mantenedor de una imagen
MAINTAINER miriameje@gmail.com

VOLUME /tmp

EXPOSE 8761

COPY docker_file/jar/ec.mjaramillo.eureka-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
