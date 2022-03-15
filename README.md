# Detector de Mutantes:tm:

Proyecto que detecta si un humano es mutante basándose en su secuencia de ADN.

## Problema

Se requiere una aplicacion que sea capaz de detectar si un humano es o no un mutante basandose en su cadena de ADN, se recibira una como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

### Ejemplo

#### :man: No-Mutante

| A   | T   | G   | C   | G   | A   |
| --- | --- | --- | --- | --- | --- |
| C   | A   | G   | T   | G   | C   |
| T   | T   | A   | T   | T   | T   |
| A   | G   | A   | C   | G   | G   |
| G   | C   | G   | T   | C   | A   |
| T   | C   | A   | C   | T   | G   |

#### :alien: Mutante

| A   | T   | G   | C   | G   | A   |
| --- | --- | --- | --- | --- | --- |
| C   | A   | G   | T   | G   | C   |
| T   | T   | A   | T   | G   | T   |
| A   | G   | A   | A   | G   | G   |
| C   | C   | C   | C   | T   | A   |
| T   | C   | A   | C   | T   | G   |

Para saber si un humano es mutante es porque se encuentra una secuencia de cuatro letras iguales​, de forma oblicua, horizontal o vertical.

## Despliegue

### Local 

Usted puede desplegar esta aplicacion con el plugin de [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html): 

```shell
cd ./mutants-services/
mvn spring-boot:run
```

Alternativamente usted puede usar el despliegue de :whale2: [Docker](https://docs.docker.com/engine/reference/commandline/run/)

```shell
cd ./mutants-services/
docker build -t mutants-services .
docker run -p 8080:8080 -d --name mutants-services mutants-services
```
