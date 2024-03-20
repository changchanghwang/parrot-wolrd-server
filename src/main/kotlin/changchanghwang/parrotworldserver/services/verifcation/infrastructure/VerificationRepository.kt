package changchanghwang.parrotworldserver.services.verifcation.infrastructure

import changchanghwang.parrotworldserver.services.verifcation.domain.Verification

interface VerificationRepository {
    fun save(verification: Verification): Verification

    fun findOneOrFail(id: Long): Verification
}
