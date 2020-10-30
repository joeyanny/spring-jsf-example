# Spring and JSF Example Application
Web application written using Spring and JavaServer Faces (JSF) frameworks. The data is stored within an in-memory H2 database.

## Building application
Build the application using Maven:
```
mvn clean install
```

This will build a WAR (Java web application archive) and place the artifact in a `target` folder.

## Running application
Run the application using a web server, e.g. Tomcat

1. Download Apache Tomcat
2. Copy the built WAR file from the target directory into [Tomcat install]/webapps
3. Start Tomcat using `[Tomcat install]/bin/startup.sh` or `[Tomcat install]/bin/startup.bat`.
4. Open a browser and navigate to `http://localhost:8080/spring-jsf/userlistview.jsf`