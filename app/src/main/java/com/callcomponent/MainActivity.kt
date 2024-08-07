package com.callcomponent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var button: Button
    private var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextPhone)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            phoneNumber = editText.text.toString()
            startCallIntent(phoneNumber)
        }

    }

    fun startCallIntent(phoneNumber: String){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),100)
            //
        }else{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel: $phoneNumber")
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel: $phoneNumber")
            startActivity(intent)
        }
    }
}