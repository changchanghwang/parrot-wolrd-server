package changchanghwang.parrotworldserver.users.dto

class UserDto {
    data class SignUpRequest(
        val email: String,
        val password: String,
        val passwordConfirm: String,
        val nickName: String,
    )
}
