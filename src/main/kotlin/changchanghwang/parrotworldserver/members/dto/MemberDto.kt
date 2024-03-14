package changchanghwang.parrotworldserver.members.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class MemberDto {
    data class SignUpRequest(
        @field:NotBlank(message = "이메일을 입력해주세요.")
        @field:Email(message = "이메일 형식이 올바르지 않습니다.")
        val email: String,
        @field:NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(
            regexp = "^.*(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*$",
            message = "비밀번호 형식이 올바르지 않습니다.",
        )
        val password: String,
        @field:NotBlank(message = "닉네임을 입력해주세요.")
        val nickName: String,
    )

    data class SignInRequest(
        @field:NotBlank(message = "이메일을 입력해주세요.")
        @field:Email(message = "이메일 형식이 올바르지 않습니다.")
        val email: String,
        @field:NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(
            regexp = "^.*(?=^.{8,}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*$",
            message = "비밀번호 형식이 올바르지 않습니다.",
        )
        val password: String,
    )

    data class SignInResponse(
        @field:NotBlank(message = "토큰이 발급되지 않았습니다.")
        val accessToken: String,
        @field:NotBlank(message = "토큰이 발급되지 않았습니다.")
        val refreshToken: String,
    )
}
