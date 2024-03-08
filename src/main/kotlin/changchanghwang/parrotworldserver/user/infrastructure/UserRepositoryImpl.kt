package changchanghwang.parrotworldserver.user.infrastructure

import changchanghwang.parrotworldserver.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val userJpaRepository: UserJpaRepository) : UserRepository {
    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun checkByEmail(email: String): Boolean {
        val user = userJpaRepository.findByEmail(email)
        return user != null
    }
}
