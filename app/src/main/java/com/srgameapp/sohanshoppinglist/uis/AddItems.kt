package com.srgameapp.sohanshoppinglist.uis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.srgameapp.sohanshoppinglist.R
import com.srgameapp.sohanshoppinglist.daos.AppDatabase
import com.srgameapp.sohanshoppinglist.daos.ShoppingItemConverters
import com.srgameapp.sohanshoppinglist.databinding.ActivityAddItemsBinding
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddItems : AppCompatActivity() {

    lateinit var binding:ActivityAddItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_items)

        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myId = intent.getStringExtra("tableId")
        val database = AppDatabase.getDatabase(this)
        val dao = database.shoppingDao()

        val gson = Gson()

//        binding.addItemButton.setOnClickListener{
//        lifecycleScope.launch(Dispatchers.IO){
//            val myMapString = dao.getShoppingItem()
//            val myMapGson:MutableMap<String,Boolean> =  gson.fromJson(myMapString ,object : TypeToken<Map<String, Boolean>>() {}.type)
//            withContext(Dispatchers.Main){
//            myMapGson[binding.itemAdd.text.toString()] = false
//
//            }
//            dao.updateListAt(myMapGson,myId.toString())
//
//
//        }
//        }





                  binding.addItemButton.setOnClickListener{
                      lifecycleScope.launch(Dispatchers.IO){

                  val listString = dao.getAShoppingItem(myId.toString())
                      val listItem = ShoppingItemConverters.fromJson(listString)
                      val mutableItem = listItem.toMutableList()
                      var tableObject = dao.getAShoppingTable(myId.toString())
                      withContext(Dispatchers.Main){
                          var priceAdd = 0.0
                          var itemAdd = ""

                      if(binding.itemPrice.text.toString()!=""){
                          priceAdd = binding.itemPrice.text.toString().toDouble()
                      }
                          itemAdd = binding.itemAdd.text.toString()


                          mutableItem.add(ShoppingItem(itemAdd,priceAdd,false))}
                      tableObject.shoppingList = mutableItem.toList()
                      dao.upsert(tableObject)
}
                  }



        }


}