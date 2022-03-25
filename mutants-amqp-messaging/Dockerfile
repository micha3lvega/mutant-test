FROM openjdk:11

# Establecer directorio de trabajo
VOLUME [ "/tmp" ]

# Exponer puerto
EXPOSE 8082

# Copiar jar al contenedor
COPY ./target/mutants-amqp-messaging-1.0.0.jar ./

# Run container
ENTRYPOINT ["java","-jar","/mutants-amqp-messaging-1.0.0.jar"]