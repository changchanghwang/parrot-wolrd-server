package changchanghwang.parrotworldserver.services.verifcation.dto

import changchanghwang.parrotworldserver.services.verifcation.domain.VerificationType

class VerificationDto {
    data class StartRequest(
        val email: String,
        val type: VerificationType,
    )

    data class StartResponse(
        val id: Long,
    )
}
