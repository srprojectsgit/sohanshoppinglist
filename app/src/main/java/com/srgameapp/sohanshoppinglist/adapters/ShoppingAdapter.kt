package com.srgameapp.sohanshoppinglist.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.srgameapp.sohanshoppinglist.R
import com.srgameapp.sohanshoppinglist.databinding.ActivityShoppingAddBinding
import com.srgameapp.sohanshoppinglist.databinding.MainTextItemBinding
import com.srgameapp.sohanshoppinglist.databinding.ShoppingTextItemBinding
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import com.srgameapp.sohanshoppinglist.uis.ShoppingAdd


interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
}

class ShoppingAdapter(val context: Context, var shoppingItems:MutableList<ShoppingItem>): RecyclerView.Adapter<ShoppingAdapter.ViewHolder>(),


    ItemTouchHelperAdapter {
    inner class ViewHolder(val adapterBinding: ShoppingTextItemBinding):RecyclerView.ViewHolder(adapterBinding.root){

    }

    companion object{
        var checkedList:MutableSet<ShoppingItem> = mutableSetOf()


    }
    val myIntent = Intent(context, ShoppingAdd::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ShoppingTextItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adapterBinding.shoppingItemView.text = shoppingItems[position].toString()


        when(holder.adapterBinding.shoppingItemView.text.toString().lowercase()){
            "salt" -> holder.adapterBinding.imageView.setImageResource(R.drawable.salt_picture)
            "sugar" -> holder.adapterBinding.imageView.setImageResource(R.drawable.sugar)
            "potatoes" -> holder.adapterBinding.imageView.setImageResource(R.drawable.potatoes)
            "milk" -> holder.adapterBinding.imageView.setImageResource(R.drawable.milk)
//            "tomatoes" -> holder.adapterBinding.imageView.setImageResource(R.drawable.tomatoes)
//            "rice" -> holder.adapterBinding.imageView.setImageResource(R.drawable.rice)
//            "corn" -> holder.adapterBinding.imageView.setImageResource(R.drawable.corn)
//            "cake" -> holder.adapterBinding.imageView.setImageResource(R.drawable.cake)
//            "ketchup" -> holder.adapterBinding.imageView.setImageResource(R.drawable.ketchup)
//            "apples" -> holder.adapterBinding.imageView.setImageResource(R.drawable.apples)
//            "drinks" -> holder.adapterBinding.imageView.setImageResource(R.drawable.drinks)




        }

//        holder.adapterBinding.checkBox.setOnCheckedChangeListener { compoundButton, b ->
//
//            if(compoundButton.isAccessibilityFocused){
//                Log.i("Changed_True", "Changed to True")
//            }
//
//        }

        checkedList = shoppingItems.toMutableSet()


        holder.adapterBinding.checkBox.setOnClickListener{

            if(holder.adapterBinding.checkBox.isChecked) {
                shoppingItems[position].checked = true
                ShoppingAdapter.checkedList.forEach {
                    if(it === shoppingItems[position]){
                        it.checked = true
                    }
                }

                shoppingItems = checkedList.toMutableList()

            }

            else if(!holder.adapterBinding.checkBox.isChecked){
                if(shoppingItems[position].checked==true){
                    ShoppingAdapter.checkedList.forEach {
                        if(it === shoppingItems[position]){
                            it.checked = false
                        }
                    }

                    shoppingItems = checkedList.toMutableList()

                }

                }



        }




    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val movedItem = shoppingItems.removeAt(fromPosition)
        shoppingItems.add(toPosition, movedItem)
        notifyItemMoved(fromPosition, toPosition)    }

}
