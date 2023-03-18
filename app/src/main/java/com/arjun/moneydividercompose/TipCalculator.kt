package com.arjun.moneydividercompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopTotal(amount: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.Cyan,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Total", style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "$ ${formatTwoDecimalPoint(amount)}", style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInput(
    amount: String,
    amountChange: (String) -> Unit,
    personCounter: Int,
    onAddOrRedusePerson: (Int) -> Unit,
    tipPercentage: Float,
    tipPercentageChange: (Float) -> Unit
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = amount, onValueChange = { amountChange.invoke(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Enter Your Amount") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyBoardController?.hide()
                })
            )

            Spacer(modifier = Modifier.height(12.dp))

            if(amount != ""){

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Split", style = MaterialTheme.typography.body1)

                    Spacer(modifier = Modifier.fillMaxWidth(.50f))

                    CustomButton(imageVector = Icons.Default.Add) {
                        onAddOrRedusePerson.invoke(+1)
                    }
                    Text(
                        text = "$personCounter",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    CustomButton(imageVector = Icons.Default.KeyboardArrowDown) {
                        onAddOrRedusePerson.invoke(-1)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tip", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.fillMaxWidth(.7f))
                    Text(
                        text = "$ ${formatTwoDecimalPoint(getTipAmount(amount, tipPercentage))}",
                        style = MaterialTheme.typography.body1
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "${formatTwoDecimalPoint(tipPercentage.toString())} %", style = MaterialTheme.typography.body1)

                Spacer(modifier = Modifier.height(8.dp))

                Slider(
                    value = tipPercentage,
                    onValueChange = {
                        tipPercentageChange.invoke(it)
                    },
                    valueRange = 0f..100f,
                    steps = 5,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                )

            }

        }
    }
}

@Composable
fun CustomButton(imageVector: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
            .clickable {
                onClick.invoke()
            }, shape = CircleShape
    ) {
        Icon(
            imageVector = imageVector, contentDescription = null, modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
        )
    }
}

