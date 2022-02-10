package com.example.randomuser.feature.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.R
import com.example.randomuser.core.utils.AutoUpdatableAdapter
import com.example.randomuser.databinding.ItemUserBinding
import javax.inject.Inject
import kotlin.properties.Delegates


class UsersAdapter @Inject constructor() :
    RecyclerView.Adapter<UsersAdapter.Holder>(),
    AutoUpdatableAdapter {

    internal var collection: List<com.example.randomuser.core.data.user.Result> by Delegates.observable(emptyList()) { prop, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    /**
     * clicklistener for fragment
     */
    internal var onItemClick: (com.example.randomuser.core.data.user.Result) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder.from(
            parent,
            R.layout.item_user
        )

    /**
     * data binding
     */
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            item = collection[position]
            executePendingBindings()
            holder.itemView.setOnClickListener { onItemClick.invoke(collection[position]) }
        }
    }

    override fun getItemCount(): Int = collection.size

    class Holder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, layout: Int): Holder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemUserBinding>(
                    inflater,
                    layout,
                    parent,
                    false
                )
                return Holder(
                    binding
                )
            }
        }
    }

}