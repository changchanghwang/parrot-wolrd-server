package changchanghwang.parrotworldserver.services.verifcation.dto

import changchanghwang.parrotworldserver.services.verifcation.domain.VerificationType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class VerificationDto {
    data class StartRequest(
        @field:NotBlank(message = "이메일을 입력해주세요.")
        @field:Email(message = "이메일 형식이 올바르지 않습니다.")
        val email: String,
        @field:NotBlank(message = "인증 타입을 입력해주세요")
        val type: VerificationType,
    )

    data class StartResponse(
        val id: Long,
    )

    data class ConfirmRequestBody(
        @field:NotBlank(message = "인증 코드를 입력해주세요.")
        val code: String,
    )
}
