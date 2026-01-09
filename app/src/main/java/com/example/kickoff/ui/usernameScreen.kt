package com.example.kickoff.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kickoff.R

@Preview(showBackground = true)
@Composable
fun UsernameScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart)
        {
            SmallButton(
                icon = R.drawable.arrow_back,
                backOnClick = { }
            )
        }
        InputUser()
        UserButtons(
            guestIcon = R.drawable.green_user,
            arrowIcon = R.drawable.fast_forward_filled,
            guestTitle = "Dohvati zadnju predikciju",
            loginTitle = "Započni turnir",
            getPrediction = { },
            startTournament = { }
        )
    }
}

@Composable
fun SmallButton(
    @DrawableRes icon: Int,
    backOnClick : () -> Unit
) {
    Button(
        onClick = backOnClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "ikonica-arrow",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun InputUser() {
    var username by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Korisničko ime",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Yellow
            ),
            placeholder = {
                Text("Korisničko ime mora biti jedinstveno",
                    color = Color.White)
            }
        )
    }
}
@Composable
fun UserButtons(
    @DrawableRes guestIcon: Int,
    @DrawableRes arrowIcon: Int,
    guestTitle : String,
    loginTitle : String,
    startTournament: () -> Unit,
    getPrediction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(
            onClick = getPrediction,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = guestTitle,
                    color = Green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = guestIcon),
                    contentDescription = "logoHuman",
                    tint = Green,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Button(
            onClick = startTournament,
            colors = ButtonDefaults.buttonColors(
                containerColor = Yellow
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = loginTitle,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = arrowIcon),
                    contentDescription = "logoArrow",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}