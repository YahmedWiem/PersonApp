package demo.person.personapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import demo.person.personapp.databinding.PersonDetailsFragmentBinding

class PersonDetailFragment : Fragment() {

    private lateinit var binding: PersonDetailsFragmentBinding
    private val args: PersonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = args.person
        with(binding) {
            firstNameValue.text = person.first
            lastNameValue.text = person.last
            countryValue.text = person.country
            companyValue.text = person.company
            emailValue.text = person.email
        }
    }

}