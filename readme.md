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

![img](/doc/iniciarApp.png "...")

cuando trates de ejecutarla, fallara, 

el problema sera:

```bash	
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
```

Esto debido a que no se ha configurado la base de datos. Por ahora se comentara del pom.xml la dependencia **spring-boot-starter-data-jpa**

con esto al ir a http://localhost:8080/
nos mostrara un error el tomcat de que no hay ningun end point.

## crear una api

Para hacer una api rest basta con anotar a la clase con **@RestController**, y a sus metodos con **@GetMapping**, para que el metodo acepte peticiones Get. Tambien existe **PostMapping**, **PutMapping**, **DeleteMapping**.

### nota:
si retornamos un array, le agrega los corchetes.
y lo devuleve como Content-Type: application/json


si creamos una clase Student con propiedades, constructor, y getters y setters,
y retornamos una lista de ellos nos devolvera los objetos en formato json.

La arquitectura que adoptaremos tendra una capa api, una capa de servicio y una capa de acceso a datos.

![img](/doc/arquitectura.png "...")


Para eso crearemos una carpeta student que tendra el controlador que es la api StudentController.
el servicio que tendra la logica de negocio StudentService.
y la capa de acceso a datos la agregaremos despues, mientras tanto tendremos la Entidad Student que sera la clase que representara la tabla student. 

## aplicando la arquitectura con inyeccion de dependencias.

como nuestro controlador esta en la capa api, para proveer el servicio necesitamos una dependencia esta seria StudentService, normalmente lo que hariamos seria instanciar el objeto en el constructor y luego usarlo. Pero lo que hace spring boot es que nos inyecta el las dependencias.

La forma de decirle a Spring Boot, que nos inyecte la dependencia por el constructor es con la anotacion **@Autowired**, pero tambien tenemos que usar otra anotacion para aquella clase que sirve de dependencia.
Esto se hace con la anotacion **@Component**, aunque existen otras anotaciones que hacen lo mismo pero son ma explicitas en cuanto a que tipo de dependencia es, en este caso es mejor usar **@Service**.

## properties files para acceder a postgres
El archivo que tiene la configuracion para poder conectarnos a la db debe guardarse en:
```bash
\SpringBootAmigosCode\src\main\resources\application.properties
```
los datos con respecto a la db se deberian sacar de .envs/.postgres

## Nota importante sobre postgres
utiliza otro puerto al por defecto a la hora de redireccionar desde docker, windows puede fallar silenciosamente, si te falla la conexion lo primero que debes probar es usar otro puerto.

## creando db postgres con docker.

la db y su persistencia en docker queda definida por el docker-compose.yml

para construir el proyecto, (o actualizarlo, en caso de modificar algo de sus layer):

```bash
docker-compose -f docker-compose.yml up --build
```

para ejecutarlo:

```bash
docker-compose -f docker-compose.yml up
```

pararlo:
    
```bash
docker-compose -f docker-compose.yml down
```

## entrar a ver dockers corriendo con compose

para ver dentro que tiene un contenedor iniciado con docker, abrir una terminal aparte y ejecutar

```bash
docker-compose run --rm nombre_del_servicio sh
```

sino ando eso probar
obtener el id con

```bash
docker ps
```

y luego ejecutar

```bash
docker exec -it id_obtenido_por_dockerps sh
```

### **Entrar a postgres**

```bash
docker exec -it  <container-name> psql -U <dataBaseUserName> <dataBaseName>
```
por ejemplo:

```bash
docker exec -it  amigoscode-postgres psql -U braian student
```

### permitir conexiones externas postgres

Si quisieramos entrar desde una ip externa a la db, no podriamos obtendriamos un error de que la "password" esta mal, esto lo podemos ver al intentar correr spring boot, o entrar desde DBeaver.

tenemos que cambiar la configuracion de la db. para eso:

```bash
docker exec -it  amigoscode-postgres bash
find / -name pg_hba.conf
cd <direccion comando anterior>
#por defecto es:
cd /var/lib/postgresql/data/
```
ahora que sabemos donde el archivo, tenemos que copiarlo desde el contenedor hacia afuera para editarlo.
para eso salimos de la terminal del contenedor y en la nuestra ejecutamos:
    
```bash
docker cp amigoscode-postgres:/var/lib/postgresql/data/pg_hba.conf .
```

luego dentro del archivo escribimos esto:
```bash
#braian conf, permitir acceder desde cualquier host, con password.
host    all             all                 *                   md5
```
guardamos y movemos el archivo al cotenedor de vuelta:
```bash
docker cp pg_hba.conf amigoscode-postgres:/var/lib/postgresql/data/
```
luego paramos el docker-compose, y volvemos a iniciar.

# conectandonos desde spring boot

ahora si ejecuta el comando de construccion, deberia generar la db de forma correcta.

para acceder desde spring, necesitamos descomentar la dependencia **spring-boot-starter-data-jpa** en el pom.xml