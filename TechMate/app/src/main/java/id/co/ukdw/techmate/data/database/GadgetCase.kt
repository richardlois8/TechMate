package id.co.ukdw.techmate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GadgetCase(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val brand : String,
    val memory : Int,
    var ram : Int,
    val price : Int,
    val features : String,
    val image : String,
    val goal : String,
){
    operator fun iterator(): Iterator<Pair<String, Any>> {
        return listOf("brand" to brand, "memory" to memory, "ram" to ram, "harga" to price, "fitur" to features, "goal" to goal).iterator()
    }
}