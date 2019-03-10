# hikaku - Spring

Supports Spring MVC 5.1.X.

## Feature Support

Please refer to the list of [all features](features.md). To check the feature support for this converter.
You will find a list of spring specific features that are supported below.

### Paths

+ Supports RequestMapping annotation
  + _Example:_ `@RequestMapping("/todos")`

+ Supports all HTTP method based mapping annotations (DeleteMapping, GetMapping, PatchMapping, PostMapping, PutMapping)
  + _Example:_ `@GetMapping("/todos")`

+ Supports endpoint definition on class level, method level and a combination of both.

+ Supports multiple path definitions on all HTTP method based mapping annotations
  + _Example:_ `@GetMapping(value = ["/todos", "todo/list"])`

+ Supports multiple path definitions on RequestMapping annotation
  + _Example:_ `@RequestMapping(value = ["/todos", "todo/list"], method = [RequestMethod.POST, RequestMethod.GET])`

+ Supports endpoints using regex for path parameter
  + _Example:_ `@RequestMapping("/{id:[0-9]+}")`
  
### HTTP method

+ Supports RequestMapping annotation
  + _Example:_ `@RequestMapping(method = GET)`

+ Supports all HTTP method based mapping annotations (DeleteMapping, GetMapping, PatchMapping, PostMapping, PutMapping)
  + _Example:_ `@GetMapping("/todos")`

### Query parameters

+ Supports parameter name using variable name
  + _Example:_ `@RequestParam tag: String`

+ Supports parameter name defined by 'value'
  + _Example:_ `@RequestParam(value = "tag") otherName: String`

+ Supports parameter name defined by alias 'name'
  + _Example:_ `@RequestParam(name = "tag") otherName: String`

+ Throws an exception in case both 'value' and 'name' are set.
  + _Example:_ `@RequestParam(value = "param", name = "other") foo: String`

+ Checks if a parameter is required depending on the value of the 'required' attribute
  + _Example:_ `@RequestParam(required = false) foo: String`

+ Checks if a parameter is required depending on the existence of the 'defaultValue' attribute
  + _Example:_ `@RequestParam(value = "tag", defaultValue = "all")`

### Path parameters

+ Supports parameter name using variable name
  + _Example:_ `@PathVariable id: Int`

+ Supports parameter name defined by 'value'
  + _Example:_ `@PathVariable(value = "id") otherName: Int`

+ Supports parameter name defined by alias 'name'
  + _Example:_ `@PathVariable(name = "id") otherName: Int`

+ Throw an exception in case both 'value' and 'name' are set.
  + _Example:_ `@PathVariable(value = "param", name = "other") foo: Int`
  
### Header parameters

+ Supports parameter name using variable name
  + _Example:_ `@RequestHeader allowCache: String`

+ Supports parameter name defined by 'value'
  + _Example:_ `@RequestParam(value = "allow-cache") otherName: String`

+ Supports parameter name defined by alias 'name'
  + _Example:_ `@RequestHeader(name = "allow-cache") otherName: String`

+ Throw an exception in case both 'value' and 'name' are set.
  + _Example:_ `@RequestHeader(value = "param", name = "other") foo: String`

+ Checks if a parameter is required depending on the value of the 'required' attribute
  + _Example:_ `@RequestHeader(required = false) foo: String`

+ Checks if a parameter is required depending on the existence of the 'defaultValue' attribute
  + _Example:_ `@RequestHeader(value = "tracker-id", defaultValue = "2394")`
  
### Produces

+ Supports RequestMapping annotation
  + _Example:_ `@RequestMapping(produces = "text/plain")`

+ Supports all HTTP method based mapping annotations (DeleteMapping, GetMapping, PatchMapping, PostMapping, PutMapping)
  + _Example:_ `@GetMapping(produces = "text/plain")`

+ Supports default value in case no produces definition has been set

+ Supports text/plain if the return value is a String and no produces definition has been set

### Consumes

+ Supports RequestMapping annotation
  + _Example:_ `@RequestMapping(consumes = "text/plain")`

+ Supports all HTTP method based mapping annotations (DeleteMapping, GetMapping, PatchMapping, PostMapping, PutMapping)
  + _Example:_ `@GetMapping(consumes = "text/plain")`

+ Supports default value in case no consumes definition has been set

+ Supports `*/*` if the return value is a String and no consumes definition has been set

## Currently not supported

+ Checking whether or not to explode query parameter.
  + _Example:_ `@RequestParam(value="tag", required=false) tags: List<String>` **or** `@RequestParam(value="tag", required=false) tags: Array<String>`

+ Query Parameter based on an object
  + _Example:_ `@RequestParam queryParam: MyObject`
  
+ Produces using negated media type _Example:_ `@RequestParam(produces = "!text/plain")`

+ Consumes using negated media type _Example:_ `@RequestParam(produces = "!text/plain")`