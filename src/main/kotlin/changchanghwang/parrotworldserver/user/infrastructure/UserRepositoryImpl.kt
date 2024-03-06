package changchanghwang.parrotworldserver.user.infrastructure

import changchanghwang.parrotworldserver.user.domain.User
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val userRepository: UserRepository) {
    fun save(user: User): User {
        return userRepository.save(user)
    }
}
