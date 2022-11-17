# curso-java-spring

### Generacion JAR

Ejecutar en Gradle la tarea build/bootJar <br>
El jar se genera en la carpeta build/libs

### Despliegue

En la terminal ejecutar el comando
```
java -jar "-Dspring.profiles.active=dev" build\libs\platzi-market-1.0.jar
```
