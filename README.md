To clear existing jar & Class file of the project, also check for compilation error in the project.
```mvn clean compile```

To create updated bundle/jar & also download the dependencies in the project.
```mvn clean compile assembly:single```

In order to execute in any system 
```java -jar yash-pdf-splitter-1.0-jar-with-dependencies.jar ```