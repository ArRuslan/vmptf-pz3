package com.rdev.nure.vmptfpz3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rdev.nure.vmptfpz3.ui.theme.VMPtFPz3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VMPtFPz3Theme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ActivityButton(text = "Task 1", cls = Task1Activity::class.java)
                    ActivityButton(text = "Task 2", cls = Task2Activity::class.java)
                    ActivityButton(text = "Task 3", cls = Task1Activity::class.java)
                    ActivityButton(text = "Task 4", cls = Task1Activity::class.java)
                }
            }
        }
    }
}

@Composable
fun ActivityButton(text: String, cls: Class<*>) {
    val context = LocalContext.current;

    Button(
        onClick = {
            context.startActivity(Intent(context, cls))
        },
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(
            text = text,
        )
    }
}