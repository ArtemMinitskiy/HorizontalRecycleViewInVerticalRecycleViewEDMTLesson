package com.example.artem.horizontalrecycleviewinverticalrecycleview

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Adapter.MyGroupAdapter
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Interface.IFirebaseLoadListener
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Model.ItemData
import com.example.artem.horizontalrecycleviewinverticalrecycleview.Model.ItemGroup
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IFirebaseLoadListener {

    lateinit var dialog: AlertDialog
    lateinit var iFirebaseLoadListener: IFirebaseLoadListener
    lateinit var data: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = SpotsDialog.Builder().setContext(this).build()
        data = FirebaseDatabase.getInstance().getReference("MyData")
        iFirebaseLoadListener = this

        recycle_view.setHasFixedSize(true)
        recycle_view.layoutManager = LinearLayoutManager(this)

        getFirebaseData()
    }

    private fun getFirebaseData() {

        dialog.show()

        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadListener.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val itemGroups = ArrayList<ItemGroup>()
                for (snapshot in dataSnapshot.children){
                    val itemGroup = ItemGroup()
                    itemGroup.headerTitle = snapshot.child("headerTitle").getValue(true).toString()
                    val t = object: GenericTypeIndicator<ArrayList<ItemData>>(){}
                    itemGroup.listItem = snapshot.child("listItem").getValue(t)
                    itemGroups.add(itemGroup)
                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups)
            }

        })
    }

    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(this, itemGroupList)
        recycle_view.adapter = adapter
        dialog.dismiss()
    }

    override fun onFirebaseLoadFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
