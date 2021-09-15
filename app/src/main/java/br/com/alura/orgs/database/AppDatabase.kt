package br.com.alura.orgs.database

import android.content.Context
import androidx.room.*
import br.com.alura.orgs.database.converter.BigDecimalConverter
import br.com.alura.orgs.database.dao.ProdutosDao
import br.com.alura.orgs.model.Produto

private const val NOME_BANCO_DE_DADOS = "orgs.db"

@Database(
    entities = [Produto::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutosDao

    companion object {
        private lateinit var db: AppDatabase

        fun criaBanco(context: Context): AppDatabase {
            if (::db.isInitialized) return db
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NOME_BANCO_DE_DADOS
            ).build()
            return db
        }
    }

}