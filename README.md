To clear existing jar & Class file of the project, also check for compilation error in the project.
```mvn clean compile```

To create updated bundle/jar & also download the dependencies in the project.
```mvn clean compile assembly:single```

In order to run the jar please use below command to split a given pdf-
``` java -jar .\target\yash-pdf-splitter-1.0-jar-with-dependencies.jar```

In order to execute in any other system 
```java -jar yash-pdf-splitter-1.0-jar-with-dependencies.jar ```