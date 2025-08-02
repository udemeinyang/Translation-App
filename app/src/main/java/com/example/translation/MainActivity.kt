package com.example.translation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val inputEditTextEditText = findViewById<EditText>(R.id.inputEditText)
        val translateButton = findViewById<Button>(R.id.TranslateButton)t>(R.id.inputEditText)
        val outputText = findViewById<TextView>(R.id.outputText)>(R.id.inputEditText)

        // Create an English-German translator:
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.GERMAN)
            .build()
        val englishGermanTranslator = Translation.getClient(options)
    }
}