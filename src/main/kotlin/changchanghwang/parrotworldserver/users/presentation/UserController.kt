package changchanghwang.parrotworldserver.users.presentation

import changchanghwang.parrotworldserver.users.application.UserService
import changchanghwang.parrotworldserver.users.dto.UserDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    fun signUp(
        @Valid @RequestBody signUpRequest: UserDto.SignUpRequest,
    ) {
        userService.signUp(signUpRequest)
    }
}
