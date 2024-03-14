package changchanghwang.parrotworldserver.members.domain.services

import changchanghwang.parrotworldserver.common.exceptions.BadRequest
import changchanghwang.parrotworldserver.members.infrastructure.MemberRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class ValidateMemberService(
    private val memberRepository: MemberRepository,
) {
    fun validateSignUp(
        email: String,
        nickName: String,
        password: String,
    ): String {
        val isExistEmail = memberRepository.checkByEmail(email)
        val isExistNickName = memberRepository.checkByNickName(nickName)
        if (isExistEmail) {
            throw BadRequest("Email already exists.", "해당 이메일로 가입할 수 없습니다.")
        }
        if (isExistNickName) {
            throw BadRequest("NickName already exists.", "해당 닉네임으로 가입할 수 없습니다.")
        }

        return hashPassword(password)
    }

    private fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun comparePassword(
        password: String,
        hashedPassword: String,
    ): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}
