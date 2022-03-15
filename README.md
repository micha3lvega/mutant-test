[![Build Status](https://travis-ci.org/amcomaschi/mutants.svg?branch=master)](https://travis-ci.org/amcomaschi/mutants)
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

## REST API

El API Rest de este proyecto puede ser consumido de la siguiente manera:

### Saber si la cadena de ADN es :alien: mutante o no

#### Request
`POST /validator/api/v1/mutant/`

```shell
curl -X POST "https://mutant-application.herokuapp.com/validator/api/v1/mutant" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"
```

#### Response

Es un mutante

    HTTP/1.1 200 OK
    Status: 200 OK
    Connection: close
    Content-Length: 0

No es un mutante

    HTTP/1.1 400 OK
    Status: 400 OK
    Connection: close
    Content-Length: 0
    
## Postman


Se ha usado [Postman](https://learning.postman.com/) una plataforma colaborativa para la documentación de servicios REST para ayudar con la visualización de la forma de consumir este proyecto.

:link: Puede consumir los servicios de este proyecto a través del dashboard de postman ingresando [aquí](https://documenter.getpostman.com/view/4294452/UVsLQRUs)

![Postman-screenshot](https://github.com/micha3lvega/mutant-test/blob/main/files/images/Documentacion_postman.png)
