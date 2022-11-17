# curso-java-spring

### Generacion JAR

Ejecutar en Gradle la tarea build/bootJar <br>
El jar se genera en la carpeta build/libs

### Despliegue

En la terminal ejecutar el comando
```
java -jar "-Dspring.profiles.active=dev" build\libs\platzi-market-1.0.jar
```

### Despliegue en heroku

* Instalar heroku cli
* Comprobar instalación de heroku
    ```
    heroku --version
    ```
* Iniciar sesion en heroku
    ```
    heroku login
    ```
* Crear proyecto en heroku
    ```
    heroku create platzi-market-ggalvisc
    ```
* Crear una base de datos Postgresql
    ```
    heroku addons:create heroku-postgresql
    ```
* Obtener conexion de la base de datos
    ```
    heroku config
    ```
* Modificar las propiedades de la aplicación utilizando las variables que proporciona Heroku
  * ${PORT} - Puerto donde despliega Heroku la aplicacion
  * ${SPRING_DATASOURCE_URL} - Url de la base de datos en Heroku
  * ${SPRING_DATASOURCE_USERNAME} - Usuario de la base de datos
  * ${SPRING_DATASOURCE_PASSWORD} - Contraseña de la base de datos
* Crear un archivo system.properties en la raiz del proyecto para indicar la version de Java a Heroku
    ```
    java.runtime.version=11
    ```
* Crear archivo Procfile que utiliza Heroku para desplegar la aplicación
    ```
    web: java -jar -Dspring.profiles.active=pdn build/libs/platzi-market-1.0.jar
    ```
* Subir proyecto a Heroku
  ```
  git push heroku HEAD:master
  ```