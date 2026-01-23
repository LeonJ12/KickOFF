package com.example.kickoff.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kickoff.FINALS_SCREEN
import com.example.kickoff.R
import com.example.kickoff.data.PredictionViewModel



@Composable
fun SemiFinalsScreen(navController : NavHostController,
                     viewModel: PredictionViewModel = viewModel()
)
{
    val matches = viewModel.semiFinalMatches
    val isReady = viewModel.isSemifinalComplete()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallButton(
                icon = R.drawable.arrow_back,
                backOnClick = {navController.popBackStack() }
            )
            Header(
                text = "POLUFINALE"
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(100.dp, Alignment.CenterVertically)
        ){
            items(matches.size) { index ->
                val match = matches[index]
                MatchCard(
                    matchNumber = index + 1,
                    team1 = match.first,
                    team2 = match.second,
                    matchIndex = index,
                    stage = "SF", // Oznaka faze
                    viewModel = viewModel
                )
            }
        }
        ContinueButton(
            icon = R.drawable.fast_forward_filled,
            continueTitle = "Slijedeća faza",
            isEnabled = isReady,
            continueClick = {
                if (isReady) {
                    viewModel.prepareFinals()
                    navController.navigate(FINALS_SCREEN)
                }
            }
        )
    }
}