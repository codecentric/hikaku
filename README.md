[![Build Status](https://api.travis-ci.org/codecentric/hikaku.svg?branch=master)](https://travis-ci.org/codecentric/hikaku)
# Hikaku

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
It is possible that not every converter supports every feature. Only the intersection of the features of two `EndpointConverter`s is used for the matching. Please keep that in mind regarding the equality of implementation and specification.
  
## Usage

Setting up a test with hikaku is very simple. You just instantiate the `Hikaku` class and provide an `EndpointConverter` for the specification and another one for the implementation. Optionally, you can also pass an instance of `HikakuConfig`. Check the list of options and default values of the [config](docs/config.md). Then you call `match()` on the `Hikaku` class.
The match result is sent to one or multiple `Reporter`. If the test fails kotlin's `DefaultAsserter.fail()` method is called.


### Example

There is an artifact for each converter. So we need one dependency for the specification and one for the implementation. In this example our project consists of an OpenAPI specification and a Spring implementation. The specification does not contain the _/error_ endpoints created by spring, so we want to omit those.
First add the dependencies for the converters, that we want to use. In this case `hikaku-openapi` and `hikaku-spring`.

```gradle
dependencies {
    testImplementation: "de.codecentric.hikaku:hikaku-openapi:$hikakuVersion"
    testImplementation: "de.codecentric.hikaku:hikaku-spring:$hikakuVersion"
}
```

#### Kotlin

And now we can create the test case:

```kotlin
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
                        ignorePaths = setOf(SpringConverter.IGNORE_ERROR_ENDPOINT)
                )
        )
        .match()
    }
}
```

#### Java

Same example in Java:

```java
@SpringBootTest
public class SpecificationTest {

  @Autowired
  private ApplicationContext springContext;

  @Test
  public void compare_specification_and_implementation() {
    OpenApiConverter openApiConverter = OpenApiConverter.usingPath(Paths.get("openapi.json"));
    SpringConverter springConverter = new SpringConverter(springContext);

    HikakuConfig hikakuConfig = new HikakuConfig(
        new HashSet<>(Arrays.asList(SpringConverter.IGNORE_ERROR_ENDPOINT))
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
