package brcom.wernen.gerenciadortarefas.dtos

data class LoginResponseDTO( val nome: String, val email: String, val token: String = "" )