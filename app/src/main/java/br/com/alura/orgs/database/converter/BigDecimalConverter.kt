package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {

    @TypeConverter
    fun deBigDecimal(valor: BigDecimal): Double {
        return valor.toDouble()
    }

    @TypeConverter
    fun paraBigDecimal(valor: Double?): BigDecimal {
        return valor?.let{
            BigDecimal(it.toString())
        } ?: BigDecimal.ZERO
    }

}