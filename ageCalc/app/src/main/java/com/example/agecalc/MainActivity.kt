package com.example.agecalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.agecalc.ui.theme.AgeCalcTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    private  var showSelectedDate: TextView?=null
    private  var theAgeInMinutes: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSelectedDate = findViewById(R.id.selectedDate)
        theAgeInMinutes = findViewById(R.id.ageMinutes)
        val btnDatePicker:Button =findViewById((R.id.button))
        btnDatePicker.setOnClickListener {
            var someObject = Something()
            someObject.someVar = 5
            println("__________${someObject.someVar}___________")

            clickDatePicker()
        }


//        setContent {
//            Greeting(name = "hellodddd")
////            AgeCalcTheme {
////                // A surface container using the 'background' color from the theme
////                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
////                }
////            }
//        }
    }
    private fun clickDatePicker(){
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val dayOfMonth = calender.get(Calendar.DAY_OF_MONTH)
val lambdaDate = DatePickerDialog.OnDateSetListener{
    view,year,month, dayOfMonth ->
    val theStringDate = "${dayOfMonth}/${month+1}/${year}"
    val theSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val theDate = theSimpleDateFormat.parse(theStringDate)
    val selectedDateInMinutes = theDate.time / 60000
    val currentDateInMinutes = theSimpleDateFormat.parse(theSimpleDateFormat.format(System.currentTimeMillis())).time /60000
    val userAgeInMinutes = currentDateInMinutes - selectedDateInMinutes

    showSelectedDate?.text = theStringDate
    theAgeInMinutes?.text = userAgeInMinutes.toString()
    Toast.makeText(this,"pressed",Toast.LENGTH_LONG).show()
}
// the given year ,month and day are just default to start the datePicker
val theDatePickerDialog = DatePickerDialog(this,lambdaDate ,year,month,dayOfMonth)
    theDatePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    theDatePickerDialog.show()


    }

}

@Composable
fun Greeting( name: String, modifier: () -> Unit) {
Greeting{
    someParameter -> println("_________${someParameter}__________ll")

}
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
}

fun Greeting(modifier: (someParam:String) -> Unit) {

}

fun something():String{
    return "hello"
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AgeCalcTheme {
    }

}
    class Something:AnotherClass() {

        var someVar = 1
         set(value){
             super.ssss()
              field = value
        }
    }
 open class AnotherClass {
    fun ssss (){}
}
