package changchanghwang.parrotworldserver.services.verifcation.presentation

import changchanghwang.parrotworldserver.services.verifcation.application.VerificationService
import changchanghwang.parrotworldserver.services.verifcation.dto.VerificationDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/verifications")
class VerificationController(private val verificationService: VerificationService) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/emails")
    fun sendEmailVerification(
        @RequestBody startRequestDto: VerificationDto.StartRequest,
    ): VerificationDto.StartResponse {
        val startResponseDto = verificationService.start(startRequestDto)
        return startResponseDto
    }

    @PostMapping("/emails/{id}")
    fun verifyEmailCode(
        @PathVariable id: Long,
        @RequestBody confirmRequestDto: VerificationDto.ConfirmRequestBody,
    ) {
        verificationService.confirm(id, confirmRequestDto)
    }
}
