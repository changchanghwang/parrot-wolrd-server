package changchanghwang.parrotworldserver.services.verifcation.application

import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import changchanghwang.parrotworldserver.common.mail.EmailService
import changchanghwang.parrotworldserver.services.members.infrastructure.MemberRepository
import changchanghwang.parrotworldserver.services.verifcation.domain.Verification
import changchanghwang.parrotworldserver.services.verifcation.domain.VerificationType
import changchanghwang.parrotworldserver.services.verifcation.dto.VerificationDto
import changchanghwang.parrotworldserver.services.verifcation.infrastructure.VerificationRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class VerificationService(
    private val verificationRepository: VerificationRepository,
    private val emailService: EmailService,
    private val templateEngine: TemplateEngine,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun start(startRequestDto: VerificationDto.StartRequest): VerificationDto.StartResponse {
        if (startRequestDto.type == VerificationType.SIGNUP) {
            if (memberRepository.checkByEmail(startRequestDto.email)) {
                throw BadRequest("Email(${startRequestDto.email}) already exists.", "이미 존재하는 이메일입니다.")
            }
        }

        val verification =
            Verification.of(startRequestDto.email, startRequestDto.type)

        val savedVerification = verificationRepository.save(verification)
        println(savedVerification.id)
        send(verification) // TODO: 추후 event 처리로 변경

        return VerificationDto.StartResponse(savedVerification.id!!)
    }

    @Transactional
    fun confirm(
        id: Long,
        confirmRequestDto: VerificationDto.ConfirmRequestBody,
    ) {
        val verification =
            verificationRepository.findOneOrFail(id)

        verification.verify(confirmRequestDto.code)

        verificationRepository.save(verification)
    }

    private fun send(verification: Verification) {
        val context = Context()
        var title = ""
        var content = ""
        if (verification.type == VerificationType.SIGNUP) {
            context.setVariable("verificationCode", verification.code)
            content = templateEngine.process("sign-up-validation-email", context)
            title = "[앵무새 세상] 이메일 인증 코드입니다."
        }
        emailService.sendEmail(verification.email, title, content)
    }
}
