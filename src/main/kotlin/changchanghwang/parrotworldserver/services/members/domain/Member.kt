package changchanghwang.parrotworldserver.services.members.domain

import changchanghwang.parrotworldserver.common.ddd.Aggregate
import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import changchanghwang.parrotworldserver.services.members.domain.services.ValidateMemberService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "member")
class Member private constructor(
    @Column(nullable = false, unique = true) val email: String,
    @Column(nullable = false) val password: String,
    @Column(nullable = false, unique = true) val nickName: String,
) : Aggregate() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = true)
    var refreshToken: String? = null

    @Column(nullable = false)
    var role: MemberRole = MemberRole.USER

    companion object {
        fun of(
            email: String,
            password: String,
            nickName: String,
            validateMemberService: ValidateMemberService,
        ): Member {
            val hashedPassword = validateMemberService.validateSignUp(email, nickName, password)
            return Member(email, hashedPassword, nickName)
        }
    }

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }

    fun validatePassword(
        password: String,
        validateMemberService: ValidateMemberService,
    ) {
        if (!validateMemberService.comparePassword(password, this.password)) {
            throw BadRequest("Invalid password", "이메일 또는 패스워드가 올바르지 않습니다.")
        }
    }
}
