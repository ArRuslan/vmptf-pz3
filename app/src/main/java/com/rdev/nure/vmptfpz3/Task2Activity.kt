package com.rdev.nure.vmptfpz3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rdev.nure.vmptfpz3.ui.theme.VMPtFPz3Theme

class Task2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VMPtFPz3Theme {
                TaskCol()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCol() {
    val operations = listOf("+", "-", "*", "/")
    val context = LocalContext.current

    var num1 by remember { mutableStateOf("0") }
    var num2 by remember { mutableStateOf("0") }
    var op by remember { mutableStateOf("+") }
    var resultText by remember { mutableStateOf("0") }
    var opExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        ExposedDropdownMenuBox(
            expanded = opExpanded,
            onExpandedChange = {opExpanded = it},
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = op,
                onValueChange = {},
                readOnly = true,
                label = { Text("Operation") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = opExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(expanded = opExpanded, onDismissRequest = { opExpanded = false }) {
                operations.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            op = selectionOption
                            opExpanded = false
                        },
                        text = {
                            Text(
                                text = selectionOption
                            )
                        },
                    )
                }
            }

        }
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = {
                var num1int: Int
                var num2int: Int
                try {
                    num1int = num1.toInt()
                    num2int = num2.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Invalid number!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val result = when(op) {
                    "+" -> num1int + num2int
                    "-" -> num1int - num2int
                    "*" -> num1int * num2int
                    "/" -> num1int / num2int
                    else -> 0
                }

                resultText = result.toString();
            },
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(
                text = "Calculate"
            )
        }
        TextField(
            value = resultText,
            onValueChange = {},
            readOnly = true,
            label = { Text("Result") },
            singleLine = true,
        )
    }

}