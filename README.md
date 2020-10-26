# ejercicio-Microservicios BeamarTest
Microservicios de entregas y planificación construidos con Spring Boot.

### Requerimientos para ejecución
- Java 8
- ActiveMQ 5.16.0
- PostgreSQL
- Maven

### ¿Pasos para ejecutar la aplicación?
 - Iniciar el broker de mensaje ActiveMQ con el comando.
```
$ activemq start
```
 - Crear las base de datos para cada servicio con los nombres `planificacion` y `entrega` respectivamente para cada servicio.
 
- Una vez que el broker esté funcionado se pueden ejecutar los 2 servicios.

- Una vez que los 2 servicios se ejecuten correctamente, el servicio de entregas se ejecutará en la url `http://localhost:8080` y el servicio de planificación en `http://localhost:8081`.
