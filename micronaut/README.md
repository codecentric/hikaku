# hikaku - Micronaut

Converter for Micronaut.

## Feature Support

Please refer to the list of [all features](../docs/features.md). To check the feature support for this converter.

### Paths

+ Supports Controller annotation in combination with empty HttpMethod annotation
  + _Example:_
```
@Controller("/todos")
class TodosController {

    @Get
    fun todos() { }
}
```

+ Supports path declaration in combination of Controller annotation and HttpMethod annotation
  + _Example:_
```
@Controller("/todo")
class TodosController {

    @Get("list")
    fun todos() { }
}
```

+ Supports alias `uri` for HttpMethod annotation
  + _Example:_ `@Get(uri = "/todos")`
  
+ Supports paths with leading and non-leading slash on both Controller and HttpMethod annotation
  + _Examples:_
    + `@Controller("/todos")` and `@Controller("todos")`
    + `@Get("/todos")` and `@Get("todos")`

### Path parameters

+ Supports path parameter without annotation
  + _Examples:_
```
@Controller("/todos/{id}")
class TodosController {

    @Get
    fun todos(id: String) { }
}
```

+ Supports path parameter with annotation
  + _Examples:_
```
@Controller("/todos/{id}")
class TodosController {

    @Get
    fun todos(@PathVariable("id") otherName: String) { }
}
```

+ Supports using alias `name`
  + _Examples:_ `@PathVariable(name = "id") otherName: String`

### Query parameters

+ Supports query parameter without annotation
  + _Examples:_
```
@Controller("/todos")
class TodosController {

    @Get
    fun todos(filter: String) { }
}
```

+ Supports query parameter with annotation
  + _Examples:_
```
@Controller("/todos")
class TodosController {

    @Get
    fun todos(@QueryValue("filter") filter: String) { }
}
```

+ Supports setting the parameter _required_ or _optional_ based on the existence of a default value
  + _Examples:_ `@QueryValue("filter", defaultValue = "all") filter: String`

### Header parameters

+ Supports required header parameter
  + _Examples:_
```
@Controller("/todos")
class TodosController {

    @Get
    fun todos(@Header("allow-cache") otherName: String) { }
}
```

+ Supports optional header parameter based on the existence of a default value
  + _Examples:_ `@Header("allow-cache", defaultValue = "true") otherName: String`

## Usage

Instantiate the converter with a package name which will be scanned recursively for controllers.

_Example_: `MicronautConverter("de.codecentric.hikaku")`