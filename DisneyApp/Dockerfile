#  imagen modelo
FROM eclipse-temurin:24.0.2_12-jdk

#informar puerto donde se ejecuta el puerto del contenedor (INFORMATIVOL)
EXPOSE 8080


#  definir directorio raiz del docker:
WORKDIR /root

#     COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
#pom
COPY ./pom.xml /root

#pasar mvn(enbebido springboot)
copy ./.mvn /root/.mvn
copy ./mvnw /root

#descargar dependencias
RUN ./mvnw dependency:go-offline

#copiar codigofunente dentro del conendor
COPY ./src /root/src

#construir la app

RUN ./mvnw clean install -DskipTests

#lebantar la app cuando el contenedor inicie
ENTRYPOINT ["java","-jar","/root/target/DisneyApp-0.0.1-SNAPSHOT.jar"]
