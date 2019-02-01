# Hikaku (比較) - Features

The following table gives an overview of all features and which converter supports which feature.
The check for endpoint paths and http methods are base functions that every converter has to support. Those are not listed in the table below.

| Feature Name | Description | [OpenApi Converter](openapi.md)| [Spring Converter](spring.md) | [WADL Converter](wadl.md) |
| --- | --- | --- | --- | --- |
| QueryParameterName |Name of a query parameter. Example: For `/todos?filter=all` the query parameter name is `filter`| YES _(1.0.0)_ | YES _(1.0.0)_ | NO |
| QueryParameterRequired | Flag indicating whether a particular query parameter is required or not. | YES _(1.0.0)_ | YES _(1.0.0)_ | NO |
| PathParameter | Name of a path parameter. Example: For `/todos/{id}` the path parameter name is `id`| YES _(1.0.0)_ | YES _(1.0.0)_ | NO |
| HeaderParameterName |Name of a header parameter. | YES _(1.1.0)_ | YES _(1.1.0)_ | NO |
| HeaderParameterRequired | Flag indicating whether a particular header parameter is required or not. | YES _(1.1.0)_ | YES _(1.1.0)_ | NO |  