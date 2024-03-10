package changchanghwang.parrotworldserver.users.infrastructure

import changchanghwang.parrotworldserver.users.domain.User

interface UserRepository {
    fun save(user: User): User

    fun checkByEmail(email: String): Boolean

    fun checkByNickName(nickName: String): Boolean
}
