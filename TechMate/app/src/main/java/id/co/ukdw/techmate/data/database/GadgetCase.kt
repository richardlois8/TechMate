package id.co.ukdw.techmate.data.database

import android.os.Parcelable
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
    val desc : String
) : java.io.Serializable {
    operator fun iterator(): Iterator<Pair<String, Any>> {
        return listOf("brand" to brand, "memory" to memory, "ram" to ram, "price" to price, "features" to features, "desc" to desc, "goal" to goal).iterator()
    }
}