package br.com.alura.orgs.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.alura.orgs.model.Produto

@Dao
interface ProdutosDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): LiveData<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(produto: Produto)

    @Delete
    fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaProduto(id: Int): Produto?

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun carregaProduto(id: Int): LiveData<Produto>


}