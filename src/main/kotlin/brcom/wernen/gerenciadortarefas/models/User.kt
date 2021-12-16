package brcom.wernen.gerenciadortarefas.models

data class User (
    val id : Long,
    val nome: String,
    val email : String,
    val password: String)