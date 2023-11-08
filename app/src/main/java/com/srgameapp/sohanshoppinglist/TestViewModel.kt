package com.srgameapp.sohanshoppinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srgameapp.sohanshoppinglist.entities.ShoppingItem
import com.srgameapp.sohanshoppinglist.entities.ShoppingTable

class TestViewModel: ViewModel() {
    lateinit var adapterList:MutableList<ShoppingTable>
    lateinit var sampleList:MutableList<ShoppingItem>



}