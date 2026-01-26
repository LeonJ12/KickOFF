package com.example.kickoff.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.kickoff.R
import com.example.kickoff.SEMIFINALS_SCREEN
import com.example.kickoff.data.PredictionViewModel
import com.example.kickoff.data.Team



@Composable
fun QuarterfinalsScreen(
    navController : NavHostController,
    viewModel: PredictionViewModel = viewModel()
)
{
    val matches = viewModel.quarterFinalMatches
    val isReady = viewModel.isQuarterfinalComplete()
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
                backOnClick = { navController.popBackStack()}
            )
            Header(
                text = "ČETVRTFINALE"
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(matches.size) { index ->
                val match = matches[index]
                MatchCard(
                    matchNumber = index + 1,
                    team1 = match.first,
                    team2 = match.second,
                    matchIndex = index,
                    stage = "QF",
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
                    viewModel.prepareSemifinals()
                    navController.navigate(SEMIFINALS_SCREEN)
                }
            }
        )
    }
}

@Composable
fun MatchCard(
    matchNumber: Int,
    team1: Team,
    team2: Team,
    matchIndex: Int,
    stage: String,
    viewModel: PredictionViewModel
) {
    val winner = viewModel.knockoutWinners["${stage}_$matchIndex"]
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "UTAKMICA $matchNumber",
                color = Green,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            MatchTeamItem(
                team = team1,
                isSelected = winner == team1,
                onClick = { viewModel.selectKnockoutWinner(stage, matchIndex, team1) }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "VS",
                    color = Green,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            MatchTeamItem(
                team = team2,
                isSelected = winner == team2,
                onClick = { viewModel.selectKnockoutWinner(stage, matchIndex, team2) }
            )

        }
    }
}
@Composable
fun MatchTeamItem(
    team: Team,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Green else Color.Transparent)
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = team.flag,
            contentDescription = "Zastava",
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = team.name,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}