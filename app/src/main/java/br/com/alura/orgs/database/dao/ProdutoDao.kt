package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Insert
    fun salva(produto: Produto)

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

}