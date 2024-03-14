package changchanghwang.parrotworldserver.members.infrastructure

import changchanghwang.parrotworldserver.members.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(private val memberJpaRepository: MemberJpaRepository) : MemberRepository {
    override fun save(user: Member): Member {
        return memberJpaRepository.save(user)
    }

    override fun checkByEmail(email: String): Boolean {
        val user = memberJpaRepository.findByEmail(email)
        return user != null
    }

    override fun checkByNickName(nickName: String): Boolean {
        val user = memberJpaRepository.findByNickName(nickName)
        return user != null
    }

    override fun findByRefreshToken(refreshToken: String): Member? {
        return memberJpaRepository.findByRefreshToken(
            refreshToken,
        )
    }

    override fun findOneOrFail(id: Long): Member {
        return memberJpaRepository.findById(id).orElseThrow()
    }

    override fun findByEmail(email: String): Member? {
        return memberJpaRepository.findByEmail(email)
    }
}
