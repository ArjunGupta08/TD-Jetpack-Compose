package com.arjun.moneydividercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arjun.moneydividercompose.ui.theme.MoneyDividerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyDividerComposeTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val amount = remember {
        mutableStateOf("")
    }
    val personCounter = remember {
        mutableStateOf(1)
    }
    val tipPercentage = remember {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopTotal(
            amount = getTotalHeaderAmount(
                amount.value,
                personCounter.value,
                tipPercentage =  getTipAmount(amount.value, tipPercentage.value).toFloat()
            )
        )

        UserInput(
            amount = amount.value,
            amountChange = { amount.value = it },
            personCounter = personCounter.value,
            onAddOrRedusePerson = {
                if (it < 0) {
                    if (personCounter.value != 1) {
                        personCounter.value--
                    }
                }else {
                    personCounter.value++
                }
            },
            tipPercentage.value,
            { tipPercentage.value = it })
    }
}





