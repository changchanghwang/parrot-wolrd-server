package changchanghwang.parrotworldserver.user.presentation

import changchanghwang.parrotworldserver.user.application.UserService
import changchanghwang.parrotworldserver.user.dto.UserDto
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
        @RequestBody signUpRequest: UserDto.SignUpRequest,
    ) {
        userService.signUp(signUpRequest)
    }
}
