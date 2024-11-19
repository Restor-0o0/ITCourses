package com.example.itstepik.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itstepik.data.Tag
import com.example.itstepik.databinding.FilterTagItemBinding

class TagsAdapter(var listTags:List<Tag> = emptyList(),private val func: OnTagClickListener): RecyclerView.Adapter<TagsAdapter.Holder>() {

    lateinit var binding: FilterTagItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.Holder {

        binding = FilterTagItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int {
       return listTags.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listTags[position],func)

    }

   class Holder(private val binding: FilterTagItemBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(item: Tag,func:OnTagClickListener){
           binding.tag.text = item.title+ " "+ item.id.toString()
           binding.tag.setOnClickListener{
               Log.e("TAGGGGSSS__",item.id.toString())
               func.onClick(item.id)
           }
       }
   }
}