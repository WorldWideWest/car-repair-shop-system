# car-repair-shop-system

## Table of contents
* [General Information](#general-info)
* [Setup](#setup)
  * [DB Setup](#db-setup)
  * [Application Setup](#application-setup)
* [About the Application](#about-the-application)
  * [Registration](#registration)
  * [Tracking](#tracking)
  * [Authentication & Authorization](#authentication-and-authorization)
  * [Home](#home)
* [Technologies used](#technologies-used)

## General Info

The repair shop system is a simple web app who track the customer ticket through the repair process in the car repair workshop.

The App is built on Spring Framework and contains modules for: 
1. Registering the customer ticket to the system,
2. Tracking the status of the ticket in the status module (Fixed/Not Fixed),
3. Custom User Authentication & Authorization module,
4. Home module

The file structure of the project contains the major folder of system where you can find the application files and resources and the sql folder where you can find the SQL script to setup all databases you will need to run the app.


## Setup

To run the app will need to setup SQL & our application.properties file in the Spring resources directory.

### DB Setup
To setup the database load the file: 
`./sql/table_init.sql`, once you loaded the file into your prefered SQL editor execute it. To understand the script better will go through it here:

```sql
DROP DATABASE IF EXISTS car_repair;
CREATE DATABASE IF NOT EXISTS car_repair;
```

In these first lines we drop the database if it exists and creating the same database. This is done when we want to start all over again and don't want the preavious database to interfire with our new database.

Then we go to use the created database with the command:

```sql
USE car_repair;
```

This is a really important step because if we don't use the created database will get an error because no database was selected and therefor we can't create the desired tables that we need to store data from our spring app.

The next step is to create the tables:

```sql
DROP TABLE IF EXISTS Ticket;
CREATE TABLE IF NOT EXISTS Ticket(
    ....
)
```

With the structure of the first table in place we move to create the second table that will reference the first table, and that is the only structural difference between the two.

The Status table contains the field ticket that references as a foreign key the Ticket table, and has a ON DELETE CASCADE constraint, that means if the record that is referenced deleted go ahead and delete all enteries that reference that record.


The reason why `Status` contains backticks is because it is a SQL keyword and by adding backticks we converte it to normal string.
```sql
DROP TABLE IF EXISTS `Status`;
CREATE TABLE IF NOT EXISTS `Status`(
    ....
    ticket INT NOT NULL,
    FOREIGN KEY(ticket) REFERENCES Ticket(id) ON DELETE CASCADE
)
```

Then we come to the part of Authentication & Authorization tables which have a ManyToMany relationship. This is because in the app we want to diferentiate users as ADMIN's and EMPLOYEE's and the EMPLOYEE's can't have access to certan parts of the app.

The `users` table contain basic information about the user but the key thing here is the username field which has to be unique, and it has that constrint, so we can authenticate each user individualy and there will be no users with the same username. But this really depends on you and what you want to store in the Table for the user, you could also use the email. But if you want to know more about it consult the Spring Security documentation where all of this is fearmly explained.

The password field is also ommited into backticks because of the keyword issue. The password is stored as an hashed string of characters and is hashed in our spring app and passed to the database and saved. For the hashing I used the BCrypt Algorithm.

```sql
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
    ....
	username VARCHAR(200) NOT NULL UNIQUE,
    `password` VARCHAR(64) NOT NULL
)
```
Once the table was created I inserted the value of the first user. The user is Oliver Twist with the id of oli.t and a hashed password which is eminem662

```sql
INSERT INTO users VALUES
(1, 'Oliver', 'Twist', 'oli.t', '$2a$10$eyl1t1zgeiNL3VR5U87d/uK5ZRl4wpPwcfskaFm5GZ8TpUVB0V9hG'); -- password:eminem662
```

Then we have the roles table which we will use for authorization for each user. This table isn't special, the only thing we added here is that the name need's to be a UNIQUE.

```sql
DROP TABLE IF EXISTS roles;
CREATE TABLE IF NOT EXISTS roles(
    ....
    `name` VARCHAR(50) NOT NULL UNIQUE
);
```

Here we also insert values that we want to use as our authorization constraints, for us we have here employee and admin

```sql
INSERT INTO roles VALUES
(1, "EMPLOYEE"),
(2, "ADMIN");
```

And then at the end we have the `users_roles` table who stores all the roles that each user has. Both of the fields are referencing the users and roles tables and both have the ON DELETE CASCADE, because we don't want to store information about a user who doesn't exist.

```sql
DROP TABLE IF EXISTS users_roles;
CREATE TABLE IF NOT EXISTS users_roles(
	user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);
```

Because we created a user above Oliver Twist, now will insert values into users_roles table to grant this user to view all pages, because he will have both authorization values.

```sql
INSERT INTO users_roles VALUES
(1, 1),
(1, 2);
```

And with this our SQL setup is done.


### Application setup

For application setup we have to update the application.properties file whicih is in `system\src\main\resources\application.properties`.

Once you open the file you will have few enteries:
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/car_repair?useSSL=false&serverTimezone=UTC
spring.datasource.username=username
spring.datasource.password=password
```
If you are hosting the database on the same port as I do 3306, you don't have to change anything in the url field.
The username and password fields are specific to your user on your database server. Enter the username and password of the user where you executed the `tables_init.sql` script.


Now we have finished the setup of our application. You can go ahead and run the application.



## About the Application

The application consists of 4 modules and they are:
1. Registering the customer ticket to the system,
2. Tracking the status of the ticket in the status module (Fixed/Not Fixed),
3. Custom User Authentication & Authorization module,
4. Home module

### Registration

When the customer comes to the repair shop he gives his information to the worker and the worker enters the data into our `Tickets` database. The view contains all the CRUD functionality and has form validation built in.


### Tracking

Once the car has reached the phase where it is ready for repair the worker has access to the created ticket and then can update the status of the ticket as fixed or not fixed. Also in this phase he adds the description on what repair was done on the car and adds the cost. This form also has builtin validation.

### Authentication and Authorization

We talked about this part in the DB Setup phase, but the for the authentication we use our custom register, login & logout forms. And the authorization enables us to restrict EMPLOYEES to have access to the views and pages thta ADMINS have, same stands for the ADMINS.

### Home

The home module gives the customer the chance to see in which phase is his/her car. The form has validation for strings, if a string is entered will prompt the user just to enter a number because the form only accepts numbers and if the ticket doesn't exist will prompt the user.

If the Car has a ticket but has not reached the phase of repair will also display that information and at the end if the car is repaired will display the information on what has been done to repair the car and the cost.


## Technologies used

* [Java](https://www.java.com/en/)
* [Spring framework](https://spring.io/)
* [MySQL Workbench CE](https://www.mysql.com/products/workbench/)
* [Visual Studio Code](https://code.visualstudio.com/)
* [Java exstensions pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
* [Git & GitHub](https://github.com/)
* [Windows Subsystem for Linux (WSL2)](https://docs.microsoft.com/en-us/windows/wsl/)
* Dependencies for the Project in the `pom.xml` file