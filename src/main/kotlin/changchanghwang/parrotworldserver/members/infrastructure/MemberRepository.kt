package changchanghwang.parrotworldserver.members.infrastructure

import changchanghwang.parrotworldserver.members.domain.Member

interface MemberRepository {
    fun save(user: Member): Member

    fun checkByEmail(email: String): Boolean

    fun checkByNickName(nickName: String): Boolean

    fun findByRefreshToken(refreshToken: String): Member?

    fun findOneOrFail(id: Long): Member

    fun findByEmail(email: String): Member?
}
