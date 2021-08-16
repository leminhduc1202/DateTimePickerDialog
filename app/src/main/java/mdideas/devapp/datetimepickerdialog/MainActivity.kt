package mdideas.devapp.datetimepickerdialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import mdideas.devapp.datetimepickerdialog.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedday = 0
    var savedmonth = 0
    var savedyear = 0
    var savedhour = 0
    var savedminute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pickDate()
        //Option 2 just show date
        pickDate01()
    }
    //Option 2
    private fun pickDate01() {
        val cal = Calendar.getInstance()
        val year01 = cal.get(Calendar.YEAR)
        val month01 = cal.get(Calendar.MONTH)
        val day01 = cal.get(Calendar.DAY_OF_MONTH)
        val hour01 = cal.get(Calendar.HOUR)
        val minute01 = cal.get(Calendar.MINUTE)
        binding.timePicker01.setOnClickListener {
            val datePD = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                binding.timeShowText01.setText(""+ dayOfMonth + "/" + month + 1 + "/" + year)},year01,month01,day01)
            datePD.show()
        }
    }//Option 2


    private fun pickDate() {
        binding.timePicker.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this,this,year,month,day).show()
        }
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedday = dayOfMonth
        savedmonth = month
        savedyear = year
        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedhour = hourOfDay
        savedminute = minute
        binding.timeShowText.text = "$savedday - $savedmonth - $savedyear \n Hour: $savedhour Minute: $savedminute "
    }
}