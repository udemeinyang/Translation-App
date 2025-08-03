package com.example.translation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {

    private lateinit var translator: Translator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val translateButton = findViewById<Button>(R.id.TranslateButton)
        val outputText = findViewById<TextView>(R.id.outputText)

        // Create an English-German translator:
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.HINDI)
            .build()
         translator = Translation.getClient(options)


        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                translateButton.setOnClickListener {
                    val textToTranslate = inputEditText.text.toString()
                    translateText(textToTranslate, outputText)
                }
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)
            }
            .addOnFailureListener { exception ->
                outputText.text = "Model Download Fails"

                // Model couldn’t be downloaded or other internal error.
                // ...
            }
    }

    private fun translateText(inputText: String, outputTextView: TextView){
        translator.translate(inputText)
            .addOnSuccessListener { translatedText ->
                outputTextView.text = translatedText
                // Translation successful.
            }
            .addOnFailureListener { exception ->
                outputTextView.text = "Translation Fails"
                // Error.
                // ...
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        translator.close()
    }
}