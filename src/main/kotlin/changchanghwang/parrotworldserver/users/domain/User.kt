package changchanghwang.parrotworldserver.users.domain

import changchanghwang.parrotworldserver.users.domain.services.ValidateUserService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User private constructor(
    @Column(name = "email", nullable = false, unique = true) val email: String,
    @Column(name = "password", nullable = false) val password: String,
    @Column(name = "nickName", nullable = false, unique = true) val nickName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "refreshToken", nullable = true)
    var refreshToken: String? = null

    companion object {
        fun of(
            email: String,
            password: String,
            passwordConfirm: String,
            nickName: String,
            validateUserService: ValidateUserService,
        ): User {
            val hashedPassword = validateUserService.validateSignUp(email, nickName, password, passwordConfirm)
            return User(email, hashedPassword, nickName)
        }
    }
}
