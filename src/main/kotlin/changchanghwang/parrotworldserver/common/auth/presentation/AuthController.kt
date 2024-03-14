package changchanghwang.parrotworldserver.common.auth.presentation

import changchanghwang.parrotworldserver.common.auth.application.AuthService
import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/renew")
    fun renewAccessToken(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val refreshToken =
            request.cookies.find {
                it.name == "refreshToken"
            }?.value ?: throw BadRequest("There is no token", "로그인에 실패하였습니다.")
        val renewAccessTokenResponseDto = authService.renewAccessToken(refreshToken)
        val accessTokenCookie = Cookie("accessToken", renewAccessTokenResponseDto.accessToken)
        accessTokenCookie.path = "/"
        val refreshTokenCookie = Cookie("refreshToken", renewAccessTokenResponseDto.refreshToken)
        refreshTokenCookie.path = "/"
        refreshTokenCookie.secure = false // TODO: https 적용 후 true로 변경
        refreshTokenCookie.isHttpOnly = false // TODO: https 적용 후 true로 변경
        response.addCookie(accessTokenCookie)
        response.addCookie(refreshTokenCookie)
    }
}
