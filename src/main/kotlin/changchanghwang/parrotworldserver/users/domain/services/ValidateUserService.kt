package changchanghwang.parrotworldserver.users.domain.services

import changchanghwang.parrotworldserver.common.auth.AuthService
import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import changchanghwang.parrotworldserver.users.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class ValidateUserService(private val userRepository: UserRepository, private val authService: AuthService) {
    fun validateSignUp(
        email: String,
        nickName: String,
        password: String,
    ): String {
        val isExistEmail = userRepository.checkByEmail(email)
        val isExistNickName = userRepository.checkByNickName(nickName)
        if (isExistEmail) {
            throw BadRequest("Email already exists.", "해당 이메일로 가입할 수 없습니다.")
        }
        if (isExistNickName) {
            throw BadRequest("NickName already exists.", "해당 닉네임으로 가입할 수 없습니다.")
        }

        return authService.hashPassword(password)
    }
}
