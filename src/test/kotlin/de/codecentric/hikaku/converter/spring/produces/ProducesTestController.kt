package de.codecentric.hikaku.converter.spring.produces

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SpringBootApplication
open class DummyApp

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllFunctionsController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
open class RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllFunctionsController())
}

@RestController
@ResponseBody
open class RequestMappingOneMediaTypeIsExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class RequestMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnFunctionController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE, "application/pdf"])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnFunctionController())
}

@RestController
@ResponseBody
@RequestMapping("/todos")
open class RequestMappingOnClassDefaultValueController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnClassDefaultValueController())
}

@RestController
@ResponseBody
open class RequestMappingOnFunctionDefaultValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnFunctionDefaultValueController())
}

@RestController
@RequestMapping("/todos")
open class RequestMappingOnClassWithoutResponseBodyAnnotationController {

    @RequestMapping
    fun getAllTodos() { }
}

@RestController
open class RequestMappingOnFunctionWithoutResponseBodyAnnotationController {

    @RequestMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@ResponseBody
@RequestMapping("/todos")
open class RequestMappingWithoutProducesOnClassInfoAndStringAsResponseBodyValueController {

    @RequestMapping
    fun getAllTodos() = ""
}

@RestController
@ResponseBody
open class RequestMappingWithoutProducesOnFunctionInfoAndStringAsResponseBodyValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
@ResponseBody
open class GetMappingOneMediaTypeIsExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class GetMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingDefaultValueController())
}

@RestController
@ResponseBody
open class GetMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class GetMappingWithoutResponseBodyAnnotationController {

    @GetMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@ResponseBody
open class DeleteMappingOneMediaTypeIsExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingDefaultValueController())
}

@RestController
@ResponseBody
open class DeleteMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class DeleteMappingWithoutResponseBodyAnnotationController {

    @DeleteMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@ResponseBody
open class PatchMappingOneMediaTypeIsExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PatchMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingDefaultValueController())
}

@RestController
@ResponseBody
open class PatchMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PatchMappingWithoutResponseBodyAnnotationController {

    @PatchMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@ResponseBody
open class PostMappingOneMediaTypeIsExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PostMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingDefaultValueController())
}

@RestController
open class PostMappingWithoutResponseBodyAnnotationController {

    @PostMapping("/todos")
    fun getAllTodos() { }
}

@RestController
@ResponseBody
open class PostMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
@ResponseBody
open class PutMappingOneMediaTypeIsExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingOneMediaTypeIsExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PutMappingMultipleMediaTypesAreExtractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingMultipleMediaTypesAreExtractedCorrectlyController())
}

@RestController
@ResponseBody
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingDefaultValueController())
}

@RestController
@ResponseBody
open class PutMappingWithoutProducesInfoAndStringAsResponseBodyValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ""
}

@RestController
open class PutMappingWithoutResponseBodyAnnotationController {

    @PutMapping("/todos")
    fun getAllTodos() { }
}