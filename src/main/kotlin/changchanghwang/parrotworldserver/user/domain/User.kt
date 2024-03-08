package changchanghwang.parrotworldserver.user.domain

import changchanghwang.parrotworldserver.user.domain.services.ValidateUserService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.mindrot.jbcrypt.BCrypt

@Entity
class User private constructor(
    @Column(name = "email", nullable = false, unique = true) val email: String,
    @Column(name = "password", nullable = false) val password: String,
    @Column(name = "nickName", nullable = false, unique = true) val nickName: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    companion object {
        fun of(
            email: String,
            password: String,
            passwordConfirm: String,
            nickName: String,
            validateUserService: ValidateUserService,
        ): User {
            validateUserService.validateSignUp(email, password, passwordConfirm)
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            return User(email, hashedPassword, nickName)
        }
    }
}
