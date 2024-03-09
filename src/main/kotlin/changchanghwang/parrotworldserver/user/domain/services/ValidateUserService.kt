package changchanghwang.parrotworldserver.user.domain.services

import changchanghwang.parrotworldserver.common.auth.AuthService
import changchanghwang.parrotworldserver.user.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class ValidateUserService(private val userRepository: UserRepository, private val authService: AuthService) {
    fun validateSignUp(
        email: String,
        nickName: String,
        password: String,
        passwordConfirm: String,
    ): String {
        val isExistEmail = userRepository.checkByEmail(email)
        val isExistNickName = userRepository.checkByNickName(nickName)
        if (isExistEmail) {
            throw Exception("Email already exists")
        }

        if (password != passwordConfirm) {
            throw Exception("Password and password confirm are not same")
        }

        return authService.hashPassword(password)
    }
}
