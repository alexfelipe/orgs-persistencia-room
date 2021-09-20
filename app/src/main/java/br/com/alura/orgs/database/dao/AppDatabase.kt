package br.com.alura.orgs.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alura.orgs.model.Produto

@Database(
    version = 1,
    entities = [Produto::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

}