# TPE ENTREGA 1

## Grupo 16

### Pasos a seguir para iniciar la aplicación.

1. Ejecutar ConfigServiceApplication
2. Ejecutar DiscoveryServerApplication
3. Ejecutar ApiGatewayApplication
4. Crear Connections con MongoDB Compass ( localhost:27017 )
5. Ejecutar el resto de los microservicios.

## PRIMERA ENTREGA

6. Crear cuenta de usuario con el método "Add account to user" (POSTMAN)
7. Iniciar viaje con el método "StarTrip" de la colección Microservicio_User (POSTMAN)
8. ( OPCIONAL ) pausar / despausar viaje en la colección Microservicio_User (POSTMAN)
9. Modificar Latitude y Longitude del scooter en viaje en la colección Microservicio_Scooter a la latitude y longitude de una parada válida ( Latitude: 200.0, Longitude: 200.0) (POSTMAN)
10. Terminar viaje con el método "EndTrip" de la colección Microservicio_User (POSTMAN)

## SEGUNDA ENTREGA

6. Registrar usuario mediante el método "Registrar usuario" de la colecction JWT en POSTMAN.
7. Loguearse mediante el username y password del registro ( paso anterior ).
8. Copiar y pegar el TOKEN de respuesta en la sección "Authorization" dentro de "Auth Type = Bearer Token".
9. Ejecutar los métodos que la clase "SecurityConfig" de api-gateway le permite mediante la autorización con la que se registró.

## Requerimientos TPE_ENTREGA_1

3. Implementar los siguientes servicios/reportes:

a) Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por
kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder
configurarse para incluir (o no) los tiempos de pausa.
- EL SCOOTER DEBE INCLUIR UN MÍNIMO DE 10KM RECORRIDOS Y UN TIEMPO DE USO DE 1 MINUTO, ( EL VIAJE DEBE DURAR MINIMO 1 MINUTO ).

b) Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la
misma.
- SELECCIONAR EL MÉTODO "AnnulledAccount" de la colección Microservice_Admins y revisar en la URL que tenga un ID account válido y el segundo parámetro en TRUE.

c) Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.
- SELECCIONAR EL MÉTODO "ScootersCountTripForYear" de la colección Microservice_Admins verificando en el primer parámetro el año a buscar y en segundo la cantidad de viajes.

d) Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
- SELECCIONAR EL MÉTODO "Billing for year" de la colección Microservice_Admins verificando en el primer parámetro el año a buscar y en segundo y tercero los meses.

e) Como administrador quiero consultar la cantidad de monopatines actualmente en operación,
versus la cantidad de monopatines actualmente en mantenimiento.
- SELECCIONAR EL MÉTODO "ScooterInOperation" de la colección Microservice_Admins.

f) Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema
habilite los nuevos precios.
- SELECCIONAR EL MÉTODO "UpdateRateByDate" de la colección Microservice_Admins envíandole en el body la fecha próxima de los nuevos precios.

g) Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar
un monopatín cerca de mi ubicación.
- SELECCIONAR EL MÉTODO "scooters nearby" de la colección Microservice_User envíandole en el primer parámetro el ID del USER y el segundo el RADIUS a buscar los monopatines.

## Requerimientos TPE_ENTREGA_2

5. Segurizar los endpoints REST con JWT.

En el microservicio "api-gateway" ingresar en src/main/java/com.api_gateway/Config/SecurityConfig y se podrán visualizar dentro del método "filterChain()" los endpoints segurizados con JWT.

6. Incorporar tests de unidad e integración (Junit o Mockito). Documentar los endpoints REST con
Swagger (OpenAPI).

Dentro de cada microservicio, en la carpeta "test" se verán los Test de unidad e integración que fueron realizados a algunos métodos.
Para la sección de Swagger (OpenAPI), dentro de cada Controller de los microservicios están las documentaciones a los endpoints.
Para acceder a la interfaz de Swagger se deberá ingresar en el siguiente link http://localhost:{PORT_EUREKA}/swagger-ui/index.html, donde PORT_EUREKA significa los puertos dinámicos de cada microservicio al momento de levantarlos.

7. Utilizar una base NoSQL (MongoDB), o bien implementar una comunicación vía protocolo gRPC entre
2 microservicios.

La base NoSQL (MongoDB) fue utilizada en el microservicio Parking y desplegada en "MongoDB Compass", para ello se debe crear la connection con la URI: localhost:27017.

8. (Opcional) Desplegar la aplicación mediante contenedores (Docker) en una nube.
