package de.codecentric.hikaku.converters.spring.produces.restcontroller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

data class Todo(val description: String = "")

@RestController
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())
}

@RestController
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
open class RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassDefaultValueController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnClassDefaultValueController())
}

@RestController
@RequestMapping("/todos")
open class RequestMappingWithoutProducesOnClassInfoAndStringAsResponseBodyValueController {

    @RequestMapping
    fun getAllTodos() = ""
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassNoProducesInfoAndNoReturnTypeController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class RequestMappingOneMediaTypeIsExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class RequestMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class RequestMappingOnFunctionDefaultValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnFunctionDefaultValueController())
}

@RestController
open class RequestMappingWithoutProducesOnFunctionInfoAndStringAsResponseBodyValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnFunctionNoProducesInfoAndNoReturnTypeController {

    @RequestMapping
    fun todos() { }
}

@RestController
open class GetMappingOneMediaTypeIsExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class GetMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingDefaultValueController())
}

@RestController
open class GetMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class GetMappingNoProducesInfoAndNoReturnTypeController {

    @GetMapping("/todos")
    fun todos() { }
}

@RestController
open class DeleteMappingOneMediaTypeIsExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingDefaultValueController())
}

@RestController
open class DeleteMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class DeleteMappingNoProducesInfoAndNoReturnTypeController {

    @DeleteMapping("/todos")
    fun todos() { }
}

@RestController
open class PatchMappingOneMediaTypeIsExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class PatchMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingDefaultValueController())
}

@RestController
open class PatchMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PatchMappingNoProducesInfoAndNoReturnTypeController {

    @PatchMapping("/todos")
    fun todos() { }
}

@RestController
open class PostMappingOneMediaTypeIsExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class PostMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingDefaultValueController())
}

@RestController
open class PostMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PostMappingNoProducesInfoAndNoReturnTypeController {

    @PostMapping("/todos")
    fun todos() { }
}

@RestController
open class PutMappingOneMediaTypeIsExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
open class PutMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingDefaultValueController())
}

@RestController
open class PutMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PutMappingNoProducesInfoAndNoReturnTypeController {

    @PutMapping("/todos")
    fun todos() { }
}

@RestController
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())
}

@RestController
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE, "application/pdf"])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class GetMappingOneMediaTypeIsOverwrittenController {

    @GetMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class DeleteMappingOneMediaTypeIsOverwrittenController {

    @DeleteMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PatchMappingOneMediaTypeIsOverwrittenController {

    @PatchMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PostMappingOneMediaTypeIsOverwrittenController {

    @PostMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PutMappingOneMediaTypeIsOverwrittenController {

    @PutMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class GetMappingMultipleMediaTypesAreOverwrittenController {

    @GetMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class DeleteMappingMultipleMediaTypesAreOverwrittenController {

    @DeleteMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PatchMappingMultipleMediaTypesAreOverwrittenController {

    @PatchMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PostMappingMultipleMediaTypesAreOverwrittenController {

    @PostMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@RestController
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PutMappingMultipleMediaTypesAreOverwrittenController {

    @PutMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@RestController
open class ErrorEndpointController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun todos() = ResponseEntity.status(200).body(ErrorEndpointController())
}