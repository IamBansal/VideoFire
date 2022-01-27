package com.example.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class AppMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main)

        val lesson =
            "https://firebasestorage.googleapis.com/v0/b/fir-start-c59b4.appspot.com/o/file_example_MP4_480_1_5MG.mp4?alt=media&token=d087ef52-e31b-488d-81d2-681b8c2d5183"

        findViewById<FloatingActionButton>(R.id.btnFloat).setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("About Developer")
                .setIcon(R.mipmap.photo)
                .setMessage("Developed by Akshat Bansal.")
                .setPositiveButton("Cool") { _, _ -> }
                .create()
                .show()
        }

        val items = ArrayList<CustomItem>()
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("Second", "This is the Second Video", lesson))
        items.add(CustomItem("Third", "This is the third Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))
        items.add(CustomItem("First", "This is the First Video", lesson))

        val adapter = CustomItemAdapter(applicationContext, items)
        val recycler = findViewById<RecyclerView>(R.id.rvRecyclerview)

        recycler.layoutManager = GridLayoutManager(applicationContext, 1)
        recycler.adapter = adapter

    }
}