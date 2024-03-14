package changchanghwang.parrotworldserver.members.presentation

import changchanghwang.parrotworldserver.members.application.MemberService
import changchanghwang.parrotworldserver.members.dto.MemberDto
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    fun signUp(
        @Valid @RequestBody signUpRequest: MemberDto.SignUpRequest,
    ) {
        memberService.signUp(signUpRequest)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @Valid @RequestBody signInRequest: MemberDto.SignInRequest,
        response: HttpServletResponse,
    ) {
        val signInResponseDto = memberService.signIn(signInRequest)
        val accessTokenCookie = Cookie("accessToken", signInResponseDto.accessToken)
        accessTokenCookie.path = "/"
        val refreshTokenCookie = Cookie("refreshToken", signInResponseDto.refreshToken)
        refreshTokenCookie.path = "/"
        refreshTokenCookie.secure = false // TODO: https 적용 후 true로 변경
        refreshTokenCookie.isHttpOnly = false // TODO: https 적용 후 true로 변경
        response.addCookie(accessTokenCookie)
        response.addCookie(refreshTokenCookie)
    }
}
