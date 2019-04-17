package test.micronaut.path.innerpathsegmentwithoutleadingslash

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/todo")
class InnerPathSegmentWithoutLeadingSlashTestController {

    @Get("list")
    fun todos() { }
}