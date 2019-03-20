# hikaku - JAX-RS

Converter for JAX-RS. This converter can be used with frameworks that make use of JAX-RS. For instance: [Jersey](https://jersey.github.io), [Apache CXF](http://cxf.apache.org), [Resteasy](https://resteasy.github.io), [Restlet](https://restlet.com/open-source/documentation/user-guide/2.3/extensions/jaxrs).

## Feature Support

Please refer to the list of [all features](../docs/features.md). To check the feature support for this converter.

## Currently not supported

* Parameters defined on fields or setters

## Usage

Instantiate the controller with a package name which will be scanned recursively for controllers.

_Example_: `JaxRsConverter("de.codecentric.hikaku")`