# hikaku
[![Build](https://github.com/cc-jhr/hikaku/actions/workflows/build.yml/badge.svg)](https://github.com/cc-jhr/hikaku/actions/workflows/build.yml) [![Coverage Status](https://coveralls.io/repos/github/cc-jhr/hikaku/badge.svg)](https://coveralls.io/github/cc-jhr/hikaku)

<p align="center">
  <img src="docs/images/hikaku-logo-small.png">
</p>

Hikaku (比較) is japanese and means "comparison". This library tests if a REST-API implementation meets its specification.

If you create your REST-API contract-first without using any type of generation, you have to make sure that specification and implementation don't diverge.
The aim of this project is to meet this need and offer a mechanism to check specification and implementation for equality without having to create requests which are fired against a mock server. So this library won't check the behavior of the API, but the structural correctness. Please see also the section [limitations](#limitations)

## Currently supported

+ **Specifications**
  + [OpenAPI 3.0.X](openapi/README.md)
  + [RAML 1.X](raml/README.md)
  + [WADL](wadl/README.md)
+ **Implementations**
  + [Spring MVC 5.3.X](spring/README.md)
  + [Micronaut](micronaut/README.md)
  + [JAX-RS 3.0.X](jax-rs/README.md)
    + [Apache CXF](http://cxf.apache.org)
    + [Dropwizard](https://www.dropwizard.io)
    + [Jersey](https://jersey.github.io)
    + [Resteasy](https://resteasy.github.io)
    + [Restlet](https://restlet.com/open-source/documentation/user-guide/2.3/extensions/jaxrs)
    + [Quarkus](https://quarkus.io)
  
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
    testImplementation "de.codecentric.hikaku:hikaku-openapi:$hikakuVersion"
    testImplementation "de.codecentric.hikaku:hikaku-spring:$hikakuVersion"
}
```

#### Kotlin

And now we can create the test case:

```kotlin
@SpringBootTest
class SpecificationTest {

    @Autowired
    private lateinit var springContext: ApplicationContext

    @Test
    fun `specification matches implementation`() {
        Hikaku(
                specification = OpenApiConverter(Paths.get("openapi.yaml")),
                implementation = SpringConverter(springContext),
                config = HikakuConfig(
                        filters = listOf(SpringConverter.IGNORE_ERROR_ENDPOINT)
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
    List<Function1<Endpoint, Boolean>> filters = new ArrayList<>();
    filters.add(SpringConverter.IGNORE_ERROR_ENDPOINT);

    List<Reporter> reporters = new ArrayList<>();
    reporters.add(new CommandLineReporter());

    new Hikaku(
            new OpenApiConverter(Paths.get("openapi.json")),
            new SpringConverter(springContext),
            new HikakuConfig(
                    reporters,
                    filters
            )
    )
    .match();
  }
}
```

## Limitations
Hikaku checks the implementation with static code analysis. So everything that is highly dynamic is not covered by hikaku. There might be other libraries and frameworks that can cover these aspects by checking the behavior.

### http status codes
For implementations the status codes are very dynamic. There are various ways to set a http status. For example using a `ResponseEntity` object in spring or using additional filters and so on. That's why hikaku does not support http status codes.

### Request and response object
For implementations both request and response objects are highly dynamic. For response objects there might be a generic `ResponseEntity` as well or interfaces with different implementations can be used. In both cases (request and response) the objects can be altered by a serialization library and there a lot of different libs out there. That's why hikaku neither supports request nor response objects.

## More Info

* **Blog (english):** [Spotting mismatches between your spec and your REST-API with hikaku](https://blog.codecentric.de/en/2019/03/spot-mismatches-between-your-spec-and-your-rest-api/)
* **Blog (german):** [ Abweichungen zwischen Spezifikation und REST-API mit hikaku erkennen](https://blog.codecentric.de/2019/03/abweichungen-zwischen-rest-api-spezifikation-erkennen/)
* **Sample project** [A complete sample project](https://github.com/cc-jhr/hikaku-sample)
