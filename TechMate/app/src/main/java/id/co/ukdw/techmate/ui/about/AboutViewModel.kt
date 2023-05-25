package id.co.ukdw.techmate.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.co.ukdw.techmate.R

class AboutViewModel : ViewModel() {
    private val usersLiveData: MutableLiveData<List<UserData>> = MutableLiveData()

    fun setUsers() {
        val gadgets = listOf(
            UserData("Edwin Mahendra", R.drawable.user1_image),
            UserData("JB Adiatmaja", R.drawable.user2_image),
            UserData("Richard Lois S.", R.drawable.user3_image),
        )
        usersLiveData.value = gadgets
    }

    fun getUsersLiveData(): LiveData<List<UserData>> = usersLiveData
}