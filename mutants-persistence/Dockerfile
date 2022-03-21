FROM openjdk:11

# Establecer directorio de trabajo
VOLUME [ "/tmp" ]

# Exponer puerto
EXPOSE 8081

# Copiar jar al contenedor
COPY ./target/mutants-persistence-1.0.0.jar ./

# Run container
ENTRYPOINT ["java","-jar","/mutants-persistence-1.0.0.jar"]