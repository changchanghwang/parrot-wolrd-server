package changchanghwang.parrotworldserver.common.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleRestException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        val defaultMessage = e.fieldError?.defaultMessage
        return ResponseEntity(defaultMessage, HttpStatus.BAD_REQUEST)
    }
}
