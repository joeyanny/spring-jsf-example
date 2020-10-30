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
### Apache Tomcat
1. Download Apache Tomcat.
2. Copy the WAR from the target folder into `[Tomcat install]/webapps` folder.
3. Start Tomcat using the start script in `[Tomcat install]/bin` folder.
4. Open a browser and navigate to `http://localhost:8080/spring-jsf/userlistview.jsf`