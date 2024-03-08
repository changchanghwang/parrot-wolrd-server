package changchanghwang.parrotworldserver.user.domain.services

import changchanghwang.parrotworldserver.user.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class ValidateUserService(private val userRepository: UserRepository) {
    fun validateSignUp(
        email: String,
        password: String,
        passwordConfirm: String,
    ) {
        val isExistEmail = userRepository.checkByEmail(email)
        if (isExistEmail) {
            throw Exception("Email already exists")
        }

        if (password != passwordConfirm) {
            throw Exception("Password and password confirm are not same")
        }
    }
}
