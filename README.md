# Hikaku (比較)

Hikaku (比較) is japanese and means "comparison". This library tests if a REST-API implementation meets it's specification.

If you create your REST-API contract-first without using any type of generation, you have to make sure that specification and implementation don't diverge.
The aim of this project is to meet this need and offer a mechanism to check specification and implementation for equality without having to create requests which are fired against a mock server. So this library won't check the behavior of the API, but the structural correctness.

## Currently supported

+ **Specifications**
  + [OpenAPI 3.0.X](docs/openapi.md)
  + [WADL](docs/wadl.md)
+ **Implementations**
  + [Spring MVC 5.1.X](docs/spring.md)
  
Please refer to the list of [all features](docs/features.md). To check the feature support for each converter.
  
## Usage
Hikaku uses built-in Kotlin assertions. That's why you always have to add the vm-argument `-ea` when you run the tests.

Setting up a test with hikaku is very simple. You just instantiate the `Hikaku` class and provide an `EndpointConverter` for the specification and another one for the implementation. Optionally, you can also pass an instance of `HikakuConfig`. Check the list of options and default values of the [config](docs/config.md). Then you call `match()` on the `Hikaku` class.

The match result will be send to a `Reporter` and the kotlin assertion is called afterwards so the test either succeeds or fails.


### Example

In this example our project consists of an OpenAPI specification and a Spring implementation. The specification does not contain the _/error_ endpoints created by spring, so we want to omit those.

#### Kotlin

```
@RunWith(SpringRunner::class)
@SpringBootTest
class SpecificationTest {

    @Autowired
    lateinit var springContext: ApplicationContext

    @Test
    fun `compare specification and implementation`() {
        Hikaku(
                specification = OpenApiConverter(Paths.get("openapi.yaml")),
                implementation = SpringConverter(springContext),
                config = HikakuConfig(
                        ignorePaths = SpringConverter.IGNORE_ERROR_ENDPOINTS
                )
        )
        .match()
    }
}
```

#### Java

Same example in Java:

```
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationTest {

  @Autowired
  private ApplicationContext springContext;

  @Test
  public void compare_specification_and_implementation() {
    OpenApiConverter openApiConverter = Companion.invoke(Paths.get("openapi.json"));
    SpringConverter springConverter = new SpringConverter(springContext);

    HikakuConfig hikakuConfig = new HikakuConfig(
        new HashSet<>(SpringConverter.Companion.getIGNORE_ERROR_ENDPOINTS()),
        false,
        false,
        new CommandLineReporter()
    );
    
    new Hikaku(
        openApiConverter,
        springConverter,
        hikakuConfig
    )
    .match();
  }
}
```