package com.fred.ktempconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var celciusRadioButton: RadioButton
    private lateinit var fahreinheitRadioButton: RadioButton
    private lateinit var converterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.valorTemp)
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    converterButton.isEnabled = false
                    converterButton.alpha = 0.7f
                } else {
                    converterButton.isEnabled = true
                    converterButton.alpha = 1f
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        celciusRadioButton = findViewById(R.id.celciusRadio)
        fahreinheitRadioButton = findViewById(R.id.fahreinheitRadio)

        converterButton = findViewById(R.id.converterButton)
        converterButton.setOnClickListener { converter() }
    }

    private fun converter() {
        val tempText: String = editText.text.toString()
        var temp: Double? = if (tempText != "") tempText.toDouble() else null

        if (temp != null) {
            if (celciusRadioButton.isChecked) {
                temp = (temp - 32) * 5 / 9
            } else if (fahreinheitRadioButton.isChecked) {
                temp = temp * 9 / 5 + 32
            }

            editText.setText(temp.toString())
        }
    }
}