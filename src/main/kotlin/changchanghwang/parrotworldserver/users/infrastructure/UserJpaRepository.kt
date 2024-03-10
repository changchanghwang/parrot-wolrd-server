package changchanghwang.parrotworldserver.users.infrastructure

import changchanghwang.parrotworldserver.users.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByNickName(nickName: String): User?
}
