package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputEditTextSum = findViewById<TextInputEditText>(R.id.textInputEditTextSum)
        val textInputEditTextTip = findViewById<TextInputEditText>(R.id.textInputEditTextTip)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)

        buttonCalculate.setOnClickListener {
            val sumString = textInputEditTextSum.text.toString()
            val tipString = textInputEditTextTip.text.toString()

            if (sumString.isEmpty() || tipString.isEmpty()) {
                Toast.makeText(this, "Введите сумму чека и процент чаевых", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val sum = sumString.toDouble()
                    val tipPercentage = tipString.toDouble()
                    if (tipPercentage < 0) {
                        Toast.makeText(this, "Процент чаевых не может быть отрицательным", Toast.LENGTH_SHORT).show()
                    } else {
                        val finalAmount = calculateFinalAmount(sum, tipPercentage)
                        textViewResult.text = "Сумма к оплате с учетом чаевых: $finalAmount"
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Некорректный ввод. Пожалуйста, введите числовое значение.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun calculateFinalAmount(sum: Double, tipPercentage: Double): Double {
        val tipAmount = sum * (tipPercentage / 100)
        return sum + tipAmount
    }
}
