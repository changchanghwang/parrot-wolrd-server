package changchanghwang.parrotworldserver.users.domain.services

import changchanghwang.parrotworldserver.common.auth.AuthService
import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import changchanghwang.parrotworldserver.users.infrastructure.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class ValidateUserServiceTest {
    @DisplayName("ValidateSignUp 메서드 테스트")
    @Nested
    inner class ValidateSignUpTest {
        @InjectMocks
        private lateinit var validateUserService: ValidateUserService

        @Mock
        private lateinit var userRepository: UserRepository

        @Mock
        private lateinit var authService: AuthService

        @Test
        @DisplayName("같은 이메일을 가진 유저가 존재하면 에러를 던진다")
        fun errorWhenSameEmail() {
            // given
            given(userRepository.checkByEmail("test@test.com")).willReturn(true)

            // then
            Assertions.assertThatThrownBy {
                validateUserService.validateSignUp(
                    "test@test.com",
                    "testNickName",
                    "testPassword",
                    "testPassword",
                )
            }.isInstanceOf(BadRequest::class.java).hasMessage("Email already exists.")
        }

        @Test
        @DisplayName("같은 닉네임을 가진 유저가 존재하면 에러를 던진다")
        fun errorWhenSameNickName() {
            // given
            given(userRepository.checkByEmail("test@test.com")).willReturn(false)
            given(userRepository.checkByNickName("testNickName")).willReturn(true)

            // then
            Assertions.assertThatThrownBy {
                validateUserService.validateSignUp(
                    "test@test.com",
                    "testNickName",
                    "testPassword",
                    "testPassword",
                )
            }.isInstanceOf(BadRequest::class.java).hasMessage("NickName already exists.")
        }

        @Test
        @DisplayName("비밀번호와 비밀번호 확인 입력값이 다르면 에러를 던진다.")
        fun errorWhenPasswordDifferent() {
            // given
            given(userRepository.checkByEmail("test@test.com")).willReturn(false)
            given(userRepository.checkByNickName("testNickName")).willReturn(false)

            // then
            Assertions.assertThatThrownBy {
                validateUserService.validateSignUp(
                    "test@test.com",
                    "testNickName",
                    "testPassword",
                    "testPassword123",
                )
            }.isInstanceOf(BadRequest::class.java).hasMessage("Password and password confirm are not same.")
        }
    }
}
