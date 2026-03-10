# DEZSYS_GK81_WAREHOUSE_ORM

Join GitHub Repo: https://github.com/ThomasMicheler/DEZSYS_GK862_DATAWAREHOUSE_ORM.git
   
This lesson introduces the data accessing model in Spring and the basics of Object Relational Mapping (ORM).

## Introduction

This exercise is intended to demonstrate the interaction between a programming language (Java) and a persistance layer (MySQL, PostgreSQL).

First you should follow the Spring tutorial ["Accessing data with MySQL"](https://spring.io/guides/gs/accessing-data-mysql) and document all important steps in your protocol. Don't forget to make notes about all problems occured during the setup. Afterwards you should extend the data model of the example and adapt it for a Data Warehouse application (data structure see below). One relation between the entities Datawarehouse and Products is required in this example. Please read the documentation how you implementation entity relations using the ORM model.

Document all individual implementation steps and any problems that arise in a log (Markdown).  
Create a GITHUB repository for this project and add the link to it in the comments.

## Requirements

*   MySQL DMS
     *  Local MySQL Service   
     *  MySQL Docker Container
*   Gradle 8 or higher  
*   Java SDK 18 or higher  
     
## Data Structure - Data Warehouse
```
<warehouseData>
    <warehouseID>001</warehouseID>
    <warehouseName>Linz Bahnhof</warehouseName>
    <warehouseAddress>Bahnhofsstrasse 27/9</warehouseAddress>
    <warehousePostalCode>Linz</warehousePostalCode>
    <warehouseCity>Linz</warehouseCity>
    <warehouseCountry>Austria</warehouseCountry>
    <timestamp>2021-09-12 08:52:39.077</timestamp>
    <productData>
         <product>
             <productID>00-443175</productID>
             <productName>Bio Orangensaft Sonne</productName>
             <productCategory>Getraenk</productCategory>
             <productQuantity>2500</productQuantity>
             <productUnit>Packung 1L</productUnit>
         </product>
         <product>
             <productID>00-871895</productID>
             <productName>Bio Apfelsaft Gold</productName>
             <productCategory>Getraenk</productCategory>
             <productQuantity>3420</productQuantity>
             <productUnit>Packung 1L</productUnit>
         </product>
    </productData>
</warehouseData>
```
## Important Commands. 

*   Use gradle to build the application  
     `gradle clean`   
     `gradle bootRun`   

*   Connect to MySQL Shell  
     `mysqlsh <username>@localhost`   

*   MySQL Shell Commands  
     `show databases; // list all local databases `   
     `use example;  // switch to a local database "example" `   
     `show tables;           // list all of current database   `   
     `create table example;   // create a SQL table with the name "example"   ` 

## Assessment

- Group size: 1 Person.  
- Result by protocol and delivery meeting (in English). 
- Requirements **Grundlagen**. 
    * Answer the questions below about the ORM framework.  
    * Use the tutorial ["Accessing data with MySQL"](https://spring.io/guides/gs/accessing-data-mysql) 
    * Implement the MySQL example with the User database 
    * Create a new user record (eg. curl -X POST "http://localhost:8080/demo/add" -d "name=John" -d "email=john@example.com")
    * Document each single development step in your protocol and describe the most important code snippets in few sentences. Furthermore, the output of the application and any problems that occur should be documented in submission document.
    * Customize the data model for the Data Warehouse application (min. 2 entities with 1 relation).  
    * Insert following records: 2 Data Warehouse records, 10 Product records.  
    * Document which parts of the program need to be adapted   
*  Extended Requirements **Erweiterte Grundlagen**
   *   Find out which methods are available for the CrudRepository to collect data   
        https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html.    
   *  Extend the Data Warehouse repository with following functionalities:   
       * Collect all data of one data warehouse specified by datawarehouseID.  
       * Collect a single product of a data warehouse specified by datawarehouseID and productID.  
       * Update a data warehouse using datawarehouseID. 
   * Document the parts of your project which have to be extend
   * Extend the data model with data about the customer purchases for a product and warehouse locations (eg. records for sold products like data/time, amount, location/warehouse)
   * Insert 30 records for sold products
*  Extended Requirements **Vertiefung**  
   * Create 250-300 training records (products, purchases)
   * Connect your application with an LLM (local eg. Ollama, cloud-based eg. Google Gemini) and generate a preview of sales numgers for the all warehouses and products over the coming months.


## Questions

Question
What is ORM and how is JPA used?

Answer
ORM maps Java objects to database tables. JPA is the standard API used to manage this mapping (e.g., via Hibernate).

Question
What is the application.properties used for and where must it be stored?

Answer
It stores configurations (DB URL, credentials, ports) and must be located in src/main/resources.

Question
Which annotations are frequently used for entity types? Which key points must be observed?

Answer
@Entity, @Id, and @OneToMany. Key points: Entities require a primary key and a no-args constructor.

Question
What methods do you need for CRUD operations?

Answer
save() (Create/Update), findAll() / findById() (Read), and deleteById() (Delete).

## Problems i ran into

Troubleshooting Protocol: Data Warehouse Implementation
1. Connection Error: Wrong Port (3306 vs. 8080)

   Issue: curl: (1) Received HTTP/0.9 when not allowed.

   Cause: Attempting to send HTTP requests directly to the MySQL Database on port 3306.

   Resolution: Switched to port 8080, which is the entry point for the Spring Boot Application. The application acts as a bridge to the database.

2. Logic Error: HTTP Method Mismatch (405)

   Issue: 405 Method Not Allowed.

   Cause: Using -X POST for the /all endpoint.

   Resolution: Changed the request method to GET, as the @GetMapping annotation in the Java controller only accepts data retrieval requests.

3. Syntax Error: PowerShell vs. Curl (Parameter Binding)

   Issue: Cannot bind parameter 'Headers'.

   Cause: Windows PowerShell uses an alias for curl that points to Invoke-WebRequest, which has different syntax for headers (-H).

   Resolution: Used Invoke-RestMethod or curl.exe to ensure the JSON headers and body were parsed correctly.

4. Client Error: Unsupported Media Type (415)

   Issue: 415 Unsupported Media Type.

   Cause: Sending data as plain text or form-data while the controller expects JSON (@RequestBody).

   Resolution: Added the header -H "Content-Type: application/json" to inform the server about the data format.

5. Server Error: Payload Mismatch (500/400)

   Issue: 500 Internal Server Error or 400 Bad Request.

   Cause: The JSON body did not match the Java Entity fields (e.g., using location instead of warehouseName) or missing required Primary Keys (warehouseID).

   Resolution: Adjusted the JSON payload to exactly match the variable names and data types defined in the Warehouse and Product classes.

## Links & Further Resources

* Object Relational Mapping (ORM) Data Access:   
   https://docs.spring.io/spring-framework/reference/data-access/orm.html
* Accessing data with MySQL.  
   https://spring.io/guides/gs/accessing-data-mysql
* Accessing Data with JPA   
   https://spring.io/guides/gs/accessing-data-jpa
* Difference between Hibernate and Spring Data:  
   https://dzone.com/articles/what-is-the-difference-between-hibernate-and-sprin-1
* Introduction Hibernate:   
   https://vicksheet.medium.com/getting-started-with-hibernate-an-introduction-to-the-orm-framework-for-java-applications-fd97af01b7a6
* Video:   
   https://www.youtube.com/watch?v=NC-1j1grMPI&ab_channel=ManningPublications

FINAL