package changchanghwang.parrotworldserver.common.exceptions
import org.springframework.http.HttpStatus

class Unauthorized(message: String, errorMessage: String?) : RestException(
    HttpStatus.UNAUTHORIZED,
    message,
    errorMessage,
)
