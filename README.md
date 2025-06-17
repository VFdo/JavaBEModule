# A Java Spring Boot Starter Template

## Contents
1. Spring Beans and dependency injection
2. Global Exception handling

### 1. Spring Beans
- Spring "Beans" are objects Spring decides to manage.
- Any POJO can be a bean if you tell Spring to "manage" it.
- Spring uses the following annotations to identify classes to create beans:
    - `@Component`
    - `@Controller`
    - `@Service`
    - `@Repository`
- The `@SpringBootApplication` annotation enables the scanning for these components.
- Spring holds instances of the beans in the ApplicationContext.
- The `@Autowire` annotation can be used to inject a dependency, but it shouldn't be used if a class only has one constructor.
- `@RequiredArgsConstructor` can be used for proper injection.

### 2. Global Exception Handling
- Exceptions can be handled globally and locally.
- Local handling is done by using try-catch-finally statements.
- Global exception handling can be done using the `@ControllerAdvice` annotation, which is explicitly used for centralized exception handling and request pre-processing in Spring Boot.
- This annotation can be used to define a class for exception handling.
- In the class we can define methods to handle specific exceptions.
- We can use addtional annotations with these handler methods,
    - `@ExceptionHandler` - to specify the type of exception to handle.
    - `@ModelAttribute` - to add model attributes. (pre-processing)
    - `@InitBinder` - to bind data. (pre-processing)
- The `@RestControllerAdvice` annotation, which is a specialization of the @ControllerAdvice anotation, includes @ResponseBody.
- That makes it better suited for RESTful applications.
