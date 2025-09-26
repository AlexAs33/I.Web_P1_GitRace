# Lab 1 Git Race -- Project Report

## Description of Changes
Durante esta práctica he desarrollado una aplicación web en Kotlin con Spring Boot que incluye un saludo personalizado y funcionalidades avanzadas. En primer lugar implementé en el `welcome.html` un saludo personalizado en función de la hora. Después añadí un filtro de limitación de peticiones usando la libreria BUcket4j en el fichero `RateLimitFilter`, que restringe a un número configurable de solicitudes por cliente y añade una cabecera con el número de peticioes restantes.

También configuré el sistema de loggin para todos los controladores y el filtro utilicen SLF4J con argumentos estructurados y Logback. Gracias a esta configuración todos los mensajes de log se generan en formato JSON estructurado, adecuado para entornos Docker.

Para comprobar todo esto diseñé test para comprobar que el filtro funcionase perfectamente.

## Technical Decisions
Para la limitación de peticiones opte por Bucket4j porque es una solución sencilla y probada en proyectos Spring Boot. Para identificar a los clientes elegí sudirección IP (`request.remoteAddr`), que es lo más directo tal y como estaba montado el repositorio.

Para el logging estructurado utilicé la librería `logstash-logback-encoder` en colaboración con SLF4J 2, que permite añadir campos clave/valor a los mensajes. Configuré Logback para que emita los logs en JSON a la salida estándar, siguiendo las buenas prácticas de los contenedores de Docker.

## Learning Outcomes
Con esta práctica he aprendido a crear y registrar filtros en Spring Boot con Kotlin, a inyectar propiedades desde `application.properties` con @Value y a implementar un sistema de rate limiting configurable. También he aprendido a configurar Logback para generar logs en formato JSON y a usar los argumentos estructurados de SLF4J para incluir campos personalizados.

Además he ganado experiencia trabajando en Codespaces y gestionando ramas y commits en GitHub siguiendo la buenas prácticas.

## AI Disclosure
### AI Tools Used
- ChatGPT

### AI-Assisted Work
ChatGPT me proporcionó la base del código para los test. Además de información sobre SLF4J y Bucket4j

### Original Work
A partir de la base que me dio ChatGPT realicé investigaciones en internet por lo cual comprendo perfectamente el código y he aprendido lo dicho en Learning Outcomes