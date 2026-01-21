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
import androidx.navigation.NavHostController
import com.example.kickoff.FINALS_SCREEN
import com.example.kickoff.R
import com.example.kickoff.Team


@Composable
fun SemiFinalsScreen(navController : NavHostController)
{
    val match1 = Pair(
        Team("Njemačka", R.drawable.germany),
        Team("Španjolska", R.drawable.spain)
    )
    val match2 = Pair(
        Team("Francuska", R.drawable.france),
        Team("Poljska", R.drawable.poland)
    )
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
            item { MatchCard(matchNumber = 1, team1 = match1.first, team2 = match1.second) }
            item { MatchCard(matchNumber = 2, team1 = match2.first, team2 = match2.second) }
        }
        ContinueButton(
            icon = R.drawable.fast_forward_filled,
            continueTitle = "Slijedeća faza",
            continueClick = {navController.navigate(FINALS_SCREEN)}
        )
    }
}