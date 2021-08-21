package com.manojrai.githubexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manojrai.githubexample.R
import com.manojrai.githubexample.data.model.Repositories
import kotlinx.android.synthetic.main.item_layout_repositories.view.*

class RepositoriesAdapter(
    private var list: List<Repositories>,
    private var clickListener: (Repositories) -> Unit
) :
    RecyclerView.Adapter<RepositoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_repositories, parent, false)
        return MyViewHolder(listItem, clickListener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    fun appendData(dataList: List<Repositories>) {
        list = dataList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, private var clickListener: (Repositories) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(repositories: Repositories) {
            itemView.tvName.text = repositories.name
            itemView.tvFullName.text = repositories.fullName
            Glide.with(itemView.context)
                .load(repositories.owner?.avatarUrl)
                .into(itemView.ivOwner)

            itemView.setOnClickListener {
                clickListener(repositories)
            }
        }
    }
}