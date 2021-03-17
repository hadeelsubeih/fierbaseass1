package com.example.myapplicationfirebaseass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity(), Adapter.onClick {

    private val fb: FirebaseFirestore? = FirebaseFirestore.getInstance()

    private val List = ArrayList<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
        save.setOnClickListener {
            if(Name.text.toString().isNotEmpty()&& Num.text.toString().isNotEmpty()&&Address.text.toString().isNotEmpty())
                AddData(Name.text.toString(),Num.text.toString() , Address.text.toString())

        }
    }

    fun AddData(Name: String, Number: String, Address: String) {
        val person: MutableMap<String, Any> = HashMap()
        val Id = UUID.randomUUID().toString()
        person["id"] =Id
        person["name"] = Name
        person["number"] = Number
        person["address"] = Address

        fb!!.collection("person")!!.document(Id)
            .set(person)
            .addOnSuccessListener { documentReference -> getData() }
            .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }

    }
    private fun getData() {
        List.clear()
        fb!!.collection("person")
            .get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    List?.add(document.toObject(ListData::class.java));
                }
                val adapter = Adapter(this, List ,this )
                myRecyclerView.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                myRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }



    }
    override fun onClick(id: Int, position: Int) {
        fb!!.collection("person").document(id.toString())
            .delete()
            .addOnSuccessListener {
                List.removeAt(position)
                val adapter = Adapter(this, List ,this )
                myRecyclerView.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false)
                myRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e -> Log.w("TAG", "Error deleting document", e) }    }



}
