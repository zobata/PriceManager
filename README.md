# Servicio de Consulta de Precios

Este proyecto es una aplicación/servicio desarrollado en Spring Boot que provee un endpoint REST para consultar el precio final y la tarifa aplicable a un producto en una cadena específica en una fecha dada. Utiliza una base de datos en memoria (H2) inicializada con datos de ejemplo.

## Instalacion

1. Clona el repositorio:

    git clone https://github.com/zobata/PriceManager.git
    cd PriceManager

2. Construye el proyecto con Maven:

    mvn clean install

3. Ejecuta la aplicación:

    mvn spring-boot:run

## Uso

El endpoint REST de consulta acepta los siguientes parámetros de entrada:
- `fecha de aplicación` (formato: `yyyy-MM-dd'T'HH:mm:ss`)
- `identificador de producto`
- `identificador de cadena`

Devuelve los siguientes datos de salida:
- `identificador de producto`
- `identificador de cadena`
- `tarifa a aplicar`
- `fechas de aplicación`
- `precio final a aplicar`

### Ejemplo de Consulta

Realiza una petición GET a la URL del endpoint con los parámetros adecuados:

```bash
curl -X GET "http://localhost:8080/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
```

### Ejemplo de Respuesta

```json
{
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
}
```

## Autores

- [zobata](https://github.com/zobata)

## Arquitectura

Este proyecto utiliza una arquitectura en capas, organizada de la siguiente manera:

- **Controller**: Maneja las solicitudes HTTP y devuelve las respuestas HTTP.
    - Ubicación: `src/main/java/com/bcnc/pricemanager/controller`
- **Model**: Contiene las clases de dominio que representan los datos y las entidades del sistema.
    - Ubicación: `src/main/java/com/bcnc/pricemanager/model`
- **Repository**: Interactúa con la base de datos para realizar operaciones CRUD.
    - Ubicación: `src/main/java/com/bcnc/pricemanager/repository`
- **Service**: Contiene la lógica de negocio y coordina la interacción entre el controlador y el repositorio.
    - Ubicación: `src/main/java/com/bcnc/pricemanager/service`

Cada capa tiene una responsabilidad específica, lo que facilita el mantenimiento y la escalabilidad del código.

## Modelo de Datos

Para mejorar la normalización y escalabilidad de la base de datos, el modelo de datos se ha dividido en cuatro tablas en lugar de una sola, como se muestra en el enunciado original:

- **brand**: Contiene información sobre las marcas.
    - `brand_id`: Clave primaria.
    - `name`: Nombre de la marca.

- **product**: Contiene información sobre los productos.
    - `product_id`: Clave primaria.
    - `name`: Nombre del producto.
    - `description`: Descripción del producto.

- **currency**: Contiene información sobre las monedas.
    - `currency_code`: Clave primaria (código ISO de la moneda).
    - `currency_name`: Nombre de la moneda.

- **price_list**: Contiene la información de precios y tarifas aplicables.
    - `id`: Clave primaria.
    - `brand_id`: Clave foránea que referencia a `brand(brand_id)`.
    - `product_id`: Clave foránea que referencia a `product(product_id)`.
    - `start_date`: Fecha de inicio de la tarifa.
    - `end_date`: Fecha de fin de la tarifa.
    - `priority`: Prioridad de la tarifa.
    - `price`: Precio final de venta.
    - `currency_code`: Clave foránea que referencia a `currency(currency_code)`.

Esta normalización permite una mejor organización de los datos y facilita su escalabilidad y mantenimiento.

## Pruebas

El proyecto incluye tests para validar las siguientes peticiones al servicio con los datos del ejemplo:

- **Test 1**: Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 2**: Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 3**: Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
- **Test 4**: Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
- **Test 5**: Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)

Para ejecutar las pruebas, simplemente ejecuta el siguiente comando en la raíz del proyecto:

```bash
mvn test
```

## Mejoras y Justificaciones

Durante el desarrollo del proyecto, se realizaron las siguientes mejoras:

1. **Normalización de la base de datos**: En lugar de utilizar una sola tabla para almacenar toda la información, se dividieron los datos en cuatro tablas (`brand`, `product`, `currency`, `price_list`) para mejorar la organización, escalabilidad y mantenimiento de los datos. Esta decisión facilita la gestión de la base de datos y evita redundancias.

2. **Arquitectura en capas**: Se adoptó una arquitectura en capas para separar las responsabilidades y facilitar el mantenimiento del código. Cada capa (Controller, Service, Repository, Model) tiene una responsabilidad específica, lo que mejora la claridad y la modularidad del código.

3. **Consultas personalizadas en el repositorio**: Se utilizó la anotación `@Query` en el repositorio para definir una consulta personalizada que selecciona el precio aplicable con la mayor prioridad para un producto y marca específicos en una fecha dada. Esto asegura que la consulta sea eficiente y específica.

4. **Pruebas automatizadas**: Se incluyeron pruebas automatizadas para asegurar que el servicio funcione correctamente y para validar las peticiones al endpoint. Esto ayuda a detectar errores y garantizar la calidad del código.

Estas mejoras se realizaron para cumplir con los principios SOLID y Clean Code, así como para garantizar un diseño escalable y mantenible.
