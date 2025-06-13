# A Java Spring Boot Starter Template

## Contents
1. Spring Beans and dependency injection
2. Global Exception handling

### 1. Spring Beans
- Spring "Beans" are objects Spring decides to manage.
- Any POJO can be a bean if you tell Spring to "manage" it.
- Spring uses the following annotations to identify classes to create beans:
    - @Component
    - @Controller
    - @Service
    - @Repository
- The @SpringBootApplication annotation enables the scanning for these components.
- Spring holds instances of the beans in the ApplicationContext.
- The @Autowire annotation can be used to inject a dependency, but it shouldn't be used if a class only has one constructor.
- @RequiredArgsConstructor can be used for proper injection.
