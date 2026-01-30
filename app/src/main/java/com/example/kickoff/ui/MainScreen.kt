package com.example.kickoff.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.kickoff.GROUPS_SCREEN

import com.example.kickoff.RULES_SCREEN
import com.example.kickoff.data.PredictionViewModel

val Green = Color(0xFF1E6030)
val Yellow = Color(0xFFFFFF00)


@Composable
fun MainScreen(navController : NavHostController,
               viewModel: PredictionViewModel = viewModel())
{
    val icons = viewModel.iconsState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TitleLogo(icon = icons.trophy)

        Description(
            title="WORLD CUP",
            year = "2026",
            subtitle = "Napravite svoju vlastitu prognozu Svjetskog prvenstva 2026 – od grupne faze do finala.Izgradite svoj idealni ždrijeb nokaut-faze i pokažite vaše znanje!"
        )
        StartButtons(
            rulesIcon = icons.checklist,
            userIcon = icons.user,
            rulesTitle = "Pravila igre",
            startTitle = "Pokreni igru",
            rulesOnClick = {navController.navigate(RULES_SCREEN)},
            userOnClick = {navController.navigate(GROUPS_SCREEN)}
        )
    }
}

@Composable
fun TitleLogo(
     icon: String
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White)) {
                    append("Kick")
                }
                withStyle(style = SpanStyle(color = Yellow)) {
                    append("OFF")
                }
            },
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .size(320.dp)
                .clip(CircleShape)
                .background(Yellow),
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = icon,
                contentDescription = "LogoTrofeja",
                modifier = Modifier.size(300.dp)
            )
        }
    }
}
@Composable
fun Description(
    title: String,
    year: String,
    subtitle: String
)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ){
        Text(
            text=title,
            color=Yellow,
            fontSize=42.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = year,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            color = Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}
@Composable
fun StartButtons(
     rulesIcon: String,
     userIcon: String,
    rulesTitle : String,
    startTitle : String,
    rulesOnClick: () -> Unit,
    userOnClick : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(
            onClick = rulesOnClick,
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
                AsyncImage(
                    model = rulesIcon,
                    contentDescription = "checklist",
                    colorFilter = ColorFilter.tint(Green),
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = rulesTitle,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Button(
            onClick = userOnClick,
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
                AsyncImage(
                    model = userIcon,
                    contentDescription = "logoHuman",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = startTitle,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

