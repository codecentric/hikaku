# hikaku - Features

The following table gives an overview of all features and which converter supports which feature.
The check for endpoint paths and http methods are base functions that every converter has to support. Those are not listed in the table below.
There might be various ways to declare or use a feature, so check each converter for unsupported features as well. 

| Feature Name | Description | [OpenApi Converter](../openapi/README.md)| [Spring Converter](../spring/README.md) | [WADL Converter](../wadl/README.md) | [RAML](../raml/README.md) | [JAX-RS](../jax-rs/README.md) |
| --- | --- | --- | --- | --- | --- | --- |
| QueryParameter | Name of a query parameter and whether the parameter is required or not. Example: `/todos?filter=all`| ✅ _(1.0.0)_ | ✅ _(1.0.0)_ | ✅ _(1.1.0)_ | ✅ _(2.0.0)_ | ✅ _(2.1.0)_ |
| PathParameter | Name of a path parameter. Example: `/todos/{id}`| ✅ _(1.0.0)_ | ✅ _(1.0.0)_ | ✅ _(1.1.0)_ | ✅ _(2.0.0)_ | ✅ _(2.1.0)_ |
| HeaderParameter | Name of a header parameter and whether the parameter is required or not. | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(2.0.0)_ | ✅ _(2.1.0)_ |
| Produces | Checks the supported media types of the response. | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(2.0.0)_ | ✅ _(2.1.0)_ |
| Consumes | Checks the supported media types of the request. | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(1.1.0)_ | ✅ _(2.0.0)_ | ✅ _(2.1.0)_ |