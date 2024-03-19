package changchanghwang.parrotworldserver.services.verifcation.infrastructure

import changchanghwang.parrotworldserver.services.verifcation.domain.Verification
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationJpaRepository : JpaRepository<Verification, Long>
