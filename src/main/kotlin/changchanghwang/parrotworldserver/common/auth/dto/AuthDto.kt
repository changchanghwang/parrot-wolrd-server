package changchanghwang.parrotworldserver.common.auth.dto

class AuthDto {
    data class RenewAccessTokenResponse(
        val accessToken: String,
        val refreshToken: String,
    )
}
