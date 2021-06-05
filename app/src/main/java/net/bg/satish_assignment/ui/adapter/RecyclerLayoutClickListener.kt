package net.bg.satish_assignment.ui.adapter

import android.view.View
import net.bg.satish_assignment.data.models.Item

interface RecyclerLayoutClickListener {
    fun redirectToDetailScreen(imageView: View?, titleView: View?, githubItem: Item?)
}