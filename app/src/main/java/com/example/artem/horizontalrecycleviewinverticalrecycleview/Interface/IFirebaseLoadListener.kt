package com.example.artem.horizontalrecycleviewinverticalrecycleview.Interface

import com.example.artem.horizontalrecycleviewinverticalrecycleview.Model.ItemGroup

interface IFirebaseLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>)
    fun onFirebaseLoadFailed(message: String)
}