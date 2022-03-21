FROM openjdk:11

# Establecer directorio de trabajo
VOLUME [ "/tmp" ]

# Exponer puerto
EXPOSE 8080

# Copiar jar al contenedor
COPY ./target/mutants-services-1.0.0.jar ./

# Run container
ENTRYPOINT ["java","-jar","/mutants-services-1.0.0.jar"]