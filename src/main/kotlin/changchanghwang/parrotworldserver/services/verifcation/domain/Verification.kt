package changchanghwang.parrotworldserver.services.verifcation.domain

import changchanghwang.parrotworldserver.common.ddd.Aggregate
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
    @Column(nullable = true) val verifiedAt: Date? = null,
) : Aggregate() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

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
}
