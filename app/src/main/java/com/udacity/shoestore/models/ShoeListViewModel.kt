package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {


    private lateinit var shoeList: MutableList<Shoe>

    init {
        initShoeList()
    }

    private val _list = MutableLiveData(shoeList)

    val list: LiveData<MutableList<Shoe>>
        get() = _list


    private fun initShoeList() {

        shoeList = mutableListOf(
            Shoe(
                name = "shoe1",
                size = 10.00,
                company = "Adidas",
                description = "Running",
                images = mutableListOf("00")
            ),
            Shoe(
                name = "shoe2",
                size = 10.00,
                company = "Adidas",
                description = "Running",
                images = mutableListOf("00")
            ),
            Shoe(
                name = "shoe3",
                size = 10.00,
                company = "Adidas",
                description = "Running",
                images = mutableListOf("00")
            ),
            Shoe(
                name = "shoe4",
                size = 10.00,
                company = "Adidas",
                description = "Running",
                images = mutableListOf("00")
            ),
            Shoe(
                name = "shoe5",
                size = 10.00,
                company = "Adidas",
                description = "Running",
                images = mutableListOf("00")
            )
        )


    }

    fun addShoe(shoe: Shoe) {
        _list.value = shoeList.plus(shoe).toMutableList()
    }


}