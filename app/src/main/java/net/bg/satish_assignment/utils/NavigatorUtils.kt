package net.bg.satish_assignment.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import net.bg.satish_assignment.R
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.ui.screens.DetailActivity
import java.util.*
import kotlin.collections.ArrayList

object NavigatorUtils {
    var INTENT_POST = "intent_post"
    fun redirectToDetailScreen(
        activity: Activity?,
        githubEntity: Item?,
        options: ActivityOptionsCompat
    ) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(INTENT_POST, githubEntity)
        ActivityCompat.startActivity(activity!!, intent, options.toBundle())
    }



}