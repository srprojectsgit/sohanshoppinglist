package com.srgameapp.sohanshoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.srgameapp.sohanshoppinglist.adapters.MainAdapterSr
import com.srgameapp.sohanshoppinglist.adapters.ShoppingAdapter
import com.srgameapp.sohanshoppinglist.daos.AppDatabase
import com.srgameapp.sohanshoppinglist.daos.ShoppingItemConverters
import com.srgameapp.sohanshoppinglist.databinding.ActivityMainBinding
import com.srgameapp.sohanshoppinglist.entities.ItemTable
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import com.srgameapp.sohanshoppinglist.uis.AddListActivity
import com.srgameapp.sohanshoppinglist.uis.ShoppingAdd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:TestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
        val database = AppDatabase.getDatabase(this)
        val dao = database.shoppingDao()
        binding.rView.layoutManager =LinearLayoutManager(this)

        lifecycleScope.launch(Dispatchers.IO){
            if(dao.getAllShoppingTables().isEmpty()) {
                viewModel.sampleList = mutableListOf(
                    ShoppingItem("Eggs",1.50,false),
                    ShoppingItem("Potatoes",2.50,false),
                    ShoppingItem("Bread",1.00,false),
                    ShoppingItem("Cake",null,false)
                )


                val testData = ShoppingTable("Sample_List",viewModel.sampleList)
                dao.insert(testData)

                val anotherSampleList = mutableListOf(
                    ShoppingItem("Salt",1.50,false),
                    ShoppingItem("Chips",2.50,true),
                    ShoppingItem("Ketchup",1.00,false),
                    ShoppingItem("Sugar",null,false),
                    ShoppingItem("Corn",1.00,false)


                )

//                var aSampleList:MutableList<ShoppingItem>
//                if(!ShoppingAdapter.checkedList.isEmpty()){
//                    aSampleList.replac
//                }


                val table2 = ShoppingTable("Another_Sample_List",anotherSampleList)
                dao.insert(table2)

                val listNumber = dao.getAllShoppingItems()


                val gson = Gson()
                val allShoppingItems = ShoppingItemConverters.fromJson(listNumber)







            }


            startList()





            withContext(Dispatchers.Main) {
                    binding.rView.adapter = MainAdapterSr(this@MainActivity, viewModel.adapterList)


                    binding.addListButton.setOnClickListener {
                        val myIntent = Intent(this@MainActivity, AddListActivity::class.java)
                        startActivity(myIntent)
                    }


            }






        }

        binding.rView.adapter?.notifyDataSetChanged()




    }

    override fun onStart() {
        super.onStart()
        startList()
    }

    fun startList(){
        lifecycleScope.launch(Dispatchers.IO){


            val database = AppDatabase.getDatabase(this@MainActivity)
            val dao = database.shoppingDao()
            val myIds = dao.getAllShoppingTables()
            val shoppingTables = myIds.toMutableList()
            viewModel.adapterList = shoppingTables


            withContext(Dispatchers.Main){
                binding.rView.adapter = MainAdapterSr(this@MainActivity, viewModel.adapterList)
                binding.rView.adapter?.notifyDataSetChanged()

            }


        }

    }

}