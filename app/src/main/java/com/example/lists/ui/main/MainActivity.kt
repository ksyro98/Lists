package com.example.lists.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lists.R
import com.example.lists.ui.add.ADD_EXTRA
import com.example.lists.ui.add.AddActivity
import com.example.lists.ui.add.ADD_REQ_CODE
import com.example.lists.ui.list.ListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    @Inject lateinit var namesAdapter: NamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        namesRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = namesAdapter
        }

        mainViewModel.names.observe(this, Observer { lists ->
            namesAdapter.names = lists ?: return@Observer
        })

        namesAdapter.onClick = { itemsList ->
            ListActivity.startActivity(this, itemsList.name)
        }

        namesAdapter.onLongClick = { itemsList ->
            mainViewModel.delete(itemsList)
        }

        addListFab.setOnClickListener {
            AddActivity.startActivityForResult(this)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_REQ_CODE && resultCode == Activity.RESULT_OK){
            val listName = data?.getStringExtra(ADD_EXTRA) ?: return
            mainViewModel.insert(listName)
        }
    }
}