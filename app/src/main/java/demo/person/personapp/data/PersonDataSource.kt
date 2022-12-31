package demo.person.personapp.data

import android.content.Context
import com.google.gson.Gson
import demo.person.personapp.model.PersonModel
import java.io.IOException

val peopleList = mutableListOf<PersonModel>()

class PersonDataSource(
    private val context: Context
) {

    fun fetchPeople(): List<PersonModel> {
        val jsonFileString = getJsonDataFromAsset(context, "person.json") ?: "File not found"
        return readPersonFromJson(jsonFileString)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    private fun readPersonFromJson(json: String): List<PersonModel> {
        val gson = Gson()
        return gson.fromJson(json, Array<PersonModel>::class.java).toList()
    }

}