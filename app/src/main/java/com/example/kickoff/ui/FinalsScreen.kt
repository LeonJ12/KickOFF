package com.example.kickoff.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.kickoff.MAIN_SCREEN
import com.example.kickoff.R
import com.example.kickoff.data.PredictionViewModel
import com.example.kickoff.data.Team


@Composable
fun FinalsScreen(navController : NavHostController,
                 viewModel: PredictionViewModel = viewModel())
{
    val match = viewModel.finalMatch
    val isReady = viewModel.isFinalComplete()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FinalSmallButton(
                icon = R.drawable.arrow_back,
                backOnClick = {navController.popBackStack() }
            )
            FinalHeader(
                text = "FINALE"
            )
        }
        FinalDescription(
            text = "Odaberi pobjednika Svjetskog prvenstva!",
            icon = R.drawable.trophy
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            if (match != null) {
                item {
                    FinalMatchCard(
                        team1 = match.first,
                        team2 = match.second,
                        viewModel = viewModel
                    )
                }
            }
        }
        FinalContinueButton(
            icon = R.drawable.black_user,
            continueTitle = "Novi turnir",
            isEnabled = isReady,
            continueClick = {
                viewModel.resetGame()
                navController.navigate(MAIN_SCREEN)
            }
        )
    }
}
@Composable
fun FinalDescription(
    text: String,
    @DrawableRes icon: Int
)
{
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Yellow
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "trofej",
                modifier = Modifier.size(140.dp),
                tint = Color.Black
            )
            Text(
                text = text,
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }
}
@Composable
fun FinalHeader(
    text : String
)
{
    Text(
        text = text,
        color = Color.Black,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}
@Composable
fun FinalSmallButton(
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
            modifier = Modifier.size(32.dp),
            tint = Color.Black
        )
    }
}
@Composable
fun FinalContinueButton(
    @DrawableRes icon: Int,
    continueTitle: String,
    isEnabled: Boolean = true,
    continueClick: () -> Unit
) {
    Button(
        onClick = continueClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) Color.Black else Color.Gray,
            disabledContainerColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth().height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = continueTitle,
                color = if (isEnabled) Yellow else Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "arrow",
                tint = if (isEnabled) Yellow else Color.LightGray,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
@Composable
fun FinalMatchCard(
    team1: Team,
    team2: Team,
    viewModel: PredictionViewModel
) {
    val winner = viewModel.knockoutWinners["FINAL_0"]

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Green)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = if (winner != null) "Novi prvak je: ${winner.name}" else "Odaberi pobjednika",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            FinalMatchTeamItem(
                team = team1,
                isSelected = winner == team1,
                onClick = { viewModel.selectKnockoutWinner("FINAL", 0, team1) }
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "VS", color = Yellow, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }

            FinalMatchTeamItem(
                team = team2,
                isSelected = winner == team2,
                onClick = { viewModel.selectKnockoutWinner("FINAL", 0, team2) }
            )
        }
    }
}
@Composable
fun FinalMatchTeamItem(
    team: Team,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Yellow else Color.Transparent)
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(id = team.flag),
            contentDescription = "zastava",
            modifier = Modifier.size(35.dp),
            tint = Color.Unspecified
        )
        Text(
            text = team.name,
            color = if (isSelected) Color.Black else Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}