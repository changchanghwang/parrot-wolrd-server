package changchanghwang.parrotworldserver.user.application

import changchanghwang.parrotworldserver.user.domain.User
import changchanghwang.parrotworldserver.user.domain.services.ValidateUserService
import changchanghwang.parrotworldserver.user.dto.UserDto
import changchanghwang.parrotworldserver.user.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val validateUserService: ValidateUserService) {
    fun signUp(signUpRequest: UserDto.SignUpRequest): User {
        val user =
            User.of(
                signUpRequest.email,
                signUpRequest.password,
                signUpRequest.passwordConfirm,
                signUpRequest.nickName,
                validateUserService,
            )
        return userRepository.save(
            user,
        )
    }
}
