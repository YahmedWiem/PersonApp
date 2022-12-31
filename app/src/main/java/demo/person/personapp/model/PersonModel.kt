package demo.person.personapp.model

import androidx.recyclerview.widget.DiffUtil
import java.io.Serializable

data class PersonModel(
    val company: String,
    val country: String,
    val created_at: String,
    val email: String,
    val first: String,
    val id: Int,
    val last: String
) : Serializable

object PersonDiffUtils : DiffUtil.ItemCallback<PersonModel>() {
    override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel) =
        newItem.id == oldItem.id

    override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel) = newItem == oldItem
}