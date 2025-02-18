package com.example.itstepik.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itstepik.R
import com.example.itstepik.data.ListItem
import com.example.itstepik.databinding.FragmentCoursesListBinding
import com.example.itstepik.viewmodel.CoursesViewModel
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
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
    val filter: FilterDialogFragment = FilterDialogFragment()

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

        viewModel.loadCourses()
        val adapter = ListDelegationAdapter<List<ListItem>>(
            courseAdapterDelegate {  }
        )
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = adapter
        binding.list.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.list.canScrollVertically(1)){
                    Log.e("SCROLLLLLL","load")
                    viewModel.loadNextCourses()
                }
            }
        })


        viewModel.coursesList.observe(viewLifecycleOwner) {it ->
            (binding.list.adapter as ListDelegationAdapter<List<ListItem>>).notifyDataSetChanged()
            lifecycleScope.launch {
                if(it.isNotEmpty() && it != null){

                        adapter.items = it
                        (binding.list.adapter as ListDelegationAdapter<List<ListItem>>).notifyDataSetChanged()
                    viewModel.coutCourses = adapter.itemCount

                }
                Log.e("DEBUUGG1", (binding.list.adapter as ListDelegationAdapter<List<ListItem>>).itemCount.toString())

            }
        }
        binding.filterButton.setOnClickListener{
            filter.show(childFragmentManager,"FILTER")
        }
        viewModel.loadTagsByParent()
    }


}