package br.com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
        fun criaBanco(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
        }
    }

}