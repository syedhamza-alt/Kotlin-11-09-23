package com.example.taskclass.Actvities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskclass.Adapters.CustomAdapter
import com.example.taskclass.R
import com.example.taskclass.databinding.ActivityOptionBinding
import com.example.taskclass.models.ItemsViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class OptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionBinding
    private lateinit var auth: FirebaseAuth

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }

    private lateinit var sharedpreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding = ActivityOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..2) {

            val genderImage = if (i % 2 == 0) R.drawable.male_ else R.drawable.female__
            val genderText = if (i % 2 == 0) "Male" else "Female"
            data.add(ItemsViewModel(genderImage, genderText));
        }

        val adapter = CustomAdapter(data);

        binding.recyclerview.adapter = adapter;

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logOut -> {
                    val editor = sharedpreferences.edit().clear()
                    editor.apply()
                    val intent = Intent(applicationContext, loginActivity::class.java);
                    startActivity(intent);
                    finishAffinity();
                    true
                }

                else -> false
            }
        }
//        binding.signOutBtn.setOnClickListener {

//            Firebase.auth.signOut();
//            Toast.makeText(applicationContext,"Logged Out", Toast.LENGTH_SHORT).show();
//
//            val intent = Intent(applicationContext, loginActivity::class.java);
//            startActivity(intent);
//            finish();
//
//        }
    }


}

