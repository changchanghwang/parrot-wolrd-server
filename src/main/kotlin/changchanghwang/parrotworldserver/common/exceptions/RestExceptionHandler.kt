package changchanghwang.parrotworldserver.common.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(RestException::class)
    fun handleRestException(e: RestException): ResponseEntity<String> {
        // TODO: logging on server
        println(e.message)
        return ResponseEntity(e.errorMessage, e.statusCode)
    }
}
