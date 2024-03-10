package changchanghwang.parrotworldserver.users.application

import changchanghwang.parrotworldserver.users.domain.User
import changchanghwang.parrotworldserver.users.domain.services.ValidateUserService
import changchanghwang.parrotworldserver.users.dto.UserDto
import changchanghwang.parrotworldserver.users.infrastructure.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val validateUserService: ValidateUserService) {
    fun signUp(signUpRequest: UserDto.SignUpRequest) {
        val user =
            User.of(
                signUpRequest.email,
                signUpRequest.password,
                signUpRequest.nickName,
                validateUserService,
            )

        userRepository.save(user)
    }
}
