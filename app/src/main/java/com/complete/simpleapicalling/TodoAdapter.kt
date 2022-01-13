package com.complete.simpleapicalling

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.complete.simpleapicalling.databinding.ListitemsBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    inner class ViewHolder(val binding : ListitemsBinding): RecyclerView.ViewHolder(binding.root) {

    }
    private val diffCallback = object : DiffUtil.ItemCallback<TodoModel>(){
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return newItem == oldItem
        }

    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var todos : List<TodoModel>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply{
            val todo = todos[position]
            placeholder.text = todo.title
            checkBox.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}












