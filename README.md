# Kibysz Aaron - Mutant Detection API

El proyecto presentado es una API para detectar si un humano es mutante basado en su secuencia de ADN. Esta recibe matrices de ADN mediante HTTP POST y devuelve un booleano definiendo si la matriz marca si es mutante o no. Este ademas guarda estos registros en una base de datos.

## Despliegue
Proyecto alojado en render

https://mutantesparcial.onrender.com

Ademas con el siguiente link se puede acceder a la Base de Datos de la Memoria de H2

http://localhost:8080/h2-console/

## Funcionamiento del Programa

La API utiliza el servicio con el cual recibe parametros en forma de String[] y este los analiza para comparar con las condiciones que se necesitan para ser o no clasificado como mutantes

### 1. Detección de mutantes

Debe ingresarse en formato JSON un Array de Strings que obligatoramente se debe cumplir que sea de tamaño NxN, ya que sino el programa no lo analizara.

#### Solicitud:

  {
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
  }

**Respuesta**:
**200 OK** si el ADN corresponde a un mutante.
**403 Forbidden** si el ADN no corresponde a un mutante.

