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
open class RequestMappingOneMediaTypeIsInheritedByAllMethodsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllMethodsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsInheritedByAllMethodsController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
open class RequestMappingMultipleMediaTypesAreInheritedByAllMethodsController {

    @RequestMapping
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllMethodsController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreInheritedByAllMethodsController())
}

@RestController
@ResponseBody
open class RequestMappingOneMediaTypeIsExractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class RequestMappingMultipleMediaTypesAreExractedCorrectlyController {

    @RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE])
open class RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnMethodController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnMethodController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingOneMediaTypeIsOverwrittenByDeclarationOnMethodController())
}

@RestController
@ResponseBody
@RequestMapping("/todos", produces = [APPLICATION_XML_VALUE, APPLICATION_XHTML_XML_VALUE])
open class RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnMethodController {

    @RequestMapping(produces = [TEXT_PLAIN_VALUE, "application/pdf"])
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnMethodController())

    @RequestMapping("/{id}")
    fun getSpecificTodo() = ResponseEntity.status(200).body(RequestMappingMultipleMediaTypesAreOverwrittenByDeclarationOnMethodController())
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
open class RequestMappingOnMethodDefaultValueController {

    @RequestMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(RequestMappingOnMethodDefaultValueController())
}

@RestController
@ResponseBody
open class RequestMappingWithoutProducesInfoAndStringAsReturnValueDefinedOnFunctionController {

    @RequestMapping("/todos")
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
@RequestMapping("/todos")
open class RequestMappingWithoutProducesInfoAndStringAsReturnValueDefinedOnClassController {

    @RequestMapping
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
open class GetMappingOneMediaTypeIsExractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class GetMappingMultipleMediaTypesAreExractedCorrectlyController {

    @GetMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
open class GetMappingDefaultValueController {

    @GetMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(GetMappingDefaultValueController())
}

@RestController
@ResponseBody
open class GetMappingWithoutProducesInfoAndStringAsReturnValueController {

    @GetMapping("/todos")
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
open class DeleteMappingOneMediaTypeIsExractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class DeleteMappingMultipleMediaTypesAreExractedCorrectlyController {

    @DeleteMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
open class DeleteMappingDefaultValueController {

    @DeleteMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(DeleteMappingDefaultValueController())
}

@RestController
@ResponseBody
open class DeleteMappingWithoutProducesInfoAndStringAsReturnValueController {

    @DeleteMapping("/todos")
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
open class PatchMappingOneMediaTypeIsExractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PatchMappingMultipleMediaTypesAreExractedCorrectlyController {

    @PatchMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PatchMappingDefaultValueController {

    @PatchMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PatchMappingDefaultValueController())
}

@RestController
@ResponseBody
open class PatchMappingWithoutProducesInfoAndStringAsReturnValueController {

    @PatchMapping("/todos")
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
open class PostMappingOneMediaTypeIsExractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PostMappingMultipleMediaTypesAreExractedCorrectlyController {

    @PostMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PostMappingDefaultValueController {

    @PostMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PostMappingDefaultValueController())
}

@RestController
@ResponseBody
open class PostMappingWithoutProducesInfoAndStringAsReturnValueController {

    @PostMapping("/todos")
    fun getAllTodos(): String = ""
}

@RestController
@ResponseBody
open class PutMappingOneMediaTypeIsExractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingOneMediaTypeIsExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PutMappingMultipleMediaTypesAreExractedCorrectlyController {

    @PutMapping("/todos", produces = [APPLICATION_XML_VALUE, TEXT_PLAIN_VALUE])
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingMultipleMediaTypesAreExractedCorrectlyController())
}

@RestController
@ResponseBody
open class PutMappingDefaultValueController {

    @PutMapping("/todos")
    fun getAllTodos() = ResponseEntity.status(200).body(PutMappingDefaultValueController())
}

@RestController
@ResponseBody
open class PutMappingWithoutProducesInfoAndStringAsReturnValueController {

    @PutMapping("/todos")
    fun getAllTodos(): String = ""
}