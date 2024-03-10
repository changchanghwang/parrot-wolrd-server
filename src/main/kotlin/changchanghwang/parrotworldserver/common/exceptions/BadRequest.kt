package changchanghwang.parrotworldserver.common.exceptions

import org.springframework.http.HttpStatus

class BadRequest(message: String, errorMessage: String?) : RestException(
    HttpStatus.BAD_REQUEST,
    message,
    errorMessage,
)
