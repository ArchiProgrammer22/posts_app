package com.example.stateapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stateapp.R
import com.example.stateapp.model.Post

class RecyclerViewAdapter(
    private val posts: List<Post>,
    private val onRecyclerClickListener: OnRecyclerClickListener
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(itemView, onRecyclerClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idText.text = posts[position].id.toString()
        holder.titleText.text = if (posts[position].title.length <= 15) {
            posts[position].title
        } else {
            posts[position].title
                .subSequence(0, 15)
                .toString()
                .plus("...")
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(
        itemView: View,
        private val onRecyclerClickListener: OnRecyclerClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val idText: TextView = itemView.findViewById(R.id.idText)
        val titleText: TextView = itemView.findViewById(R.id.titleText)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onRecyclerClickListener.onClick(adapterPosition)
        }
    }

    interface OnRecyclerClickListener {
        fun onClick(position: Int)
    }
}