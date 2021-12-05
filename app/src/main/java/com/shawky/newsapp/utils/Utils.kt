package com.shawky.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormat(date:String) : String{
        return try {
            val parsedDate = OffsetDateTime.parse(date)
            val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault())
            formatter.format(parsedDate)
        }catch (e:Exception){
            ""
        }
    }
}