package brcom.wernen.gerenciadortarefas.models

import com.fasterxml.jackson.annotation.JsonBackReference
import nonapi.io.github.classgraph.json.Id
import java.time.LocalDate

//data class Tarefa (
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//    var nome: String = "",
//    var dataPrevistaConclusao : LocalDate = LocalDate.MIN,
//    var dataConclusao : LocalDate? = null,
//
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "idUsuario")
//    val usuario: User? = null
//)