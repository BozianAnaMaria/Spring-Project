# Application for Project Management with Spring Framework
# Notes
## 1. Core Spring Annotations (Dependency Injection & Configuration)
 - @Configuration – Marks a class as a source of bean definitions.
 - @Component – Marks a class as a Spring-managed component (general-purpose bean).
 - @Service – Specialization of @Component for service layer classes.
 - @Repository – Specialization of @Component for DAO (data access object) classes.
 - @Bean – Declares a Spring-managed bean within a @Configuration class.
 - @Autowired – Automatically injects dependencies.
 - @Qualifier – Specifies which bean to inject when multiple candidates exist.
 - @Value – Injects values from properties files or environment variables.

## 2. Annotations Differences
1. @Controller

This annotation is used in the presentation layer of a Spring MVC application. It is responsible for handling HTTP requests and returning either a view (for web applications) or data (if combined with @ResponseBody in REST APIs). It is specifically meant for controllers managing web interactions.

2. @Service

This annotation is used in the service layer and is meant to hold business logic. It acts as a middle layer between the controller and the repository, ensuring that business operations are correctly executed. It is primarily used to improve readability and maintainability.

3. @Repository

This annotation is used in the data access layer, specifically for DAO (Data Access Object) components. It provides exception translation, converting database-related exceptions into Spring-specific exceptions. It is typically applied to classes interacting with the database.

4. @Component

This is a generic annotation that marks a class as a Spring-managed bean. Unlike @Service and @Repository, which are specializations of @Component, it is not tied to any specific layer and can be used for utility or helper classes.

5. @Configuration

This annotation is used for defining Spring configuration classes. It is responsible for managing bean definitions explicitly using @Bean methods. It helps configure application components programmatically rather than using XML configurations.