package com.srgameapp.sohanshoppinglist.uis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.srgameapp.sohanshoppinglist.MainActivity
import com.srgameapp.sohanshoppinglist.R
import com.srgameapp.sohanshoppinglist.daos.AppDatabase
import com.srgameapp.sohanshoppinglist.databinding.ActivityAddListBinding
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddListActivity : AppCompatActivity(){

lateinit var binding:ActivityAddListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        binding = ActivityAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.getDatabase(this)
        val dao = database.shoppingDao()
        binding.createListButton.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                val mylistName = binding.listName.text.toString()
                val emptyShoppingList = mutableListOf<ShoppingItem>()
                val myList = ShoppingTable(mylistName, emptyShoppingList)
                dao.insert(myList)
                val myIntent = Intent(this@AddListActivity, MainActivity::class.java)
                startActivity(myIntent)
            }
        }





    }
}