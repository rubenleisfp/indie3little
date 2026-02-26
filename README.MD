# Objetivo

Vamos a realizar la implementación de un API Rest para un una tienda online llamada Indi3.

Todo lo necesario para llevar a cabo este objetivo está dentro de este proyecto.
Se proporciona una maqueta base con una serie de clases que el alumno
deberá completar en la mayoría de los casos. El profesor provee firmas y en ciertos casos atributos de clase. 
El alumno por lo tanto deberá completar  esta maqueta para cumplir con los requisitos proporcionados en este texto y
a través de SWAGGER, para completar el API solicitado. 

El alumno por lo tanto, SOLO deberá:


* Codificar los métodos ya existente
* Añadir anotaciones en las clases proveidas
* Añadir referencias a services en controllers y a repositories en services
* Configurar el application.properties

NO debe:

* Añadir nuevas clases
* Añadir nuevos métodos
* Modificar el POM.xml

Pasos a seguir:

* Incluir las anotaciones @Id y las que establecen las relaciones entre entidades
* Incluir las anotaciones en la capa de repositorio para permitir hacer las queries
* Incluir las anotaciones @Get @PostMapping en la capa del controlador
* Realizar las implementaciones necesarias en la capa de servicio o en el resto de metodos

Para no extendernos demasiado en la realización de este proyecto, vamos a 
dar soporte a las siguientes entidades involucradas (Categoría, Producto, Imagenes), con estos casos de uso:

* Gestión de categorías (CRUD): el alumno deberá implementar un API
* Gestión de productos/imagenes (CRUD): dentro de las categorías podemos crear y gestionar productos. Estos productos a su vez deben contener obligatoriamente
un thumbnail o imagen de previsualización. A mayores, pueden incluir otras imagenes para ver en el detalle. Todas las imagenes se deben subir a un alojamiento
web como es Cloudinary, de tal manera que en base de datos lo que almacenaremos realmente es su URL. También permitiremos búsqueda más avanzadas sobre los productos.
* Gestión de imagenes (CRUD): También podremos eliminar imagenes de un producto o añadir nuevas al mismo, eso sí, los productos solamente podrán tener thumbnail. Las imagenes las subiremos como se ha comentado a Cloudinary.

El API a crear será muy semejante al proporcionado por [dummyjson.com](https://dummyjson.com/docs/products)

Cuando realizamos una aplicación/API, debemos pensar en que funcionalidades vamos a realizar y como las presentaremos a nuestro usuario.

A partir de un boceto, podemos empezar a definir estas pantallas y las acciones
que se puede realizar en cada una de ellas.

Esta fase de diseño suele realizarse con wireframes anotados:

https://www.lucidchart.com/pages/es/que-es-un-wireframe-para-un-sitio-web



# Recursos

* Modelo de BBDD: (src/main/resources/design/database) está disponible el modelo relacional de MySQL con la base de datos con la que debe trabajar el alumno.
Como podrá ver hay más tablas de las requeridas, para que así se haga a la idea de como sería un modelo completo para realizar una tienda online. En este proyecto únicamente deberá trajar con las tablas de las entidades implicadas.

* Documentación de API: el alumno puede consultar en vivo al ejecutar el proyecto la documentación de SWAGGER, esta le indica que debe realizar en cada método del controlador, que casos debe gestionar y como debe gestionar el error.
Tiene esta URL en el apartado correspondiente de este mismo documento.

* Wireframe (src/main/resources/design/wireframe): el profesor ha hecho una serie de bocetos de pantallas. A partir de estos bocetos, se han visto las necesidades del usuario y se ha definido el API que debe completar el alumno.

* Para evitar en ciertas clases el tener que declarar getters and setters, usamos la librería Lombok.  Es necesario que instales el plugin en tu IDE tal y como se indica en este pagina. Revisa este pequeño tutorial dónde se explica como usarlo.

* Swagger_doc: documentación de Swagger 

https://www.youtube.com/watch?v=RB5y9omjlqE
https://www.baeldung.com/lombok-ide

# URLs

* Open API: http://localhost:8080/v3/api-docs 
* Swagger: http://localhost:8080/swagger-ui/index.html

