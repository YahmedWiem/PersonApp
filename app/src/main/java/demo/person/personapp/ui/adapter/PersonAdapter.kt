package demo.person.personapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import demo.person.personapp.R
import demo.person.personapp.databinding.PersonItemViewBinding
import demo.person.personapp.model.PersonDiffUtils
import demo.person.personapp.model.PersonModel

class PersonAdapter(
    private val onPersonCLicked: (PersonModel) -> Unit
) : ListAdapter<PersonModel, PersonAdapter.PersonViewHolder>(PersonDiffUtils) {

    inner class PersonViewHolder(private val binding: PersonItemViewBinding) :
        ViewHolder(binding.root) {
        fun bind(person: PersonModel) {
            binding.root.setOnClickListener {
                onPersonCLicked(getItem(adapterPosition))
            }
            binding.personCountry.text = person.country
            binding.personFullName.text = binding.root.context.resources.getString(
                R.string.person_full_name,
                person.first,
                person.last
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = PersonItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}