package com.manojrai.githubexample.ui.repodetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.manojrai.githubexample.R
import kotlinx.android.synthetic.main.activity_repositories_details.*
import org.koin.android.ext.android.inject

class RepositoriesDetailsActivity : AppCompatActivity() {

    val viewModel: RepositoriesDetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories_details)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {
        val fullName = intent.getStringExtra("fullName") ?: return
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val avatarUrl = intent.getStringExtra("avatarUrl")
        viewModel.setDetails(name, fullName, description, avatarUrl)

    }

    private fun setUpObserver() {

        viewModel.name.observe(this, {
            tvName.text = it
        })

        viewModel.fullName.observe(this, {
            tvFullName.text = it
        })

        viewModel.description.observe(this, {
            tvDescriptions.text = it
        })

        viewModel.avatarUrl.observe(this, {
            Glide.with(ivOwner.context)
                .load(it).into(ivOwner)
        })

    }

}