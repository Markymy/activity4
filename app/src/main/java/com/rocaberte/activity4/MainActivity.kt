package com.rocaberte.activity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    // UI components
    private lateinit var radioLightMode: RadioButton
    private lateinit var radioDarkMode: RadioButton
    private lateinit var checkboxPushNotifications: CheckBox
    private lateinit var emailEditText: EditText
    private lateinit var nicknameEditText: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        // Bind UI components
        radioLightMode = findViewById(R.id.radioLightMode)
        radioDarkMode = findViewById(R.id.radioDarkMode)
        checkboxPushNotifications = findViewById(R.id.checkboxPushNotifications)
        emailEditText = findViewById(R.id.emailEditText)
        nicknameEditText = findViewById(R.id.nicknameEditText)
        btnSave = findViewById(R.id.btnSave)

        loadSettings()

        btnSave.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSettings() {
        emailEditText.setText(sharedPreferences.getString("email", ""))
        nicknameEditText.setText(sharedPreferences.getString("nickname", ""))
        if (sharedPreferences.getBoolean("lightMode", true)) {
            radioLightMode.isChecked = true
        } else {
            radioDarkMode.isChecked = true
        }
        checkboxPushNotifications.isChecked = sharedPreferences.getBoolean("pushNotifications", false)
    }

    private fun saveSettings() {
        val editor = sharedPreferences.edit()
        editor.putString("email", emailEditText.text.toString())
        editor.putString("nickname", nicknameEditText.text.toString())
        editor.putBoolean("lightMode", radioLightMode.isChecked)
        editor.putBoolean("pushNotifications", checkboxPushNotifications.isChecked)
        editor.apply()

        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show()
    }
}
