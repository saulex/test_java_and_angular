
# Project Title

This project is made up of two parts, project Back-end and project Front-end


## Back-end
You must have a mysql database run in your local environment.

 - In the folder "test_java_crud" /src/main/resources there is a file application.properties, you should completed the setup database, create a new database if necessary
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/crud-solvedex-test-java (<- your database)
spring.datasource.username=root (<- your user)
spring.datasource.password=  (<- your password)
```

 - Open folder "test_java_crud" run this command

```bash
  % mvn install
```
 - Then run this command to lunch the app first time
```bash
  % mvn spring-boot:run   
```
- Next go to the class CreateRoles.java in folder /src/main/java/com/solvedex/test/security/utils/ and comment the line 11, should be looks like that

```bash

//@Component (<- comment line)
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);


    }

}
```
- Check in mysql client on the schema '(your database)' on the table 'role_name' if there are two rows.
```bash
|id.  |role_name  
-----------------
|1	  |ROLE_ADMIN
|2	  |ROLE_USER
```

- Shutdown the server
```bash
% control + r
```
- Finally run this comment

```bash
% mvn spring-boot:run  
```

- PPlease install Postman and import the collection test crud.postman_collection.json or open the url http://localhost:8080/swagger-ui/index.html to ckeck the collection REST.


## Front-end
You must have a npm and nodejs in your local environment.

 - First step is install angular client this tutorial can help you -> https://angular.io/guide/setup-local

- First check if your ng client is worked in local environment. run this comment
```bash
% ng version   

```
- You shuold get:
```bash

     _                      _                 ____ _     ___
    / \   _ __   __ _ _   _| | __ _ _ __     / ___| |   |_ _|
   / △ \ | '_ \ / _` | | | | |/ _` | '__|   | |   | |    | |
  / ___ \| | | | (_| | |_| | | (_| | |      | |___| |___ | |
 /_/   \_\_| |_|\__, |\__,_|_|\__,_|_|       \____|_____|___|
                |___/
    

Angular CLI: 16.2.7
Node: 18.17.0
Package Manager: npm 9.6.7
OS: darwin x64

Angular: 16.2.10
... animations, common, compiler, compiler-cli, core, forms
... platform-browser, platform-browser-dynamic, router

Package                         Version
---------------------------------------------------------
@angular-devkit/architect       0.1602.7
@angular-devkit/build-angular   16.2.7
@angular-devkit/core            16.2.7
@angular-devkit/schematics      16.2.7
@angular/cli                    16.2.7
@schematics/angular             16.2.7
rxjs                            7.8.1
typescript                      5.1.6
zone.js                         0.13.3

```

- Run the command

```bash
ng serve --proxy-config proxy.conf.json -o
```

- A new explore windows should apeare.


