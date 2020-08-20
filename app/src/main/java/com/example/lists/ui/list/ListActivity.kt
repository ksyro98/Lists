package com.example.lists.ui.list

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat.getActionMasked
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lists.R
import com.example.lists.ui.add.ADD_EXTRA
import com.example.lists.ui.add.AddActivity
import com.example.lists.ui.add.ADD_REQ_CODE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.name_item.nameTextView
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val listViewModel by viewModels<ListViewModel>()
    @Inject lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listViewModel.listName = intent.getStringExtra(NAME_EXTRA) ?: return

        nameTextView.text = listViewModel.listName

        itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            setHasFixedSize(true)
            adapter = itemsAdapter
        }

        listViewModel.allItems.observe(this, Observer { items ->
            itemsAdapter.items = items
        })

        itemsAdapter.onClick = { item ->
            listViewModel.delete(item)
        }

        addItemFab.setOnClickListener {
            AddActivity.startActivityForResult(this)
        }

    }

    companion object {
        private const val NAME_EXTRA = "name extra"

        fun startActivity(context: Context, listName: String){
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(NAME_EXTRA, listName)
            context.startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_REQ_CODE && resultCode == Activity.RESULT_OK){
            val itemName = data?.getStringExtra(ADD_EXTRA) ?: return
            listViewModel.insert(itemName)
        }
    }

}