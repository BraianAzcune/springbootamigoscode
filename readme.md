# tuturial del canal amigoscode
https://www.youtube.com/watch?v=9SGDpanrc8U&ab_channel=Amigoscode 

# preparacion.

para poder correr el proyecto primero necesitamos tener el jdk 18 de java.
se instala dede aqui:

https://www.oracle.com/java/technologies/downloads/

para Windows elegir la version MSI. 

luego comprobamos que esta instalado con:
```bash
java -version
```
debe entregar un output tal que:
```bash
java version "18" 2022-03-22
Java(TM) SE Runtime Environment (build 18+36-2087)
Java HotSpot(TM) 64-Bit Server VM (build 18+36-2087, mixed mode, sharing)
```

luego para que el proyecto funcione bien en VSCODE hay que instalar estos dos extensions packs:

```bash
vscjava.vscode-java-pack
Pivotal.vscode-boot-dev-pack
```

Para crear un proyecto con todas las dependencias usar la web:

https://start.spring.io/

## Iniciar aplicacion:

basta con hacer click al boton:

![img](/doc/iniciarApp.png "...").

cuando trates de ejecutarla, fallara, 

el problema sera:

```bash	
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
```

Esto debido a que no se ha configurado la base de datos. Por ahora se comentara del pom.xml la dependencia **spring-boot-starter-data-jpa**

con esto al ir a http://localhost:8080/
nos mostrara un error el tomcat de que no hay ningun end point.

## crear una api




## La base de datos postgres, esta en docker.