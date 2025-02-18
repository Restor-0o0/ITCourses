package com.example.itstepik.views

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.itstepik.R
import com.example.itstepik.data.Tag
import com.example.itstepik.databinding.FilterTagItemBinding

class TagsAdapter(
    var listTags:List<Tag> = emptyList(),
    private val func: OnTagClickListener): RecyclerView.Adapter<TagsAdapter.Holder>() {

    var lastPick:Int? = null
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
        holder.bind(listTags[position],position,func)

    }

   inner class Holder(private val binding: FilterTagItemBinding) : RecyclerView.ViewHolder(binding.root){
       fun bind(item: Tag,position: Int,func:OnTagClickListener){
           binding.tag.text = item.title+ " "+ item.id.toString()

           val context = binding.root.context

           if(item.active){
               binding.root.background = ContextCompat.getDrawable(context,R.drawable.menu_back_butt_check)
           }
           else{
               binding.root.background = ContextCompat.getDrawable(context,R.drawable.menu_back_butt)
           }

           binding.tag.setOnClickListener{

               Log.e("TAGGGGSSS__",item.id.toString())
               for(it in listTags){
                   if(it.active){
                       it.active = false
                       notifyDataSetChanged()
                   }
               }
               item.active = item.active.not()

               notifyItemChanged(position)
               func.onClick(item.id)
           }
       }
   }
}