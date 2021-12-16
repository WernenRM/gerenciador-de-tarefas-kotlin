package brcom.wernen.gerenciadortarefas.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JWRUtils {

    private val keySecurity = "ChavedeSeguranca"

    fun TokenGenerator(userId: String): String {
        return Jwts.builder()
            .setSubject(userId)
            .signWith(SignatureAlgorithm.HS512, keySecurity.toByteArray())
            .compact()
    }

    fun isTokenValitention(token: String): Boolean {
        val claims = getClaimsToken(token)

        if (claims != null) {
            val userId = claims.subject
            if (!userId.isNullOrEmpty() && !userId.isNullOrBlank()) {
                return true
            }
        }

        return false
    }

    private fun getClaimsToken(token: String) = try {
        Jwts.parser().setSigningKey(keySecurity.toByteArray()).parseClaimsJws(token).body
    } catch (exception: Exception) {
        null
    }

    fun getIdUser(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }
}
