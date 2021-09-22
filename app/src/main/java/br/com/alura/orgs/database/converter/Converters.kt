package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun paraDouble(valor: BigDecimal?): Double {
        return valor?.toDouble() ?: 0.0
    }

    @TypeConverter
    fun paraBigDecimal(valor: Double?): BigDecimal {
        return valor?.let {
            BigDecimal(valor.toString())
        } ?: BigDecimal.ZERO
    }

}