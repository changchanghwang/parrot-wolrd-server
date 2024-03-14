package changchanghwang.parrotworldserver.common.exceptions

import org.springframework.http.HttpStatus

class NotFoundResource(message: String, errorMessage: String?) : RestException(
    HttpStatus.INTERNAL_SERVER_ERROR,
    message,
    errorMessage,
)
