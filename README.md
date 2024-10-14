# API - Gestion de Ventas
Este API permite la gestion de ventas y productos de tu tienda.

## Descripción
Esta API proporciona endpoints para gestionar el catálogo de productos, manejar las ventas y consultar reportes de ventas realizadas.

Está diseñada para ser utilizada por distintas tecnologias front-end;


## Requisitos
-  **Java 23**
- **MySQL**
- **Maven**
- **Spring Boot 3.3.4**

## Instalación de la API
1. Clonar el repositorio
``` BASH
git clone https://github.com/Mikeas367/API-GestionVentas.git
```
2. Moverte hacia el repositorio
``` BASH
cd API-GestionVentas
```
3. Instalar las dependencias con maven
``` BASH
mvn clean install
```
4. Configura la base de datos en el archivo application.properties
``` properties 
   spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
```
5. Ejecuta el proyecto
``` BASH
mvn spring-boot:run
```

# Documentación de la API
## Obtener todos los productos
- **URI**: /api/products
	- **Metodo** : GET
		- **Descripcion**: Retorna una lista con todos los productos almacenados en la base de datos.
		- **Respuesta**:
			```Json
			[
				{
					"id": 1,
					"productName": "Coca-Cola 500ml",
					"unitPrice": 1500.0,
					"productStock": 8
				},
				{
					"id": 2,
					"productName": "Galletita Oreo",
					"unitPrice": 2500.0,
					"productStock": 19
				}
			]```
## Obtener un Producto
- **URI**: /api/products/{id}
	- **Metodo** : GET
		- **Descripcion**:  Retorna un solo producto con el id indicado en la URI.
		- **Respuesta**:
			```Json
				{
					"id": 1,
					"productName": "Coca-Cola 500ml",
					"unitPrice": 1500.0,
					"productStock": 8
				}```

## Nuevo Producto
**URI**: /api/products
 - **Metodo**: POST
	 - **Descripcion**: Crea un nuevo producto y lo almacena enla base de datos
	 - **Cuerpo de la solicitud**
```Json
	{
		"productName": "Coca-Cola 500ml",
		"unitPrice": 1500.0,
		"productStock": 8
	}
```

- **Respuesta**
```Json
	{
    		"id": 1,
    		"productName": "Coca-Cola 500ml",
    		"unitPrice": 1500.0,
    		"productStock": 8
	}
```

## NuevaVenta
**URI**: /api/sales
 - **Metodo**: POST
	 - **Descripcion**: Crea un nueva venta y la almacena enla base de datos, modificando el stock del producto/s especificados en cada detalle de venta.
	 - **Cuerpo de la solicitud**
```Json
	{
		"saleDescription": "Venta de prueba",
		"details": [
				{
					"product": {
							"id": 1
						},
					"quantity": 2
				},
				{
					"product": {
							"id": 2
						},
					"quantity": 1
				}
			]
	}
```
