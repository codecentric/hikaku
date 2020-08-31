package de.codecentric.hikaku.converters.spring.consumes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

data class Todo(val description: String)
data class Tag(val name: String)

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOneMediaTypeIsInheritedByAllFunctionsController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags")
    fun tags(@RequestBody tag: Tag) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
@Suppress("UNUSED_PARAMETER")
open class RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags")
    fun tags(@RequestBody tag: Tag) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOneMediaTypeIsExtractedCorrectlyController {

    @RequestMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @RequestMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags", consumes = [TEXT_PLAIN_VALUE])
    fun tags(@RequestBody tag: Tag) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }

    @RequestMapping("/tags", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun tags(@RequestBody tag: Tag) { }
}

@Controller
@RequestMapping("/todos")
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnClassDefaultValueController {

    @RequestMapping
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnFunctionDefaultValueController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping("/todos")
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnClassWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @RequestMapping
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnFunctionWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @RequestMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@RequestMapping("/todos")
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnClassWithoutRequestBodyAnnotationController {

    @RequestMapping
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class RequestMappingOnFunctionWithoutRequestBodyAnnotationController {

    @RequestMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingOneMediaTypeIsExtractedCorrectlyController {

    @GetMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @GetMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @GetMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class GetMappingWithoutRequestBodyAnnotationController {

    @GetMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingOneMediaTypeIsExtractedCorrectlyController {

    @DeleteMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @DeleteMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @DeleteMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingWithoutRequestBodyAnnotationController {

    @DeleteMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingOneMediaTypeIsExtractedCorrectlyController {

    @PatchMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PatchMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PatchMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PatchMappingWithoutRequestBodyAnnotationController {

    @PatchMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingOneMediaTypeIsExtractedCorrectlyController {

    @PostMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PostMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PostMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PostMappingWithoutRequestBodyAnnotationController {

    @PostMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingOneMediaTypeIsExtractedCorrectlyController {

    @PutMapping("/todos", consumes = [APPLICATION_XML_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PutMapping("/todos", consumes = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingWithoutConsumesInfoAndStringAsRequestBodyValueController {

    @PutMapping("/todos")
    fun todos(@RequestBody todo: String) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class PutMappingWithoutRequestBodyAnnotationController {

    @PutMapping("/todos")
    fun todos(todo: String) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class GetMappingOneMediaTypeIsOverwrittenController {

    @GetMapping("/todos", consumes = [TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingOneMediaTypeIsOverwrittenController {

    @DeleteMapping("/todos", consumes = [TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PatchMappingOneMediaTypeIsOverwrittenController {

    @PatchMapping("/todos", consumes = [TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PostMappingOneMediaTypeIsOverwrittenController {

    @PostMapping("/todos", consumes = [TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PutMappingOneMediaTypeIsOverwrittenController {

    @PutMapping("/todos", consumes = [TEXT_PLAIN_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class GetMappingMultipleMediaTypesAreOverwrittenController {

    @GetMapping("/todos", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class DeleteMappingMultipleMediaTypesAreOverwrittenController {

    @DeleteMapping("/todos", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PatchMappingMultipleMediaTypesAreOverwrittenController {

    @PatchMapping("/todos", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PostMappingMultipleMediaTypesAreOverwrittenController {

    @PostMapping("/todos", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@RequestMapping(consumes = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
@Suppress("UNUSED_PARAMETER")
open class PutMappingMultipleMediaTypesAreOverwrittenController {

    @PutMapping("/todos", consumes = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}

@Controller
@Suppress("UNUSED_PARAMETER")
open class ErrorEndpointController {

    @GetMapping("/todos", consumes = [APPLICATION_PDF_VALUE])
    fun todos(@RequestBody todo: Todo) { }
}
