package br.com.alura.orgs.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Produto ADD COLUMN quantidade INTEGER NOT NULL DEFAULT 0")
    }

}

val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """CREATE TABLE IF NOT EXISTS `Produto_novo` 
            (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
            `nome` TEXT NOT NULL, 
            `descricao` TEXT NOT NULL, 
            `valor` REAL NOT NULL, 
            `imagem` TEXT)"""
        )

        database.execSQL(
            """INSERT INTO Produto_novo (id, nome, descricao, valor, imagem) 
                SELECT id, nome, descricao, valor, imagem FROM Produto"""
        );

        database.execSQL("DROP TABLE Produto");

        database.execSQL("ALTER TABLE Produto_novo RENAME TO Produto");
    }

}