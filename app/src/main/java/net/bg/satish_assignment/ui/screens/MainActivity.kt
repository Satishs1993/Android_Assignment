package net.bg.satish_assignment.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.bg.satish_assignment.R
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.data.network.RetrofitCallbackResource
import net.bg.satish_assignment.databinding.ActivityMainBinding
import net.bg.satish_assignment.ui.GithubRepoViewModel
import net.bg.satish_assignment.ui.adapter.GithubListAdapter
import net.bg.satish_assignment.ui.adapter.RecyclerLayoutClickListener
import net.bg.satish_assignment.utils.NavigatorUtils


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),RecyclerLayoutClickListener {

    private val githubModel: GithubRepoViewModel by viewModels()
    private lateinit var githubListAdapter: GithubListAdapter
    private var githubItemList : ArrayList<Item> = ArrayList()
    private lateinit var viewBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()

        githubModel.getListData()


        githubModel.dbRepoList.observe(this,{
            if (it!=null){
                githubItemList.clear()
                githubItemList.addAll(it)
                githubListAdapter.notifyDataSetChanged()
            }
        })

        githubModel.repoList.observe(this,{
            viewBinding.progressBar.visibility = View.GONE
            when(it){
                    is RetrofitCallbackResource.Error ->
                    {
                        if (it.isNetworkError){
                            githubModel.getDbData()
                        }
                    }
                    is RetrofitCallbackResource.Success -> {
                            githubItemList.addAll(it.value.items)
                            githubListAdapter.notifyDataSetChanged()
                    }
                }

        })
    }

    private fun initView(){

        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        githubListAdapter = GithubListAdapter(this,githubItemList,this)
        viewBinding.recyclerView.adapter = githubListAdapter
    }

    override fun redirectToDetailScreen(imageView: View?,
                                        titleView: View?, githubItem: Item?) {

        val imgAnim = Pair.create(imageView,getString(R.string.transition_image))
        val textAnim = Pair.create(imageView,getString(R.string.transition_title))

        NavigatorUtils.redirectToDetailScreen(this,githubItem,
        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
        imgAnim,textAnim))

    }
}