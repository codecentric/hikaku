package de.codecentric.hikaku.converters.spring.produces.responsebody

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

data class Todo(val description: String = "")

@Controller
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())
}

@Controller
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
open class RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())
}

@Controller
@ResponseBody
@RequestMapping("/todos")
open class RequestMappingOnClassDefaultValueController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnClassDefaultValueController())
}

@Controller
@ResponseBody
@RequestMapping("/todos")
open class RequestMappingWithoutProducesOnClassInfoAndStringAsResponseBodyValueController {

    @RequestMapping
    fun getAllTodos() = ""
}

@Controller
@RequestMapping("/todos")
open class RequestMappingOnClassWithoutResponseBodyAnnotationController {

    @RequestMapping
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassNoProducesInfoAndNoReturnTypeController {

    @RequestMapping
    fun todos() { }
}

@Controller
@ResponseBody
open class RequestMappingOneMediaTypeIsExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class RequestMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class RequestMappingOnFunctionDefaultValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnFunctionDefaultValueController())
}

@Controller
@ResponseBody
open class RequestMappingWithoutProducesOnFunctionInfoAndStringAsResponseBodyValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ""
}

@Controller
open class RequestMappingOnFunctionWithoutResponseBodyAnnotationController {

    @RequestMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnFunctionNoProducesInfoAndNoReturnTypeController {

    @RequestMapping
    fun todos() { }
}

@Controller
@ResponseBody
open class GetMappingOneMediaTypeIsExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class GetMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingDefaultValueController())
}

@Controller
@ResponseBody
open class GetMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ""
}

@Controller
open class GetMappingWithoutResponseBodyAnnotationController {

    @GetMapping("/todos")
    fun getAllTodos() { }
}

@RestController
open class GetMappingNoProducesInfoAndNoReturnTypeController {

    @GetMapping("/todos")
    fun todos() { }
}

@Controller
@ResponseBody
open class DeleteMappingOneMediaTypeIsExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingDefaultValueController())
}

@Controller
@ResponseBody
open class DeleteMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ""
}

@Controller
open class DeleteMappingWithoutResponseBodyAnnotationController {

    @DeleteMapping("/todos")
    fun getAllTodos() { }
}

@RestController
open class DeleteMappingNoProducesInfoAndNoReturnTypeController {

    @DeleteMapping("/todos")
    fun todos() { }
}

@Controller
@ResponseBody
open class PatchMappingOneMediaTypeIsExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PatchMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingDefaultValueController())
}

@Controller
@ResponseBody
open class PatchMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ""
}

@Controller
open class PatchMappingWithoutResponseBodyAnnotationController {

    @PatchMapping("/todos")
    fun getAllTodos() { }
}

@RestController
open class PatchMappingNoProducesInfoAndNoReturnTypeController {

    @PatchMapping("/todos")
    fun todos() { }
}

@Controller
@ResponseBody
open class PostMappingOneMediaTypeIsExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PostMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingDefaultValueController())
}

@Controller
open class PostMappingWithoutResponseBodyAnnotationController {

    @PostMapping("/todos")
    fun getAllTodos() { }
}

@Controller
@ResponseBody
open class PostMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PostMappingNoProducesInfoAndNoReturnTypeController {

    @PostMapping("/todos")
    fun todos() { }
}

@Controller
@ResponseBody
open class PutMappingOneMediaTypeIsExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingOneMediaTypeIsExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PutMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@Controller
@ResponseBody
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingDefaultValueController())
}

@Controller
@ResponseBody
open class PutMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ""
}

@Controller
open class PutMappingWithoutResponseBodyAnnotationController {

    @PutMapping("/todos")
    fun getAllTodos() { }
}

@RestController
open class PutMappingNoProducesInfoAndNoReturnTypeController {

    @PutMapping("/todos")
    fun todos() { }
}

@Controller
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())
}

@Controller
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE, "application/pdf"])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class GetMappingOneMediaTypeIsOverwrittenController {

    @GetMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class DeleteMappingOneMediaTypeIsOverwrittenController {

    @DeleteMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PatchMappingOneMediaTypeIsOverwrittenController {

    @PatchMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PostMappingOneMediaTypeIsOverwrittenController {

    @PostMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE])
open class PutMappingOneMediaTypeIsOverwrittenController {

    @PutMapping("/todos", produces = [TEXT_PLAIN_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class GetMappingMultipleMediaTypesAreOverwrittenController {

    @GetMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class DeleteMappingMultipleMediaTypesAreOverwrittenController {

    @DeleteMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PatchMappingMultipleMediaTypesAreOverwrittenController {

    @PatchMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PostMappingMultipleMediaTypesAreOverwrittenController {

    @PostMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
@RequestMapping(produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class PutMappingMultipleMediaTypesAreOverwrittenController {

    @PutMapping("/todos", produces = [TEXT_PLAIN_VALUE, APPLICATION_PDF_VALUE])
    fun todos() = Todo()
}

@Controller
@ResponseBody
open class ErrorEndpointController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(ErrorEndpointController())
}