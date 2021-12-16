package brcom.wernen.gerenciadortarefas.filters

import brcom.wernen.gerenciadortarefas.authorization
import brcom.wernen.gerenciadortarefas.bearer
import brcom.wernen.gerenciadortarefas.impl.UserDetailmpl
import brcom.wernen.gerenciadortarefas.models.User
import brcom.wernen.gerenciadortarefas.utils.JWRUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTFilterAuthorization(authenticationManager: AuthenticationManager, val jwrUtils: JWRUtils)
    : BasicAuthenticationFilter(authenticationManager){

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val authorizationHeader = request.getHeader(authorization)

        if( authorizationHeader != null && authorizationHeader.startsWith(bearer)){
            val autorizado = getAuthentication(authorizationHeader)
            SecurityContextHolder.getContext().authentication = autorizado
        }

        chain.doFilter(request,response)
        }

    private fun getAuthentication(athorization: String): UsernamePasswordAuthenticationToken {
      val token = authorization.substring(7)
        if (jwrUtils.isTokenValitention(token)){
            val idString = jwrUtils.getIdUser(token)
            if(!idString.isNullOrBlank() && !idString.isNullOrEmpty()){
                val user = User(idString.toLong(), "Usuario Teste", "admin@admin.com","Admin1234@")
                val userImpl = UserDetailmpl(user)
                return UsernamePasswordAuthenticationToken(userImpl, null, userImpl.authorities)
            }
        }

        throw UsernameNotFoundException("Token informado não está valido, ou não tem uma informação de identificação do usuario")
    }
}