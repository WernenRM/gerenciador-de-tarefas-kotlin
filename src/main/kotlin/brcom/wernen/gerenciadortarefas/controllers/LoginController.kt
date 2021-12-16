package brcom.wernen.gerenciadortarefas.controllers

import brcom.wernen.gerenciadortarefas.dtos.ErroDTO
import brcom.wernen.gerenciadortarefas.dtos.LoginDTO
import brcom.wernen.gerenciadortarefas.dtos.LoginResponseDTO
import brcom.wernen.gerenciadortarefas.utils.JWRUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/login")
class LoginController {

    private val LOGIN_MOCK = "admin@admin.com"
    private val PASSWORD_MOCK = "Admin@1234"


    @PostMapping
  fun  efetuarLogin(@RequestBody dto : LoginDTO) : ResponseEntity<Any>{
    try{
        if(dto == null || dto.login.isNullOrBlank() || dto.login.isNullOrEmpty()
                || dto.password.isNullOrEmpty() || dto.password.isNullOrBlank()
                || dto.login != LOGIN_MOCK || dto.password != PASSWORD_MOCK){
            return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(),
            "Parametro invalido"), HttpStatus.BAD_REQUEST)
        }

        val userId = 1
        val token = JWRUtils().TokenGenerator(userId.toString())

        val userTest = LoginResponseDTO("usuario teste", LOGIN_MOCK, token)
        return ResponseEntity(userTest, HttpStatus.OK)

    }catch (e: Exception){
       return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "Não foi possivel efetuar o login, Tente Novamente"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}