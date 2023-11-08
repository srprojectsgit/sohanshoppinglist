package com.srgameapp.sohanshoppinglist.uis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.srgameapp.sohanshoppinglist.R
import com.srgameapp.sohanshoppinglist.adapters.ShoppingAdapter
import com.srgameapp.sohanshoppinglist.daos.AppDatabase
import com.srgameapp.sohanshoppinglist.daos.ShoppingItemConverters
import com.srgameapp.sohanshoppinglist.databinding.ActivityShoppingAddBinding
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingAdd : AppCompatActivity() {

    lateinit var binding:ActivityShoppingAddBinding
    lateinit var shoppingAdapter: ShoppingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_add)

        binding = ActivityShoppingAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.getDatabase(this)
        val dao = database.shoppingDao()
        val myLayoutManager = LinearLayoutManager(this)
        binding.shoppingAdd.layoutManager = myLayoutManager
        val myId = intent.getStringExtra("mylistid")


        lifecycleScope.launch(Dispatchers.IO){
//            val listAdapter = dao.getShoppingList(myId.toString())
//
//            val newList = gson.fromJson(listAdapter,Array<String>::class.java).toList()
//            withContext(Dispatchers.Main) {
//                binding.shoppingAdd.adapter = ShoppingAdapter(newList.toMutableList())
//            }
//
//            Log.i("HelloWorld",myId.toString())

//            val listItems = dao.getShoppingItem()
//            val shoppingMap:Map<String,Boolean> = gson.fromJson(listItems, object: TypeToken<Map<String, Boolean>>() {}.type)
//
//            val myList = shoppingMap.keys.toMutableList()
//            withContext(Dispatchers.Main) {
//                binding.shoppingAdd.adapter = ShoppingAdapter(myList)
//            }


//
//                    withContext(Dispatchers.Main) {
//                binding.shoppingAdd.adapter = ShoppingAdapter(shoppingList)
//  }

            val myListString = dao.getAShoppingTable(myId.toString())
            withContext(Dispatchers.Main) {
                shoppingAdapter = ShoppingAdapter(applicationContext,myListString.shoppingList.toMutableList())
                if(!ShoppingAdapter.checkedList.isEmpty()){
                    shoppingAdapter = ShoppingAdapter(applicationContext,ShoppingAdapter.checkedList.toMutableList())
                }
                        binding.shoppingAdd.adapter = shoppingAdapter
  }


            var myListString2 = dao.getAShoppingTable(myId.toString())
            val updatedSet = ShoppingAdapter.checkedList
            val updatedList = updatedSet.toMutableList()

            if(!updatedList.isEmpty()) {
                myListString2.shoppingList = updatedList
            }

        }



        binding.addItemsButton.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                val myIntent = Intent(this@ShoppingAdd, AddItems::class.java)
                myIntent.putExtra("tableId",myId)
                startActivity(myIntent)
            }
        }

        ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN
            ,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                lifecycleScope.launch(Dispatchers.IO){
                    var myListString = dao.getAShoppingTable(myId.toString())
                    val shoppingItem = myListString.shoppingList
                    val myAdapter = shoppingItem.toMutableList()


                    withContext(Dispatchers.Main){
                        val movedItem = myAdapter.removeAt(viewHolder.adapterPosition)
                        myAdapter.add(target.adapterPosition,movedItem)
                        binding.shoppingAdd.adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)


                    }
//
//                    myListString.shoppingList = myAdapter
//                    dao.insert(myListString)


                }
//                val movedItem = data.removeAt(fromPosition)
//                data.add(toPosition, movedItem)
//                notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

                return makeMovementFlags(dragFlags,swipeFlags)
            }


            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val viewPosition = viewHolder.adapterPosition
                binding.shoppingAdd.removeViewAt(viewHolder.adapterPosition)
                lifecycleScope.launch(Dispatchers.IO){

//                    var myVar = dao.getShoppingList(myId.toString())
//                    val newStarterList = gson.fromJson(myVar,Array<String>::class.java).toMutableList()
//                    val adapter = ShoppingAdapter(newStarterList)
//                    newStarterList.removeAt(viewPosition)
//                    withContext(Dispatchers.Main){
//                    binding.shoppingAdd.adapter =adapter}
//                    dao.updateListAt(newStarterList.toList(),myId.toString())

                    val myVar = dao.getAShoppingItem(myId.toString())
                    val myList = ShoppingItemConverters.fromJson(myVar)
                    val mutableMyList = myList.toMutableList()

                    mutableMyList.removeAt(viewPosition)

                    val newShoppingTable = ShoppingTable(myId.toString(),mutableMyList)
                    dao.insert(newShoppingTable)


                    shoppingAdapter = ShoppingAdapter(applicationContext,mutableMyList)
                    withContext(Dispatchers.Main){
                    binding.shoppingAdd.adapter =shoppingAdapter}

//                    Log.i("SR_TAG",viewHolder.itemView.)
////                    newList.forEach{item ->
////                        if(item.itemName == viewHolder.)
////                    }






                }
                binding.shoppingAdd.adapter?.notifyDataSetChanged()

            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }



        }


        ).attachToRecyclerView(binding.shoppingAdd)



    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch(Dispatchers.IO){
//            val database = AppDatabase.getDatabase(this@ShoppingAdd)
//            val dao = database.shoppingDao()
//            val gson = Gson()
//            val myId = intent.getStringExtra("mylistid")
//
//            val listAdapter = dao.getShoppingList(myId.toString())
//
//            val newList = gson.fromJson(listAdapter,Array<String>::class.java).toList()
//            withContext(Dispatchers.Main) {
//                binding.shoppingAdd.adapter = ShoppingAdapter(newList.toMutableList())
//            }

            val database = AppDatabase.getDatabase(this@ShoppingAdd)
            val dao = database.shoppingDao()
            val myId = intent.getStringExtra("mylistid")

            var myListString2 = dao.getAShoppingTable(myId.toString())

            val updatedSet = ShoppingAdapter.checkedList
            val updatedList = updatedSet.toMutableList()

            if(!updatedList.isEmpty()) {
                myListString2.shoppingList = updatedList
            }

            dao.insert(myListString2)







            withContext(Dispatchers.Main) {
                shoppingAdapter = ShoppingAdapter(applicationContext,myListString2.shoppingList.toMutableList())
                if(!ShoppingAdapter.checkedList.isEmpty()){
                    shoppingAdapter = ShoppingAdapter(applicationContext,ShoppingAdapter.checkedList.toMutableList())
                }
                binding.shoppingAdd.adapter = shoppingAdapter
                myListString2.shoppingList.forEach{
                    Log.i("haschecked",it.checked.toString())
                }











        }

    }










}}