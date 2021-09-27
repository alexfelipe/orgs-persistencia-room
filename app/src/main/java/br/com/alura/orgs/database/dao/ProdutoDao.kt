package br.com.alura.orgs.database.dao

import androidx.room.*
import br.com.alura.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Insert
    fun salva(produto: Produto)

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Update
    fun edita(produto: Produto)

    @Delete
    fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaProdutoPor(id: Long): Produto?

    @Update
    fun atualiza(produto: Produto)

}