package project.seo.pictureviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.databinding.ActivityMainBinding
import project.seo.pictureviewer.network.RetrofitService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.addItemDecoration(RecyclerViewItemDecorator())

        binding.recyclerView.adapter = ListAdapter { position ->
            startActivity(
                Intent(
                    this@MainActivity, DetailActivity::class.java
                ).putExtra(
                    EXTRA_NAME, position
                )
            )
        }
        lifecycleScope.launch {
            with(RetrofitService.getPicture()) {
                (binding.recyclerView.adapter as? ListAdapter)?.updateData(this)
                QueryUtils.extractData(this)
            }
            withContext(Dispatchers.Main) {
                binding.loadingBar.visibility = View.GONE
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "picturePosition"
    }
}