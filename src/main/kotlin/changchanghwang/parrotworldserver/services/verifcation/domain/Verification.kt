package changchanghwang.parrotworldserver.services.verifcation.domain

import changchanghwang.parrotworldserver.common.ddd.Aggregate
import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.Date
import kotlin.random.Random

@Entity(name = "verification")
class Verification private constructor(
    @Column(nullable = false) val email: String,
    @Column(nullable = false) val code: String,
    @Column(nullable = false) val expiredAt: Date,
    @Column(nullable = false) val sentAt: Date,
    @Column(nullable = false) val type: VerificationType,
) : Aggregate() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = true)
    var verifiedAt: Date? = null

    companion object {
        fun of(
            email: String,
            type: VerificationType,
        ): Verification {
            val code = List(6) { Random.nextInt(10) }.joinToString("")
            val expiredAt = Date(System.currentTimeMillis() + 3 * 60 * 1000)
            return Verification(email, code, expiredAt, Date(), type)
        }
    }

    fun verify(code: String) {
        if (this.code != code) {
            throw BadRequest("Invalid code", "인증 코드가 올바르지 않습니다.")
        }
        if (this.expiredAt < Date()) {
            throw BadRequest("Expired code", "인증 코드가 만료되었습니다.")
        }
        this.verifiedAt = Date()
    }
}
