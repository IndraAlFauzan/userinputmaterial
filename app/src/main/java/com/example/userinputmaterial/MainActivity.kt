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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.userinputmaterial.data.DummyData
import com.example.userinputmaterial.data.UserModel
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
                    MainRegister()

                }
            }
        }
    }
}


@Composable
fun MainRegister() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderRegister()
        Spacer(modifier = Modifier.padding(10.dp))
        RegisterForm()
    }
}

@Composable
fun HeaderRegister() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.padding(start = 120.dp))
        Text(text = "Register", fontWeight = FontWeight.Bold)
    }
    Divider()

    Text(
        text = "Create Your Account",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 10.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RegisterForm() {
    val myViewModel: MyViewModel = viewModel()
    var userName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var alamat by remember {
        mutableStateOf("")
    }
    var telp by remember {
        mutableStateOf("")
    }
    val kinds = DummyData.jenisKelamin
    val listStatus = DummyData.status
    var setJenisKelamin by rememberSaveable { mutableStateOf("") }
    var setStatus by rememberSaveable { mutableStateOf("") }


    val data by myViewModel.uiState.collectAsState()
    val userModel: UserModel = data


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedTextField(
            value = telp,
            label = { Text(text = "Telepon") },
            onValueChange = { telp = it },
            placeholder = { Text(text = "Telepon") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            maxLines = 3
        )
        Spacer(modifier = Modifier.padding(5.dp))
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
        radioButton(
            mItems = kinds,
            selected = setJenisKelamin,
            setSelected = { newOption ->
                setJenisKelamin = newOption
                myViewModel.saveJenisKelamin(newOption)
            }
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Status: ")
        }
        radioButton(
            mItems = listStatus,
            selected = setStatus,
            setSelected = { newOption ->
                setStatus = newOption
                myViewModel.saveStatus(newOption)
            }
        )
        OutlinedTextField(
            value = alamat,
            label = { Text(text = "Alamat") },
            onValueChange = { alamat = it },
            placeholder = { Text(text = "Alamat") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            maxLines = 3,


            )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {
            myViewModel.getData(userName, email, userModel.sex, userModel.status, alamat, telp)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        // Display the data
        Card(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {

                item {
                    Text(text = "Jenis Kelamin: ${myViewModel.setJk}")
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                item {
                    Text(text = "Status: ${myViewModel.setStatus}")
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                item {
                    Text(text = "Alamat: ${myViewModel.setAlamat}")
                    Spacer(modifier = Modifier.padding(5.dp))
                }
                item {
                    Text(text = "Email: ${myViewModel.setEmail}")
                    Spacer(modifier = Modifier.padding(5.dp))
                }

            }
        }
    }

}


@Composable
fun radioButton(
    mItems: List<String>,
    selected: String,
    setSelected: (String) -> Unit
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
                    selected = item == selected,
                    onClick = {
                        setSelected(item)
                    }
                )
                Text(text = item)
            }
        }
    }

}

