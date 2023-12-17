# Rick And Morty

![image](https://github.com/Deimer/RickAndMorty/assets/5855547/59a55129-71ba-4288-a8d5-bb086425c938)


Aplicación para preuba técnica - Documentación

Esta aplicación se creó como parte de un desafío y cumple con los siguientes requisitos:

## Funcionalidades Principales
- Listado de Personajes
- Detalles de Personajes
- Arquitectura
- Diseño UX/UI (Dark Mode)
- Repositorio y Documentación


## Funcionalidades Extras
- Caché de Imágenes
- Caché de Respuestas
- Gestión de Errores
- Testing
- Búsqueda y Filtros


## Arquitectura
La implementación de la arquitectura Clean Architecture junto con el patrón de diseño MVVM ofrece diversas ventajas que contribuyen a un desarrollo de software eficiente. Algunas de estas ventajas son:

- **Separación de Preocupaciones:** La combinación de Clean Architecture y MVVM facilita la separación clara de responsabilidades, resultando en un código más organizado y comprensible.
- **Colaboración en Equipo:** Proporciona un marco de trabajo que favorece la colaboración efectiva entre miembros del equipo, ya que cada capa tiene funciones específicas y bien definidas.
- **Pruebas Unitarias Sencillas:** El patrón de diseño MVVM posibilita la realización de pruebas unitarias de manera más sencilla, ya que la lógica de presentación y la lógica de negocio están separadas.
- **Mantenibilidad y Escalabilidad:** Contribuye a mejorar la mantenibilidad del código a largo plazo y permite una fácil escalabilidad del sistema a medida que evolucionan los requisitos del proyecto.

## Porqué Clean Architecture
Clean Architecture es un nombre popularizado por Robert Cecil Martin, conocido como “Uncle Bob” que se basa en la premisa de estructurar el código en capas contiguas, es decir, que solo tienen comunicación con las capas que están inmediatamente a sus lados.
Basados en esta idea podemos decir que cada nivel debe realizar sus propias tareas y se comunica únicamente con sus niveles inmediatamente contiguos.
Los niveles de los que se compone Clean Architecture son los siguientes:

- **UI:** La interfaz de usuario propiamente dicha (html, xml, etc).
- **Presenters:** Clases que se subscriben a los eventos generados por la interfaz y que responden en consecuencia, también realizan el pintado de la información en la interfaz.
- **Use Cases:** Evaluación de reglas de negocio y tomas de decisiones.
- **Entities:** Modelo de datos de la aplicación, comunicación con servicios web, cache de datos.

![image](https://github.com/Deimer/RickAndMorty/assets/5855547/037d9be8-e68d-4b31-97dd-483da2c66559)


## Modularización de la Aplicación
Tomando en cuenta el concepto de Clean Architecture, la aplicación se ha modularizado, lo que significa que cada capa mencionada a continuación se encuentra en su propio módulo de Android.
La modularización se utiliza para dividir la aplicación en capas con responsabilidades específicas:

1. **Network:** Esta capa se encarga de todas las operaciones relacionadas con la red, como la comunicación con un servidor o una API.
2. **Database:** Aquí se maneja la interacción con la base de datos, que en este caso es Room Database. Se Almacenan los datos de los personajes de Rick And Morty.
3. **Datasource:** La capa Datasource se encarga de la obtención y manipulación de datos crudos desde la fuente de datos, que puede ser una API, una base de datos o cualquier otra fuente de datos. Esta capa proporciona datos al Repositorio.
4. **Repository:** El repositorio actúa como una capa intermedia que abstrae el origen de datos (Datasource) y proporciona datos a la capa de UseCase.
5. **UseCase:** Contiene la lógica de negocio de la aplicación. Aquí se definen los casos de uso, como la obtención de los personajes, el buscar personaje por ID o nombre y la gestión de cada personaje en la caché del aplicativo.
6. **App:** La capa de la aplicación se encarga de la navegación y la configuración global de la aplicación además de manejar la interfaz de usuario y la presentación de datos. Los ViewModels (ViewModels en el patrón MVVM) se utilizan para gestionar la lógica de la interfaz de usuario..

![Captura de pantalla 2023-12-17 a la(s) 3 14 22 p m](https://github.com/Deimer/RickAndMorty/assets/5855547/f354e361-4a3b-4de6-bd98-4b555743629f)

La modularización se realiza por las siguientes razones:

- **Separación de Responsabilidades:** Cada módulo se enfoca en una responsabilidad específica, lo que facilita el mantenimiento y la evolución de la aplicación.
- **Reutilización de Código:** Los módulos pueden ser reutilizados en otros proyectos si es necesario.
- **Mejora de la Compilación:** La modularización permite compilar solo los módulos necesarios, lo que acelera el proceso de compilación y desarrollo.
- **Colaboración en Equipo:** Diferentes equipos pueden trabajar en módulos separados de manera independiente, lo que mejora la colaboración.
- **Escalabilidad:** La modularización facilita la adición de nuevas funcionalidades o características a la aplicación.

## Roles de Cada Capa
- **Network:** Comunicación con un servidor remoto para obtener los datos de los personajes desde una API ajena al desarrollo (https://rickandmortyapi.com/documentation).
- **Database:** Almacenamiento de datos de los personajes y su información completa en Room Database.
- **Repository:** Abstracción de los orígenes de datos, proporcionando datos a las capas superiores.
- **UseCase:** Lógica de negocio para la consulta de los personajes de forma remota y luego de forma local.
- **App:** Configuración global de la aplicación y navegación entre pantallas con la interfaz de usuario, incluida la presentación de datos.

## Comunicación entre Capas
Para la comunicación entre las diferentes capas, se utilizaron las corrutinas de Kotlin. Esto nos permite realizar funciones asincrónicas de manera eficiente y fácil.

## Uso de SOLID y Clean Code
Durante el desarrollo de la aplicación, se siguieron los principios de SOLID y Clean Code para garantizar un código limpio, organizado y mantenible. Esto incluye prácticas como la separación de responsabilidades y la escritura de código legible.

## Navigation Component y Patrón de "One Single Activity"
La aplicación utiliza Navigation Component de Android para gestionar la navegación entre pantallas. Además, sigue el patrón de "One Single Activity," lo que significa que toda la navegación se realiza dentro de una única actividad principal. Esto mejora la eficiencia y la gestión de la navegación.

![Captura de pantalla 2023-12-17 a la(s) 3 43 04 p m](https://github.com/Deimer/RickAndMorty/assets/5855547/d0810cdd-5ee6-4fb1-80cf-8c3d5973ccef)

¡Gracias por revisar esta documentación y por la oportunidad de desarrollar esta aplicación!

