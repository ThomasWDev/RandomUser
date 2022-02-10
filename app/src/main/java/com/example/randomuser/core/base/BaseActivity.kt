package com.example.randomuser.core.base

import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.randomuser.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

/** ---
 * To act as a super class for all other activities.
 * Passing ViewDataBinding to include initialization which is common for all activities
 * that's using databinding
 */
abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected val gson = Gson()

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected lateinit var binding: V

    private val loadingDialog: LoadingDialog by lazy(mode = LazyThreadSafetyMode.NONE) {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)

        onCreated(savedInstanceState)
    }

    protected abstract fun onCreated(instance: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog.dismiss()
    }

    /***
     * this handles the back button on toolbars
     */
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    /**
     * this function control the visibility, showing of back button or adding title in toolbar on all activities
     */
    fun setToolbar(show: Boolean = false, showBackButton: Boolean = false, title: String = "") {
        val actionBar = supportActionBar

        actionBar?.run {
            if (show) {
                show()
                displayOptions = ActionBar.DISPLAY_SHOW_TITLE

                setHomeButtonEnabled(showBackButton)
                setDisplayHomeAsUpEnabled(showBackButton)

                if (title != "") {
                    setDisplayShowTitleEnabled(true)
                    this@run.title = title
                } else
                    setDisplayShowTitleEnabled(false)

            } else
                hide()
        }
    }

    /***
     * this function is for loading dialog
     */
    fun showLoading(isLoading: Boolean) {
        loadingDialog.let {
            if (isLoading && !loadingDialog.isShowing)
                loadingDialog.show()
            else if (!isLoading && loadingDialog.isShowing) {
                loadingDialog.dismiss()
            }
        }
    }

    /***
     *  this function is for snackbar
     */
    fun showMessage(message: String, positive: Boolean, neutral: Boolean = false) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)

        val sbView = snackbar.view
        when {
            neutral -> sbView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_blue_dark
                )
            )
            !positive -> {
                sbView.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        android.R.color.holo_red_dark
                    )
                )
            }
            else -> sbView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_green_dark
                )
            )
        }

        val textValue = sbView.findViewById<TextView>(R.id.snackbar_text)
        textValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        snackbar.show()
    }
}