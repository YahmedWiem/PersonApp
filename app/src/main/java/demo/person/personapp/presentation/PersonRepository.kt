package demo.person.personapp.presentation

import demo.person.personapp.data.PersonDataSource

class PersonRepository(
    private val personDataSource: PersonDataSource
) {

    fun fetchPeople() = personDataSource.fetchPeople()

}