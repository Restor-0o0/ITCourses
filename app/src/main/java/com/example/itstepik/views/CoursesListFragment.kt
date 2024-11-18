package com.example.itstepik.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.itstepik.R
import com.example.itstepik.data.ListItem
import com.example.itstepik.databinding.FragmentCoursesListBinding
import com.example.itstepik.viewmodel.CoursesViewModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoursesListFragment : Fragment() {
    lateinit var binding : FragmentCoursesListBinding
    val viewModel by viewModel<CoursesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentCoursesListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListDelegationAdapter<List<ListItem>>(
            courseAdapterDelegate {  }
        )
        binding.list.adapter = adapter


        viewModel
    }

}