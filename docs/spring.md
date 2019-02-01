# Hikaku (比較) - Spring

Supports Spring MVC 5.1.X.

## Feature Support

Please refer to the list of [all features](features.md). To check the feature support for this converter.
You will find a list of spring specific features that are supported below.

### Check endpoints
+ Supporting RequestMapping annotation
  + _Example:_ `@RequestMapping("/todos")`

+ Supporting all HTTP method based mapping annotations (DeleteMapping, GetMapping, PatchMapping, PostMapping, PutMapping)
  + _Example:_ `@GetMapping("/todos")`

+ Supporting endpoint definition on class level, method level and a combination of both.

+ Supporting multiple endpoint definitions on all HTTP method based mapping annotations
  + _Example:_ `@GetMapping(value = ["/todos", "todo/list"])`

+ RequestMapping annotation having multiple path mappings and/or multiple HTTP methods.
  + _Example:_ `@RequestMapping(value = ["/todos", "todo/list"], method = [RequestMethod.POST, RequestMethod.GET])`

+ Endpoints using regex for path parameter
  + _Example:_ `@RequestMapping("/{id:[0-9]+}")`

### Check query parameter

+ Check parameter name using variable name
  + _Example:_ `@RequestParam tag: String`

+ Check parameter name defined by 'value'
  + _Example:_ `@RequestParam(value = "tag") otherName: String`

+ Check parameter name defined by alias 'name'
  + _Example:_ `@RequestParam(name = "tag") otherName: String`

+ Throw an exception in case both 'value' and 'name' are set. This is good to fail fast. Otherwise this would only throw an error during runtime.
  + _Example:_ `@RequestParam(value = "param", name = "other") foo: String`

+ Check if parameter is required depending on value of 'required' attribute
  + _Example:_ `@RequestParam(required = false) foo: String`

+ Check if parameter is required depending on the existence of 'defaultValue' attribute
  + _Example:_ `@RequestParam(value = "tag", defaultValue = "all")`

### Check path parameter

+ Check parameter name using variable name
  + _Example:_ `@PathVariable id: Int`

+ Check parameter name defined by 'value'
  + _Example:_ `@PathVariable(value = "id") otherName: Int`

+ Check parameter name defined by alias 'name'
  + _Example:_ `@PathVariable(name = "id") otherName: Int`

+ Throw an exception in case both 'value' and 'name' are set. This is good to fail fast. Otherwise this would only throw an error during runtime.
  + _Example:_ `@PathVariable(value = "param", name = "other") foo: Int`
  
### Check header parameter

+ Check parameter name using variable name
  + _Example:_ `@RequestHeader useCache: String`

+ Check parameter name defined by 'value'
  + _Example:_ `@RequestParam(value = "use-cache") otherName: String`

+ Check parameter name defined by alias 'name'
  + _Example:_ `@RequestHeader(name = "use-cache") otherName: String`

+ Throw an exception in case both 'value' and 'name' are set. This is good to fail fast. Otherwise this would only throw an error during runtime.
  + _Example:_ `@RequestHeader(value = "param", name = "other") foo: String`

+ Check if parameter is required depending on value of 'required' attribute
  + _Example:_ `@RequestHeader(required = false) foo: String`

+ Check if parameter is required depending on the existence of 'defaultValue' attribute
  + _Example:_ `@RequestHeader(value = "tracker-id", defaultValue = "2394")`

## Currently not supported

+ Checking whether or not to explode query parameter.
  + _Example:_ `@RequestParam(value="tag", required=false) tags: List<String>` **or** `@RequestParam(value="tag", required=false) tags: Array<String>`

+ Query Parameter based on an object
  + _Example:_ `@RequestParam queryParam: MyObject`