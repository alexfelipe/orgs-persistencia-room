package br.com.alura.orgs.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.orgs.database.converter.Converters
import br.com.alura.orgs.model.Produto

private const val NOME_BANCO = "orgs.db"

@Database(
    version = 2,
    entities = [Produto::class]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun criaBanco(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            NOME_BANCO
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}