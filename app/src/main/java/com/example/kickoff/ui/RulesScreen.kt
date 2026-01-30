package com.example.kickoff.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kickoff.data.PredictionViewModel

@Composable
fun RulesScreen(navController: NavHostController,
                viewModel: PredictionViewModel = viewModel()) {

    val icons = viewModel.iconsState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            SmallButton(
                icon = icons.arrowBack,
                backOnClick = { navController.popBackStack() }
            )
        }

        Text(
            text = "PRAVILA",
            color = Yellow,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RuleItem(
                number = 1,
                text = "Odaberite 2 ekipe iz svake grupe koje prolaze u nokaut fazu."
            )
            RuleItem(
                number = 2,
                text = "U četvrtfinalu odaberite pobjednike svih utakmica."
            )
            RuleItem(
                number = 3,
                text = "U polufinalu odaberite pobjednike obje utakmice."
            )
            RuleItem(
                number = 4,
                text = "U finalu odaberite prvaka Svjetskog prvenstva 2026!"
            )
            RuleItem(
                number = 5,
                text = "Cilj je pogoditi ishod i napraviti svoje idealno Svjetsko prvenstvo."
            )
        }
    }
}

@Composable
fun RuleItem(number: Int, text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        )
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Yellow, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number.toString(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
