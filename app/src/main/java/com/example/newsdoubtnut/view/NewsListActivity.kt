package com.example.newsdoubtnut.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amplifyframework.analytics.AnalyticsEvent
import com.amplifyframework.core.Amplify
import com.example.newsdoubtnut.R
import com.example.newsdoubtnut.databinding.ActivityNewsListBinding
import com.example.newsdoubtnut.injection.ViewModelFactory
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging

class NewsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsListBinding
    private lateinit var viewModel: NewsListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { Log.i("AmplifyQuickstart", "Failed to fetch auth session") }
        )


        val event = AnalyticsEvent.builder()
            .name("PasswordReset")
            .addProperty("Channel", "SMS")
            .addProperty("Successful", true)
            .addProperty("ProcessDuration", 792)
            .addProperty("UserAge", 120.3)
            .build()

        val event1 = AnalyticsEvent.builder()
            .name("Incyte User")
            .addProperty("name", "Gaurav Setia")
            .addProperty("authorized","Yes")
            .addProperty("age", "29")
            .addProperty("place", "Gurgaon")
            .addProperty("otherMedicalIssue", "none")
            .build()

        Amplify.Analytics.recordEvent(event)
        Amplify.Analytics.recordEvent(event1)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("NewsListActivity", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("NewsListActivity", token.toString())
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })


        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        binding.newsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))[NewsListViewModel::class.java]
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}