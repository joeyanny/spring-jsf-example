# spring-jsf-example
Example project to show how to implement an application using Spring, JSF web application framework and in-memory H2 database.

## Building application
Build the application using Maven:

```
mvn clean install
```

This will create a Java web application (WAR) artifact within the `target` folder.

**Note:** The `groupId` field in the `pom.xml` will need to be populated for the build to be successful.

## Running application
The application is run using Jetty servlet container.

```
mvn jetty:run
```

Open a web browser and navigate to the following URL to view the web screens.

```
http://localhost:8080/users
```

## Internationalisation
The locale is defaulted to the user's locale in the session. To change locale append the name of the locale to the URL as a query parameter.

```
http://localhost:8082/users?locale=[locale]
```

This will load the matching `message_[locale].properties` from the resources folder.