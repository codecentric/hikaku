# hikaku
[![Build Status](https://api.travis-ci.org/codecentric/hikaku.svg?branch=master)](https://travis-ci.org/codecentric/hikaku) [![Maven Central Version](https://img.shields.io/maven-central/v/de.codecentric.hikaku/hikaku-core.svg)](https://search.maven.org/search?q=g:de.codecentric.hikaku)

<p align="center">
  <img src="docs/images/hikaku-logo-small.png">
</p>

Hikaku (比較) is japanese and means "comparison". This library tests if a REST-API implementation meets its specification.

If you create your REST-API contract-first without using any type of generation, you have to make sure that specification and implementation don't diverge.
The aim of this project is to meet this need and offer a mechanism to check specification and implementation for equality without having to create requests which are fired against a mock server. So this library won't check the behavior of the API, but the structural correctness.

## Currently supported

+ **Specifications**
  + [OpenAPI 3.0.X](openapi/README.md)
  + [RAML 1.X](raml/README.md)
  + [WADL](wadl/README.md)
+ **Implementations**
  + [Spring MVC 5.1.X](spring/README.md)
  + [JAX-RS 2.1.X](jax-rs/README.md)
    + [Jersey](https://jersey.github.io)
    + [Apache CXF](http://cxf.apache.org)
    + [Resteasy](https://resteasy.github.io)
    + [Restlet](https://restlet.com/open-source/documentation/user-guide/2.3/extensions/jaxrs)
  
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
    fun `specification matches implementation`() {
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
  public void specification_matches_implementation() {
    OpenApiConverter specification = new OpenApiConverter(Paths.get("openapi.json"));
    SpringConverter implementation = new SpringConverter(springContext);

    HikakuConfig hikakuConfig = new HikakuConfig(
        new HashSet<>(Arrays.asList(SpringConverter.IGNORE_ERROR_ENDPOINT))
    );
    
    new Hikaku(
        specification,
        implementation,
        hikakuConfig
    )
    .match();
  }
}
```

#### More Info

* **Blog (english):** [Spotting mismatches between your spec and your REST-API with hikaku](https://blog.codecentric.de/en/2019/03/spot-mismatches-between-your-spec-and-your-rest-api/)
* **Blog (german):** [ Abweichungen zwischen Spezifikation und REST-API mit hikaku erkennen](https://blog.codecentric.de/2019/03/abweichungen-zwischen-rest-api-spezifikation-erkennen/)
* **Sample project** [A complete sample project](https://github.com/cc-jhr/hikaku-sample)