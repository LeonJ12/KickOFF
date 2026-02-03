package com.example.kickoff.data


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class PredictionViewModel : ViewModel() {
    val groups = mutableStateListOf<Group>()
    val db = Firebase.firestore

    init {
        fetchGroups()
        fetchIcons()
    }
    private fun fetchGroups() {
        db.collection("groups")
            .get()
            .addOnSuccessListener { result ->
                val loadedGroups = result.documents.map { doc ->
                    groups.clear()
                        val groupName = doc.getString("name") ?: ""

                    val teamsRaw = doc.get("teams") as? List<Map<String, String>> ?: emptyList()

                        val teamList = teamsRaw.map { teamMap ->
                            Team(
                                name = teamMap["name"] ?: "",
                                flag = teamMap["flag"] ?: ""
                            )
                        }
                        Group(groupName, teamList)
                }
                groups.addAll(loadedGroups.sortedBy { it.name })
            }
    }
    var iconsState by mutableStateOf(AppIcons())
    private fun fetchIcons()
    {
        db.collection("icons")
            .get()
            .addOnSuccessListener { result ->
                val dokument = result.documents[0]
                iconsState = AppIcons(
                    trophy = dokument.getString("trophy") ?: "",
                    checklist = dokument.getString("checklist") ?: "",
                    user = dokument.getString("user") ?: "",
                    arrowBack = dokument.getString("arrowBack") ?: "",
                    arrowForward = dokument.getString("arrowForward") ?: ""
                )
            }
    }
     val selectedGroupTeams = mutableStateMapOf<String, MutableList<Team>>()
     val knockoutWinners = mutableStateMapOf<String, Team>()



    var quarterFinalMatches = mutableStateListOf<Pair<Team, Team>>()
    var semiFinalMatches = mutableStateListOf<Pair<Team, Team>>()
    var finalMatch: Pair<Team, Team>? = null



    fun toggleGroupSelection(groupName: String, team: Team) {
        val currentList = selectedGroupTeams.getOrPut(groupName) { mutableStateListOf() }

        if (currentList.contains(team)) {
            currentList.remove(team)
        } else {
            if (currentList.size < 2) {
                currentList.add(team)
            }
        }
    }

    fun areGroupsCompleted(): Boolean {

        return groups.all { selectedGroupTeams[it.name]?.size == 2 }
    }



    fun prepareQuarterfinals() {
        if (quarterFinalMatches.isNotEmpty()) return


        val groupA = selectedGroupTeams["A"]!!
        val groupB = selectedGroupTeams["B"]!!
        val groupC = selectedGroupTeams["C"]!!
        val groupD = selectedGroupTeams["D"]!!

        quarterFinalMatches.add(Pair(groupA[0], groupB[1]))
        quarterFinalMatches.add(Pair(groupB[0], groupA[1]))
        quarterFinalMatches.add(Pair(groupC[0], groupD[1]))
        quarterFinalMatches.add(Pair(groupD[0], groupC[1]))
    }

    fun prepareSemifinals() {
        semiFinalMatches.clear()

        val qf1Winner = knockoutWinners["QF_0"]
        val qf2Winner = knockoutWinners["QF_1"]
        val qf3Winner = knockoutWinners["QF_2"]
        val qf4Winner = knockoutWinners["QF_3"]

        if (qf1Winner != null && qf2Winner != null) semiFinalMatches.add(Pair(qf1Winner, qf2Winner))
        if (qf3Winner != null && qf4Winner != null) semiFinalMatches.add(Pair(qf3Winner, qf4Winner))
    }

    fun prepareFinals() {
        val sf1Winner = knockoutWinners["SF_0"]
        val sf2Winner = knockoutWinners["SF_1"]

        if (sf1Winner != null && sf2Winner != null) {
            finalMatch = Pair(sf1Winner, sf2Winner)
        }
    }



    fun selectKnockoutWinner(stagePrefix: String, matchIndex: Int, winner: Team) {
        knockoutWinners["${stagePrefix}_$matchIndex"] = winner
    }


    fun isQuarterfinalComplete(): Boolean = (0..3).all { knockoutWinners.containsKey("QF_$it") }
    fun isSemifinalComplete(): Boolean = (0..1).all { knockoutWinners.containsKey("SF_$it") }
    fun isFinalComplete(): Boolean = knockoutWinners.containsKey("FINAL_0")

    fun resetGame() {
        selectedGroupTeams.clear()
        knockoutWinners.clear()
        quarterFinalMatches.clear()
        semiFinalMatches.clear()
        finalMatch = null
    }
}