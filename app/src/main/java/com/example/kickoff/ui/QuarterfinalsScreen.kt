package com.example.kickoff.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kickoff.R
import com.example.kickoff.SEMIFINALS_SCREEN
import com.example.kickoff.Team



@Composable
fun QuarterfinalsScreen(navController : NavHostController)
{
    val match1 = Pair(
        Team("Njemačka", R.drawable.germany),
        Team("Hrvatska", R.drawable.croatia)
    )
    val match2 = Pair(
        Team("Škotska", R.drawable.scotland),
        Team("Španjolska", R.drawable.spain)
    )
    val match3 = Pair(
        Team("Slovenija", R.drawable.slovenia),
        Team("Francuska", R.drawable.france)
    )
    val match4 = Pair(
        Team("Danska", R.drawable.denmark),
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
            item { MatchCard(matchNumber = 1, team1 = match1.first, team2 = match1.second) }
            item { MatchCard(matchNumber = 2, team1 = match2.first, team2 = match2.second) }
            item { MatchCard(matchNumber = 3, team1 = match3.first, team2 = match3.second) }
            item { MatchCard(matchNumber = 4, team1 = match4.first, team2 = match4.second) }
        }
        ContinueButton(
            icon = R.drawable.fast_forward_filled,
            continueTitle = "Slijedeća faza",
            continueClick = {navController.navigate(SEMIFINALS_SCREEN)}
        )
    }
}

@Composable
fun MatchCard(
    matchNumber: Int,
    team1: Team,
    team2: Team
) {
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

                MatchTeamItem(team = team1)

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

                MatchTeamItem(team = team2)

        }
    }
}
@Composable
fun MatchTeamItem(
    team: Team
) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(id = team.flag),
            contentDescription = "zastava",
            modifier = Modifier.size(32.dp),
            tint = Color.Unspecified
        )
        Text(
            text = team.name,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}