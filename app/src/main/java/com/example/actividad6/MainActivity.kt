package com.example.actividad6

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var selectDate:TextView
    private lateinit var getAge:Button
    private lateinit var showAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDate = findViewById(R.id.selectDate)
        getAge = findViewById(R.id.getAge)
        showAge = findViewById(R.id.showAge)

        selectDate.setOnClickListener{
            var calendar = Calendar.getInstance()
            var calendarDay = calendar.get(Calendar.DAY_OF_MONTH)
            var calendarMonth = calendar.get(Calendar.MONTH)
            var calendarYear = calendar.get(Calendar.YEAR)

            val calendarDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, year, month, dayOfMonth ->
                calendarDay = dayOfMonth
                calendarMonth = month
                calendarYear = year
                getAge.visibility = View.VISIBLE
                    textMessage("Tu fecha de nacimiento es :$calendarDay/${calendarMonth + 1}/$calendarYear")
                getAge.setOnClickListener {
                    val currYear = Calendar.getInstance().get(Calendar.YEAR)
                    var currMonth = Calendar.getInstance().get(Calendar.MONTH)
                    val initialMonth = currMonth
                    val age = currYear - calendarYear
                    val month = calendarMonth - currMonth

                    // checar si ya cumplio años o todavia no
                    if (month >= 0) {
                        showAge.visibility = View.VISIBLE
                        showAge.text = "Tu edad es ${age - 1} años ${initialMonth + 1} meses"
                        textMessage("Tu edad es ${age - 1} años ${initialMonth + 1} meses")
                    } else {
                        showAge.visibility = View.VISIBLE
                        showAge.text = "Tu edad es $age años ${month + 1} meses"
                        textMessage("Tu edad es $age años ${month + 1} meses")
                    }

                }
                    selectDate.text = "Tu fecha de nacimiento es :$calendarDay/${calendarMonth + 1}/$calendarYear"
            }, calendarYear, calendarMonth, calendarDay)
            calendarDialog.show()
        }
    }

    private fun textMessage(s: String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}