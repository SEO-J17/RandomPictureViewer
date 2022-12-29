package project.seo.pictureviewer.navigator

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import project.seo.pictureviewer.R
import project.seo.pictureviewer.ui.detail.DetailFragment
import project.seo.pictureviewer.ui.main.MainFragment
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor(
    private val activity: FragmentActivity,
) : AppNavigator {
    override fun navigateTo(id: Int?, screen: Screens) {
        val fragment = when (screen) {
            Screens.DETAIL -> DetailFragment()
            Screens.MAIN -> MainFragment()
        }

        if (id != null) {
            fragment.arguments = Bundle().apply {
                putInt(AppNavigator.PICTURE_KEY, id)
            }
        }

        activity.supportFragmentManager.commit {
            replace(R.id.container, fragment)
            addToBackStack(fragment::class.java.canonicalName)
        }
    }

}