package changchanghwang.parrotworldserver.user.infrastructure

import changchanghwang.parrotworldserver.user.domain.User

interface UserRepository {
    fun save(user: User): User

    fun checkByEmail(email: String): Boolean

    fun checkByNickName(nickName: String): Boolean
}
