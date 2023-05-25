package id.co.ukdw.techmate.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.ukdw.techmate.data.database.GadgetCase
import java.util.Locale

class HomeViewModel : ViewModel() {
    private val searchResultLiveData: MutableLiveData<List<GadgetCase>> = MutableLiveData()

    fun filter(gadgets: List<GadgetCase>, text: String) {
        val filteredList: MutableList<GadgetCase> = ArrayList()
        for (item in gadgets) {
            if (item.brand.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) ||
                item.goal.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                filteredList.add(item)
            }
        }
        searchResultLiveData.value = filteredList
    }

    fun getSearchResultLiveData(): LiveData<List<GadgetCase>> = searchResultLiveData
}