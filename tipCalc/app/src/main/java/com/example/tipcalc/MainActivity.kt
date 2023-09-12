@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalc.ui.theme.TipCalcTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipLayout()
                }
            }
        }
    }
}

@Composable
fun TipLayout( modifier: Modifier = Modifier) {
    Column(
        modifier =
        Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        Arrangement.Center,
        Alignment.CenterHorizontally

    ) {
        var amountField by remember {
            mutableStateOf("")
        }
        var percentField by remember {
            mutableStateOf("")
        }
        var isChecked by remember {
            mutableStateOf(false)
        }
        val amount = amountField.toDoubleOrNull() ?:0.0
        val percent = percentField.toDoubleOrNull()?.div(100) ?:0.15
        val tip:String = calTip(amount,percent,isChecked)
        var showTip  =  
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)

        )

        EditableField(
            value = amountField,
            onValueChange = {amountField = it},
            label = R.string.bill_amount ,
            icon = R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxSize()
        )
        EditableField(
            value = percentField,
            onValueChange = {percentField = it},
            label = R.string.round_up_tip ,
            icon = R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxSize()
        )
        RoundTipRow(isChecked = isChecked , onCheckChange ={isChecked = it} )

        Text(
            stringResource(R.string.tip_amount,tip),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
    }
}

fun calTip (amount:Double,percentage:Double,isChecked: Boolean) : String {
    var tip = amount *   percentage
    if(isChecked) tip = kotlin.math.ceil(tip)
    return  NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditableField(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange : (String)->Unit,
    @StringRes label:Int,
    @DrawableRes icon:Int,
    keyboardOptions: KeyboardOptions
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        leadingIcon = { Icon( painterResource(icon),null )},
        modifier = modifier
    )
}


@Composable
fun RoundTipRow(
    modifier:Modifier = Modifier,
    isChecked:Boolean,
    onCheckChange:(Boolean)->Unit
    ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(40.dp)
    ) {
        Text(
            stringResource(R.string.round_up_tip),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
            )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckChange ,
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .fillMaxWidth()
            )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalcTheme {
        TipLayout()
    }
}