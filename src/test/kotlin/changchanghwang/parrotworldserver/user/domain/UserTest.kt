package changchanghwang.parrotworldserver.user.domain

import changchanghwang.parrotworldserver.user.domain.services.ValidateUserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class UserTest {
    @Nested
    inner class OfMethodTest {
        @Mock
        private lateinit var validateUserService: ValidateUserService

        @Test
        fun `of method를 사용하면 User 객체를 생성 할 수 있다`() {
            // given
            given(
                validateUserService.validateSignUp("test@test.com", "testNickName", "testPassword", "testPassword"),
            ).willReturn("hashedTestPassword")

            // when
            val user = User.of("test@test.com", "testPassword", "testPassword", "testNickName", validateUserService)

            // then
            Assertions.assertThat(user.email).isEqualTo("test@test.com")
            Assertions.assertThat(user.nickName).isEqualTo("testNickName")
            Assertions.assertThat(user.password).isEqualTo("hashedTestPassword")
        }
    }
}
