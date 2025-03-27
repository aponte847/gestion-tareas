# Reto Edwin Aponte

## Requisitos para correr el repo:
- Tener java 17
- Tener Intellij u otro IDE
- Tener Postman para realizar las peticiones

## Pasos para correr el repo:
- Clonar el repo
- Abrir el proyecto en Intellij
- Configurar el java 17 en file > project structure > SDK > 17
- Correr el proyecto

## Datos de la base de datos:
- Se utilizo una base de datos H2 en memoria
- Se crean las tablas al iniciar la aplicacion por medio de un archivo schema.sql
- Se puede acceder a la consola de H2 en: ```http://localhost:8080/h2-console``` con los siguientes datos:
```
  JDBC URL: jdbc:h2:mem:taskdb
  Driver Class: org.h2.Driver
  User Name: sa
  Password:
```

## Datos precargados:
- Se precargo 1 usuario:
```json
      {
        "username": "admin",
        "password": "admin123",
        "role": "ADMIN"
      }
```
      

El usuario admin se crea o precarga una vez se inicia la aplicacion desde el archivo GestiontareasApplication


- Se precargo 3 Status
```sql
    INSERT INTO task_status (id, name) VALUES (1, 'Pendiente');
    INSERT INTO task_status (id, name) VALUES (2, 'En Progreso');
    INSERT INTO task_status (id, name) VALUES (3, 'Completada');
```

Los datos de los status se crean o precargan una vez se inicia la aplicacion por medio de un archivo data.sql

## Como realizar las peticiones desde postman
- Importar la collecion de postman desde: resources/postman-collection/retos.postman_collection.json
- Dentro de la coleccion van a encontrar la carpeta Reto:
  - Dentro de la carpeta Reto van a existir dos carpetas y dos endpoints 
    - Task:
      - Get-All
      - Get-By-ID
      - Create
      - Update
      - Delete
    - User
      - Get-All
      - Get-By-ID
      - Create
      - Update
      - Delete
    - POST Auth
    - GET Health
    - Como realizar una peticion:
      - ir Auth y realizar la peticion POST Auth con el body, ya que es el usuario precargado:
        ```json
        {
          "username": "admin",
          "password": "admin123"
        }
        ```
      - Una vez realizada la peticion, copiar el token que se genera en el body de la respuesta
      - Ir a la carpeta Task y realizar la peticion deseada, en el header agregar el token generado en el paso anterior en el campo Authorization
      - dentro de Authorization en Auth Type seleccionar Bearer Token y pegar el token generado en el paso anterior
      - Esto aplica para todos los endpoints menos para Health
      - Health se utiliza para verificar que la aplicacion esta corriendo correctamente.

## Como realizar las peticiones desde SWAGGER
- Una vez tengas el repo corriendo en:
```http://localhost:8080/swagger-ui/index.html``` puedes realizar las peticiones desde ahi
- Para realizar las peticiones debes autenticarte en el endpoint POST Auth y copiar el token generado en el body de la respuesta
- Una vez tengas el token debes ir a Authorize y pegar el token generado en el campo Value y darle click en Authorize
- Una vez autorizado puedes realizar las peticiones en los diferentes endpoints:
## Task:
  - Get-All
    URL: ```localhost:8080/api/tasks```
  - Get-By-ID
    URL: con el id de la tarea que se desea consultar
    ```localhost:8080/api/tasks/1```
  - Create
    Boby: con en cual se crea una tarea
      ```json
        {
          "title": "Implementar TaskController",
          "description": "Modificado",
          "statusId": 1,
          "userId": 1
        }
      ```
  - Update
    URL: con el id de la tarea que se desea actualizar
    ```localhost:8080/api/tasks/1```
    Body: con el cual se actualiza una tarea
      ```json
        {
          "title": "Implementar TaskController mas correcciones",
          "description": "Modificado de nuevo",
          "statusId": 2,
          "userId": 1
        }
      ```
  - Delete
    URL: con el id de la tarea que se desea eliminar
    ```localhost:8080/api/tasks/1```
## User
  - Get-All
    URL: ```localhost:8080/api/users```
  - Get-By-ID
    URL: con el id del usuario que se desea consultar
    ```localhost:8080/api/users/1```
  - Create
    Boby: con en cual se crea un usuario
      ```json
        {
          "username": "user1",
          "password": "user123",
          "role": "USER"
        }
      ```
  - Update
    URL: con el id del usuario que se desea actualizar
    ```localhost:8080/api/users/1```
    Body: con el cual se actualiza un usuario
      ```json
        {
          "username": "user1",
          "password": "user123",
          "role": "ADMIN"
        }
      ```
  - Delete
    URL: con el id del usuario que se desea eliminar
    ```localhost:8080/api/users/1```

## El repositorio cuenta con Swagger, sin Auth el cual puedes acceder una vez tengas el repo corriendo en:
```http://localhost:8080/swagger-ui/index.html```
y en
```http://localhost:8080/v3/api-docs```

