package com.example.lists.ui.add

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lists.R
import kotlinx.android.synthetic.main.activity_add.*

const val ADD_EXTRA = "add extra"
const val ADD_REQ_CODE = 100

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val callingActivity = intent.getSerializableExtra(CLASS_EXTRA) as Class<*>
        val result = Intent(this, callingActivity)

        okAddTextView.setOnClickListener{

            if(addEditText.text.isEmpty()){
                return@setOnClickListener
            }

            result.putExtra(ADD_EXTRA, addEditText.text.toString())
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        cancelAddTextView.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, result)
            finish()
        }

    }


    companion object{

        private const val CLASS_EXTRA = "class extra"

        fun startActivityForResult(activity: Activity){
            val intent = Intent(activity, AddActivity::class.java)
            intent.putExtra(CLASS_EXTRA, activity.javaClass)
            activity.startActivityForResult(intent, ADD_REQ_CODE)
        }

    }
}