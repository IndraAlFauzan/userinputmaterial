package com.example.userinputmaterial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userinputmaterial.data.DummyData
import com.example.userinputmaterial.layout.MyViewModel
import com.example.userinputmaterial.ui.theme.UserinputmaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserinputmaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Register()

                }
            }
        }
    }
}

@Composable
fun Rbl(
    mItems: List<String>,
    selected: String,
    setSelected: (sl: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        mItems.forEach { item ->
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (selected == item),
                        onClick = { setSelected(item) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (item == selected),
                    onClick = { setSelected(item) }
                )
                Text(text = item)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Register() {
    var userName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var showUsername by remember {
        mutableStateOf("")
    }
    val getData by remember {
        mutableStateOf(MyViewModel())
    }
    val kinds = DummyData.jenisKelamin
    val (selected, setSelected) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = userName,
            label = { Text(text = "Username") },
            onValueChange = { userName = it },
            placeholder = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )

        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Email") },
            onValueChange = { email = it },
            placeholder = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Jenis Kelamin: ")
        }
        Rbl(
            mItems = kinds,
            selected = selected,
            setSelected = setSelected
        )

        Spacer(modifier = Modifier.padding(10.dp))


        Button(onClick = {
            getData.getData(userName, email, selected)

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        // Move the following code inside a Composable function
        val data = getData.uiState.collectAsState()

        // Display the data
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Username : ${data.value.name}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Email : ${data.value.email}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Jenis Kelamin : ${data.value.sex}", fontWeight = FontWeight.Bold)
            }

        }
    }

}

