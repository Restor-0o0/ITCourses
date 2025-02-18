package com.example.itstepik.views

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.itstepik.databinding.FilterDialogBinding
import com.example.itstepik.viewmodel.CoursesViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterDialogFragment: DialogFragment(),OnTagClickListener {

    lateinit var binding:FilterDialogBinding
    val viewModel by viewModel<CoursesViewModel>(ownerProducer = {requireParentFragment()}    )
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        dialog!!.window!!.attributes.dimAmount = 0f
        dialog!!.window!!.setBackgroundDrawable(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var layoutManager = FlexboxLayoutManager(context)
        layoutManager.setFlexDirection(FlexDirection.ROW)
        //layoutManager.setJustifyContent(JustifyContent.FLEX_END)
        binding.tags.layoutManager = layoutManager
        binding.all.setOnClickListener{

        }
        binding.free.setOnClickListener{

        }
        binding.notfree.setOnClickListener{

        }
        binding.advanced.setOnClickListener{

        }
        binding.beginner.setOnClickListener{

        }
        binding.intermediate.setOnClickListener{

        }
        binding.frame.setOnClickListener{
            dialog!!.dismiss()
        }
        val adapter = TagsAdapter( func =this)
        viewModel.tagsList.observe(viewLifecycleOwner){ it ->
            lifecycleScope.launch {
                if(it.isNotEmpty() && it != null){
                    Log.e("TAGGGGSSS",it.size.toString())
                    adapter.listTags = it
                    Log.e("TAGGGGSSS",adapter.itemCount.toString())
                    adapter.notifyDataSetChanged()
                }
                cancel()
            }
        }
        binding.tags.adapter = adapter

    }

    override fun onClick(tag: Int) {
        viewModel.loadCourses(tag=tag)
    }
}