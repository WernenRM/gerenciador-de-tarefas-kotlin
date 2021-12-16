package brcom.wernen.gerenciadortarefas.controllers
import brcom.wernen.gerenciadortarefas.models.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/usuario")
class UsuarioController {

    @GetMapping
    fun obterUsuario() = User(1, "Usuário Teste", "admin@admin.com", "")
}