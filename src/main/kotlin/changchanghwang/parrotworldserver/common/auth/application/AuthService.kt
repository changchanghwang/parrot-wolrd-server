package changchanghwang.parrotworldserver.common.auth.application

import changchanghwang.parrotworldserver.common.auth.dto.AuthDto
import changchanghwang.parrotworldserver.common.exceptions.Unauthorized
import changchanghwang.parrotworldserver.services.members.infrastructure.MemberRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

data class RorateOutput(
    val memberId: Long,
    val refreshToken: String,
)

@Service
class AuthService(private val memberRepository: MemberRepository) {
    val secretKey: SecretKey =
        Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(
                System.getenv("JWT_SECRET"),
            ),
        )

    fun generateAccessToken(memberId: Long): String {
        val now = Date()
        println("$now , ${now.time} , ${now.time + 5 * 60 * 1000}")
        return Jwts.builder()
            .claim("id", memberId)
            .issuedAt(now)
            .expiration(Date(now.time + 5 * 60 * 1000))
            .signWith(secretKey)
            .compact()
    }

    fun generateRefreshToken(expiration: Date?): String {
        val now = Date()
        return Jwts.builder()
            .issuedAt(now)
            .expiration(expiration ?: Date(now.time + 14L * 24 * 60 * 60 * 1000))
            .signWith(secretKey)
            .compact()
    }

    fun validateAccessToken(token: String): Long {
        try {
            val jwt =
                Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
            return jwt.payload["id"].toString().toLong()
        } catch (e: Exception) {
            throw Unauthorized(e.message ?: "Invalid Token", "로그인에 실패하였습니다.")
        }
    }

    fun rotateRefreshToken(token: String): RorateOutput {
        try {
            val jwt =
                Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
            val member =
                memberRepository.findByRefreshToken(
                    token,
                ) ?: throw Unauthorized("There is no member with RefreshToken", "로그인에 실패하였습니다.")
            val newRefreshToken = generateRefreshToken(jwt.payload.expiration)
            member.updateRefreshToken(newRefreshToken)
            memberRepository.save(member)
            return RorateOutput(member.id!!, newRefreshToken)
        } catch (e: Exception) {
            throw Unauthorized(e.message ?: "Invalid Token", "로그인에 실패하였습니다.")
        }
    }

    fun renewAccessToken(refreshToken: String): AuthDto.RenewAccessTokenResponse {
        val rotateOutput = rotateRefreshToken(refreshToken)
        val accessToken = generateAccessToken(rotateOutput.memberId)
        return AuthDto.RenewAccessTokenResponse(accessToken, rotateOutput.refreshToken)
    }
}
