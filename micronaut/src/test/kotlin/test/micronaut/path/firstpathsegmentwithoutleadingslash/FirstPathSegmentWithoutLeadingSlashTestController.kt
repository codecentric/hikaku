package test.micronaut.path.firstpathsegmentwithoutleadingslash

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("todo")
class FirstPathSegmentWithoutLeadingSlashTestController {

    @Get("/list")
    fun todos() { }
}