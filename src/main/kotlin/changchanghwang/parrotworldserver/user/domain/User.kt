package changchanghwang.parrotworldserver.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
class User(
    @Id
    var id: Long? = null
) {
}