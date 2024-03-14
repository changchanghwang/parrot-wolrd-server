package changchanghwang.parrotworldserver.common.exceptions

import org.springframework.http.HttpStatus

open class RestException(val statusCode: HttpStatus, message: String, errorMessage: String?) : RuntimeException(
    message,
) {
    val errorMessage = errorMessage ?: "Something went wrong. Please try again later."
}
