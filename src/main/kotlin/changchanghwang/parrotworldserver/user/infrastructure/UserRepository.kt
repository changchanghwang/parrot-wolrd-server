package changchanghwang.parrotworldserver.user.infrastructure

import changchanghwang.parrotworldserver.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
