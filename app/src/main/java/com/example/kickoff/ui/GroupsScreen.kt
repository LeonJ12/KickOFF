package com.example.kickoff.ui
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kickoff.R
import com.example.kickoff.Team



@Preview(showBackground = true)
@Composable
fun GroupsScreen() {

    val groupA = listOf(
        Team("Njemačka", R.drawable.germany),
        Team("Škotska", R.drawable.scotland),
        Team("Mađarska", R.drawable.hungary),
        Team("Švicarska", R.drawable.switzerland)
    )

    val groupB = listOf(
        Team("Španjolska", R.drawable.spain),
        Team("Hrvatska", R.drawable.croatia),
        Team("Italija", R.drawable.italy),
        Team("Albanija", R.drawable.albania)
    )

    val groupC = listOf(
        Team("Slovenija", R.drawable.slovenia),
        Team("Danska", R.drawable.denmark),
        Team("Srbija", R.drawable.serbia),
        Team("Engleska", R.drawable.england)
    )

    val groupD = listOf(
        Team("Poljska", R.drawable.poland),
        Team("Nizozemska", R.drawable.netherlands),
        Team("Austrija", R.drawable.austria),
        Team("Francuska", R.drawable.france)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { GroupCard(groupName = "A", teams = groupA) }
            item { GroupCard(groupName = "B", teams = groupB) }
            item { GroupCard(groupName = "C", teams = groupC) }
            item { GroupCard(groupName = "D", teams = groupD) }
        }
        ContinueButton(
            icon = R.drawable.fast_forward_filled,
            continueTitle = "Četvrtfinale",
            continueClick = {}
        )
    }
}

@Composable
fun GroupCard(
    groupName: String,
    teams: List<Team>
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
                text = "GRUPA $groupName",
                color = Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            teams.forEach { team ->
                TeamItem(team = team)
            }
        }
    }
}

@Composable
fun TeamItem(
    team: Team
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
@Composable
fun Header()
{
    Text(
        text = "GRUPNA FAZA",
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ContinueButton(
    @DrawableRes icon : Int,
    continueTitle : String,
    continueClick : () -> Unit
)
{
    Button(
        onClick = continueClick,
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
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "arrow",
                tint = Green,
                modifier = Modifier.size(28.dp)
            )

            Text(
                text = continueTitle,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}