# TPE ENTREGA 1

## Grupo 16

### Pasos a seguir para iniciar la aplicación.

1. Ejecutar DiscoveryServer
2. Ejecutar ApiGateway
3. Ejecutar el resto de los microservicios.
4. Crear cuenta de usuario con el método "Add account to user" (POSTMAN)
5. Iniciar viaje con el método "StarTrip" de la colección Microservicio_User (POSTMAN)
6. ( OPCIONAL ) pausar / despausar viaje en la colección Microservicio_User (POSTMAN)
7. Modificar Latitude y Longitude del scooter en viaje en la colección Microservicio_Scooter a la latitude y longitude de una parada válida ( Latitude: 200.0, Longitude: 200.0) (POSTMAN)
8. Terminar viaje con el método "EndTrip" de la colección Microservicio_User (POSTMAN)

## Requerimientos TPE_ENTREGA_1

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
