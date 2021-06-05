package net.bg.satish_assignment.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.databinding.ActivityDetailBinding
import net.bg.satish_assignment.utils.NavigatorUtils

class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding : ActivityDetailBinding
    private lateinit var gitHubItem : Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        gitHubItem = intent.getParcelableExtra(NavigatorUtils.INTENT_POST)!!

        bindUIData()

    }

    private fun bindUIData(){
        Glide.with(this)
            .load(gitHubItem.owner.avatar_url)
            .into(viewBinding.img)
        viewBinding.tvTitle.text = gitHubItem.full_name
        viewBinding.totalWatches.text = "Total Watches ${gitHubItem.watchers}"
        viewBinding.totalForks.text = "Total Forks : ${gitHubItem.forks_count.toString()}"
        viewBinding.forkUrl.text = "Fork URL : ${gitHubItem.forks_url}"
    }
}