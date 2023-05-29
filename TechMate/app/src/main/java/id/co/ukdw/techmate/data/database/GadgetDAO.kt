package id.co.ukdw.techmate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GadgetDAO {
    @Query("SELECT * FROM GadgetCase")
    fun getAllGadget(): List<GadgetCase>

    @Query("SELECT * FROM GadgetCase WHERE id = :id")
    fun getGadgetDetail(id: Int): GadgetCase

    @Insert
    fun insertGadget(gadget: List<GadgetCase>)

    @Insert
    fun insertGadget(gadget: GadgetCase)
}