package brcom.wernen.gerenciadortarefas.controllers

import brcom.wernen.gerenciadortarefas.dtos.ErroDTO
import brcom.wernen.gerenciadortarefas.dtos.LoginDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/login")
class LoginController {

  @PostMapping
  fun  efetuarLogin(@RequestBody dto : LoginDTO) : ResponseEntity<Any>{
    try{
        if(dto == null || dto.login.isNullOrBlank() || dto.login.isNullOrEmpty()
                || dto.password.isNullOrEmpty() || dto.password.isNullOrBlank()){
            return ResponseEntity(ErroDTO(HttpStatus.BAD_REQUEST.value(),
            "Parametro invalido"), HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity("Login Efetuado", HttpStatus.OK)

    }catch (e: Exception){
       return ResponseEntity(ErroDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "Não foi possivel efetuar o login, Tente Novamente"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
  }
}