package br.com.alura.orgs.database

import android.content.Context
import androidx.room.*
import br.com.alura.orgs.database.converter.BigDecimalConverter
import br.com.alura.orgs.database.dao.ProdutosDao
import br.com.alura.orgs.model.Produto

@Database(
    entities = [Produto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BigDecimalConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutosDao

    companion object {
        private lateinit var db: AppDatabase

        fun criaBanco(context: Context): AppDatabase {
            if(::db.isInitialized) return db
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
            return db
        }
    }

}