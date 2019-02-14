package de.codecentric.hikaku.converter.spring.consumes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

data class Todo(val description: String)
data class Tag(val name: String)

@RestController
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsInheritedByAllFunctionsController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags")
    fun tags(@RequestBody tag: Tag) { }
}

@RestController
@RequestMapping(consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
open class RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags")
    fun tags(@RequestBody tag: Tag) { }
}

@RestController
open class RequestMappingOneMediaTypeIsExtractedCorrectlyController {

    @RequestMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class RequestMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @RequestMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags", consumes = [TEXT_PLAIN_VALUE])
    fun tags(@RequestBody tag: Tag) { }
}

@RestController
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags", consumes = [TEXT_PLAIN_VALUE, "application/pdf"])
    fun tags(@RequestBody tag: Tag) { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassDefaultValueController {

    @RequestMapping
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class RequestMappingOnFunctionDefaultValueController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @RequestMapping
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class RequestMappingOnFunctionWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassWithoutRequestBodyAnnotationController {

    @RequestMapping
    fun todos(todo: String) { }
}

@RestController
open class RequestMappingOnFunctionWithoutRequestBodyAnnotationController {

    @RequestMapping("/todos")
    fun todos(todo: String) { }
}

@RestController
open class GetMappingOneMediaTypeIsExtractedCorrectlyController {

    @GetMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class GetMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @GetMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class GetMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @GetMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class GetMappingWithoutRequestBodyAnnotationController {

    @GetMapping("/todos")
    fun todos(todo: String) { }
}

@RestController
open class DeleteMappingOneMediaTypeIsExtractedCorrectlyController {

    @DeleteMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @DeleteMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class DeleteMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @DeleteMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class DeleteMappingWithoutRequestBodyAnnotationController {

    @DeleteMapping("/todos")
    fun todos(todo: String) { }
}

@RestController
open class PatchMappingOneMediaTypeIsExtractedCorrectlyController {

    @PatchMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PatchMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PatchMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PatchMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PatchMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class PatchMappingWithoutRequestBodyAnnotationController {

    @PatchMapping("/todos")
    fun todos(todo: String) { }
}

@RestController
open class PostMappingOneMediaTypeIsExtractedCorrectlyController {

    @PostMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PostMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PostMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PostMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PostMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class PostMappingWithoutRequestBodyAnnotationController {

    @PostMapping("/todos")
    fun todos(todo: String) { }
}

@RestController
open class PutMappingOneMediaTypeIsExtractedCorrectlyController {

    @PutMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PutMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PutMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@RestController
open class PutMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PutMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@RestController
open class PutMappingWithoutRequestBodyAnnotationController {

    @PutMapping("/todos")
    fun todos(todo: String) { }
}