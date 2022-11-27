package project.seo.pictureviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(PictureFragment())
    }

    fun setFragment(fragment: Fragment) {
        with(supportFragmentManager) {
            if (backStackEntryCount == 0) {
                beginTransaction().add(binding.fragmentContainerView.id, fragment)
                    .addToBackStack("first")
            } else {
                beginTransaction()
                    .replace(binding.fragmentContainerView.id, fragment)
            }
                .commit()

        presenter = MainPresenter(this)
        presenter.start()

        binding.recyclerView.addItemDecoration(RecyclerViewItemDecorator())

    }

    override val lifecycleScope: CoroutineScope
        get() = lifecycle.coroutineScope

    override fun showLoadingBar() {
        binding.loadingBar.visibility = View.VISIBLE
    }

    override fun hideLoadingBar() {
        binding.loadingBar.visibility = View.GONE
    }

    override fun showList(pictureInfo: MutableList<PictureData>) {
        binding.recyclerView.adapter = ListAdapter(pictureInfo) { position ->
            presenter.onItemClick(position)
        }
    }

    override fun showDetail(position: Int) {
        startActivity(
            Intent(
                this@MainActivity,
                DetailActivity::class.java
            ).putExtra(
                EXTRA_NAME,
                position
            )
        )
    }

    companion object {
        const val EXTRA_NAME = "picturePosition"
    }
}