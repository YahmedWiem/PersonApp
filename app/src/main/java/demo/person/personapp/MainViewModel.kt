package demo.person.personapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.person.personapp.model.PersonModel
import demo.person.personapp.presentation.PersonRepository
import demo.person.personapp.ui.COUNTRY_SORT_TAG
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PersonRepository
) : ViewModel() {

    private val originalPeopleList = mutableListOf<PersonModel>()
    private val _people = MutableLiveData<List<PersonModel>>()
    val people: LiveData<List<PersonModel>> = _people

    fun fetchPeople() {
        originalPeopleList.addAll(repository.fetchPeople())
        _people.value = originalPeopleList
    }

    fun filter(query: String) {
        _people.value = if (query.isEmpty()) {
            originalPeopleList
        } else {
            originalPeopleList.filter {
                "${it.first} ${it.last}".lowercase().contains(query)
            }
        }
    }

    fun sort(tag: String) {
        if (tag == COUNTRY_SORT_TAG) {
            originalPeopleList.sortWith(compareBy { it.country.lowercase() })
        } else {
            originalPeopleList.sortWith(compareBy<PersonModel> { it.first.lowercase() }.thenBy { it.last.lowercase() })
        }
        _people.value = originalPeopleList
    }
}

private const val TAG = "MainViewModel"