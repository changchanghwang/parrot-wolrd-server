package changchanghwang.parrotworldserver.services.verifcation.infrastructure

import changchanghwang.parrotworldserver.services.verifcation.domain.Verification
import org.springframework.stereotype.Repository

@Repository
class VerificationRepositoryImpl(
    private val verificationJpaRepository: VerificationJpaRepository,
) : VerificationRepository {
    override fun save(verification: Verification): Verification {
        return verificationJpaRepository.save(verification)
    }
}
