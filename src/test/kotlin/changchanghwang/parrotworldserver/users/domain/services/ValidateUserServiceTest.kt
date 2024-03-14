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
                )
            }.isInstanceOf(BadRequest::class.java).hasMessage("NickName already exists.")
        }

        @Test
        @DisplayName("validation을 통과하면 password를 hash해서 반환한다")
        fun returnHashedPassword() {
            // given
            given(userRepository.checkByEmail("test@test.com")).willReturn(false)
            given(userRepository.checkByNickName("testNickName")).willReturn(false)
            given(authService.hashPassword("testPassword")).willReturn("hashedTestPassword")

            // then
            Assertions.assertThat(
                validateUserService.validateSignUp(
                    "test@test.com",
                    "testNickName",
                    "testPassword",
                ),
            ).isEqualTo("hashedTestPassword")
        }
    }
}
