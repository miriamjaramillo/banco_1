#La línea de abajo indica que basaremos nuestra imagen  
FROM openjdk:11

#Identificar al mantenedor de una imagen
MAINTAINER miriameje@gmail.com

VOLUME /tmp

EXPOSE 8083

COPY docker_file/jar/ec.mjaramillo.movimiento-0.0.1-SNAPSHOT.jar movimientomjaramillo.jar
ENTRYPOINT ["java", "-jar", "movimientomjaramillo.jar"]
