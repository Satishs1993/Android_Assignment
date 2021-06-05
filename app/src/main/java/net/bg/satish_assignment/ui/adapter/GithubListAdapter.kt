package net.bg.satish_assignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.databinding.RepositoryListItemBinding

class GithubListAdapter(private val context : Context,
private val repositoryItems : ArrayList<Item>,
private val itemClick : RecyclerLayoutClickListener) :
    RecyclerView.Adapter<GithubListAdapter.ViewHolder>() {

    inner class ViewHolder(private val repositoryListItemBinding : RepositoryListItemBinding)
        : RecyclerView.ViewHolder(repositoryListItemBinding.root){

            fun bindView(items : Item){
                repositoryListItemBinding.itemDesc.text = items.forks_url
                repositoryListItemBinding.itemTitle.text = items.full_name
                Glide.with(context)
                    .load(items.owner.avatar_url)
                    .into(repositoryListItemBinding.itemProfileImg)
            }

        fun onLayoutButtonClick() {
            itemClick.redirectToDetailScreen(repositoryListItemBinding.itemProfileImg,
                repositoryListItemBinding.itemTitle, repositoryItems[layoutPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var infalter = LayoutInflater.from(parent.context)
        var bindingInflater = RepositoryListItemBinding.inflate(infalter,parent,false)

        var viewHolder = ViewHolder(bindingInflater)
        bindingInflater.root.setOnClickListener {
            viewHolder.onLayoutButtonClick()
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var repoItems = repositoryItems[position]
        holder.bindView(repoItems)

    }



    override fun getItemCount(): Int {
        return repositoryItems.size
    }
}