package changchanghwang.parrotworldserver.user.domain

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "nickName", nullable = false, unique = true)
    val nickName: String,
)
