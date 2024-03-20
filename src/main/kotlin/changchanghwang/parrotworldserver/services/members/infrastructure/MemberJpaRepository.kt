package changchanghwang.parrotworldserver.services.members.infrastructure

import changchanghwang.parrotworldserver.services.members.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?

    fun findByNickName(nickName: String): Member?

    fun findByRefreshToken(refreshToken: String): Member?
}
