package com.srgameapp.sohanshoppinglist.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgameapp.sohanshoppinglist.databinding.MainTextItemBinding
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable
import com.srgameapp.sohanshoppinglist.uis.ShoppingAdd

class MainAdapterSr(var context: Context, private val listOfShoppingLists:List<ShoppingTable>): RecyclerView.Adapter<MainAdapterSr.ViewHolder>() {

    inner class ViewHolder(val adapterBinding:MainTextItemBinding): RecyclerView.ViewHolder(adapterBinding.root){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = MainTextItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adapterBinding.textView.text = listOfShoppingLists[position].shoppingId
        holder.adapterBinding.sizeListText.text = "${getItemChecked()[position]}/${listOfShoppingLists[position].shoppingList.size}"

        holder.adapterBinding.textView.setOnClickListener{
            val myIntent = Intent(context, ShoppingAdd::class.java)
            myIntent.putExtra("mylistid",holder.adapterBinding.textView.text.toString())
            context.startActivity(myIntent)

        }









    }

    override fun getItemCount(): Int {
        return listOfShoppingLists.size
    }

    fun getItemChecked():MutableList<Int>{
        var checkedList:MutableList<Int> = mutableListOf()

        listOfShoppingLists.forEach{var totalChecked =0
            it.shoppingList.forEach{item ->
                if(item.checked==true){
                    totalChecked++
                }
            }
            checkedList.add(totalChecked)
            Log.i("Checked_Test",checkedList.toString())

        }
        return checkedList
    }


}