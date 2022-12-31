package demo.person.personapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import demo.person.personapp.MainViewModel
import demo.person.personapp.databinding.PeopleFragmentBinding
import demo.person.personapp.ui.adapter.PersonAdapter

const val COUNTRY_SORT_TAG = "country_tag"
const val NAME_SORT_TAG = "name_tag"

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private lateinit var binding: PeopleFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PeopleFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val peopleAdapter = PersonAdapter() {
            val direction =
                PeopleFragmentDirections.actionPeopleFragmentToPersonDetailFragment(it)
            findNavController().navigate(direction)
        }

        viewModel.people.observe(viewLifecycleOwner) { people ->
            peopleAdapter.submitList(people.toMutableList())
        }
        binding.peopleListView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = peopleAdapter
        }
        viewModel.fetchPeople()

        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            viewModel.filter(text.toString())
        }

        binding.countrySegment.setOnClickListener {
            sort(COUNTRY_SORT_TAG)
        }

        binding.nameSegment.setOnClickListener {
            sort(NAME_SORT_TAG)
        }
    }

    private fun sort(tag: String) {
        binding.countrySegment.isChecked = tag == COUNTRY_SORT_TAG
        binding.nameSegment.isChecked = tag == NAME_SORT_TAG
        viewModel.sort(tag)
    }
}

private const val TAG = "PeopleFragment"