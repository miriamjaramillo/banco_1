#La línea de abajo indica que basaremos nuestra imagen  
FROM openjdk:11

#Identificar al mantenedor de una imagen
MAINTAINER miriameje@gmail.com

VOLUME /tmp

EXPOSE 8082

COPY docker_file/jar/mjaramillo.cuenta.ec-0.0.1-SNAPSHOT.jar cuentamjaramillo.jar
ENTRYPOINT ["java", "-jar", "cuentamjaramillo.jar"]
