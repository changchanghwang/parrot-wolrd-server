<<<<<<<< Updated upstream:src/test/kotlin/changchanghwang/parrotworldserver/user/domain/UserTest.kt
package changchanghwang.parrotworldserver.user.domain

import changchanghwang.parrotworldserver.user.domain.services.ValidateUserService
========
package changchanghwang.parrotworldserver.members.domain

import changchanghwang.parrotworldserver.members.domain.services.ValidateMemberService
>>>>>>>> Stashed changes:src/test/kotlin/changchanghwang/parrotworldserver/members/domain/MemberTest.kt
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(value = [MockitoExtension::class])
class MemberTest {
    @Nested
    inner class OfMethodTest {
        @Mock
        private lateinit var validateMemberService: ValidateMemberService

        @Test
        fun `of method를 사용하면 User 객체를 생성 할 수 있다`() {
            // given
            given(
                validateMemberService.validateSignUp("test@test.com", "testNickName", "testPassword"),
            ).willReturn("hashedTestPassword")

            // when
            val member = Member.of("test@test.com", "testPassword", "testNickName", validateMemberService)

            // then
            Assertions.assertThat(member.email).isEqualTo("test@test.com")
            Assertions.assertThat(member.nickName).isEqualTo("testNickName")
            Assertions.assertThat(member.password).isEqualTo("hashedTestPassword")
        }
    }
}
