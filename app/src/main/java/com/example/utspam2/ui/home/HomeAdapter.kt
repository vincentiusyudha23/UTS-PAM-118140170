package com.example.utspam2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.utspam2.databinding.ItemListBinding
import com.example.utspam2.model.DataItem

class HomeAdapter(
    private val users: ArrayList<DataItem>
) :
    RecyclerView.Adapter<HomeAdapter.ListViewHolder>()
{

    private var filteredUser: ArrayList<DataItem>

    init{
        filteredUser = users
    }

    inner class ListViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        var tvUserName = binding.tvUsername
        var tvEmail = binding.tvEmail
        var imgUsers = binding.imgUsers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    fun addUser(newUser: DataItem){
        filteredUser.add(newUser)
        notifyItemInserted(users.lastIndex)
    }

    fun clear(){
        filteredUser.clear()
        notifyDataSetChanged()
    }

    fun setFilteredUser(data: ArrayList<DataItem>){
        filteredUser = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filteredUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = filteredUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(80,80))
            .transform(CircleCrop())
            .into(holder.imgUsers)

        holder.tvUserName.text = "${user.firstName} ${user.lastName}"
        holder.tvEmail.text = user.email
    }


}