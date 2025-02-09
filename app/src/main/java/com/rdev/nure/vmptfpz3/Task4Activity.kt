package com.rdev.nure.vmptfpz3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.rdev.nure.vmptfpz3.task4.FitnessDatabase
import com.rdev.nure.vmptfpz3.task4.FitnessRepository
import com.rdev.nure.vmptfpz3.task4.FitnessTraining
import com.rdev.nure.vmptfpz3.task4.OfflineFitnessRepository
import com.rdev.nure.vmptfpz3.ui.theme.VMPtFPz3Theme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class Task4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VMPtFPz3Theme {
                TaskActivity()
            }
        }
    }
}

@Composable
fun TaskActivity() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val fitnessRepository: FitnessRepository by lazy {
        OfflineFitnessRepository(FitnessDatabase.getDatabase(context).itemDao())
    }
    val trainingsList by fitnessRepository.getAllTrainingsStream().asLiveData().observeAsState(listOf())
    val avgTime by fitnessRepository.getAvgTimePerDay().asLiveData().observeAsState()
    val totTime by fitnessRepository.getTotTime().asLiveData().observeAsState()
    val dateFormat = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm")

    var timeText by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = timeText,
            onValueChange = { timeText = it },
            singleLine = true,
            label = { Text("Time (minutes)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(4.dp),
        )
        Button(
            onClick = {
                val timeMins: Int
                try {
                    timeMins = timeText.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Invalid number!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if(timeMins <= 0)
                    return@Button

                coroutineScope.launch {
                    fitnessRepository.insertTraining(FitnessTraining(
                        time = timeMins,
                        date = System.currentTimeMillis() / 1000,
                    ));
                }

                Toast.makeText(context, "Added: $timeMins minutes", Toast.LENGTH_SHORT).show()
            },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth().padding(4.dp),
        ) {
            Text(
                text = "Add"
            )
        }
        Text(
            text = "Average training time per day: ${avgTime}"
        )
        Text(
            text = "Total training time: ${totTime}"
        )
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn {
                items(
                    1,
                    contentType = { FitnessTraining::class },
                    key = { it.hashCode() },
                ) {
                    for(training in trainingsList) {
                        Row(
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Column {
                                Text("Date: ${dateFormat.format(training.date * 1000L)}")
                                Text("Training time: ${training.time} minutes")
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}