package eu.tutorials.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.CalendarView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*

class MainActivity : AppCompatActivity() {
    private var  tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
           tvSelectedDate = findViewById(R.id.TvSelectedDate)
            tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
           btnDatePicker.setOnClickListener{
               ClickDatePicker()


           }
    }
    fun ClickDatePicker(){

         val Mycalender = Calendar.getInstance()
         val Year = Mycalender.get(Calendar.YEAR)
         val Month = Mycalender.get(Calendar.MONTH)
         val Day = Mycalender.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener { _, SelectedYear,SelectedMonth,SelectedDayOfMonth ->

               Toast.makeText(this,
                   "Year was $SelectedYear,month was ${SelectedMonth+1} " +
                           "day of month $SelectedDayOfMonth",Toast.LENGTH_LONG ).show()

               val SelectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
               tvSelectedDate?.setText(SelectedDate)

               val sdf = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)

               val theDate = sdf.parse(SelectedDate)
                theDate?.let {
                    val SelectedDateInMinutes =theDate.time/60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time /60000

                        val diffrenceInMinutes = currentDateInMinutes -SelectedDateInMinutes

                        tvAgeInMinutes?.text = diffrenceInMinutes.toString()
                    }

                }


           },
           Year,
           Month,
           Day,
       )


        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}