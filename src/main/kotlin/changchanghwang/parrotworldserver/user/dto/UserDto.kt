package changchanghwang.parrotworldserver.user.dto

class UserDto {
    data class SignUpRequest(
        val email: String,
        val password: String,
        val passwordConfirm: String,
        val nickName: String,
    )
}
