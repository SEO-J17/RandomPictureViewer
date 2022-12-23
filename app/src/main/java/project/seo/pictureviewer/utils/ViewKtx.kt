package project.seo.pictureviewer.extensions

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner

val View.coroutineScope: LifecycleCoroutineScope?
    get() = findViewTreeLifecycleOwner()?.lifecycle?.coroutineScope