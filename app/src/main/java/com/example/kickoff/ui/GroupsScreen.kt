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

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kickoff.QUARTERFINALS_SCREEN
import com.example.kickoff.R
import com.example.kickoff.data.Team
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kickoff.data.PredictionViewModel


@Composable
fun GroupsScreen(navController : NavHostController,
                 viewModel: PredictionViewModel = viewModel()
) {
    val groups = viewModel.groups
    val isReady = viewModel.areGroupsCompleted()



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
                text = "GRUPNA FAZA"
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(groups.size) { index ->
                val group = groups[index]
                GroupCard(
                    groupName = group.name,
                    teams = group.teams,
                    viewModel = viewModel
                )
            }
        }
        ContinueButton(
            icon = R.drawable.fast_forward_filled,
            continueTitle = "Nokaut faza",
            isEnabled = isReady,
            continueClick = {
                if (isReady) {
                    viewModel.prepareQuarterfinals()
                    navController.navigate(QUARTERFINALS_SCREEN)
                }
            }
        )
    }
}

@Composable
fun GroupCard(
    groupName: String,
    teams: List<Team>,
    viewModel: PredictionViewModel
) {
    val selectedTeams = viewModel.selectedGroupTeams[groupName] ?: emptyList()
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
                text = "GRUPA $groupName",
                color = Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            teams.forEach { team ->
                val isSelected = selectedTeams.contains(team)
                TeamItem(
                    team = team,
                    isSelected = isSelected,
                    onClick = { viewModel.toggleGroupSelection(groupName, team) }
                )
            }
        }
    }
}

@Composable
fun TeamItem(
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
@Composable
fun Header(
    text : String
)
{
    Text(
        text = text,
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ContinueButton(
    @DrawableRes icon: Int,
    continueTitle: String,
    isEnabled: Boolean = true,
    continueClick: () -> Unit
) {
    Button(
        onClick = continueClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) Color.White else Color.Gray,
            contentColor = if (isEnabled) Color.Black else Color.LightGray
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
                text = continueTitle,
                color = if (isEnabled) Color.Black else Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "arrow",
                tint = if (isEnabled) Green else Color.DarkGray,
                modifier = Modifier.size(28.dp)
            )
        }
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