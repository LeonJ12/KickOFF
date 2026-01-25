package com.example.kickoff.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.kickoff.R
class PredictionViewModel : ViewModel() {


    val groups = listOf(
        Group("A", listOf(Team("Njemačka", R.drawable.germany), Team("Škotska", R.drawable.scotland), Team("Mađarska", R.drawable.hungary), Team("Švicarska", R.drawable.switzerland))),
        Group("B", listOf(Team("Španjolska", R.drawable.spain), Team("Hrvatska", R.drawable.croatia), Team("Italija", R.drawable.italy), Team("Albanija", R.drawable.albania))),
        Group("C", listOf(Team("Slovenija", R.drawable.slovenia), Team("Danska", R.drawable.denmark), Team("Srbija", R.drawable.serbia), Team("Engleska", R.drawable.england))),
        Group("D", listOf(Team("Poljska", R.drawable.poland), Team("Nizozemska", R.drawable.netherlands), Team("Austrija", R.drawable.austria), Team("Francuska", R.drawable.france)))
    )




    private val _selectedGroupTeams = mutableStateMapOf<String, MutableList<Team>>()
    val selectedGroupTeams: Map<String, List<Team>> = _selectedGroupTeams


    private val _knockoutWinners = mutableStateMapOf<String, Team>()
    val knockoutWinners: Map<String, Team> = _knockoutWinners


    var quarterFinalMatches = mutableStateListOf<Pair<Team, Team>>()
    var semiFinalMatches = mutableStateListOf<Pair<Team, Team>>()
    var finalMatch: Pair<Team, Team>? = null



    fun toggleGroupSelection(groupName: String, team: Team) {
        val currentList = _selectedGroupTeams.getOrPut(groupName) { mutableStateListOf() }

        if (currentList.contains(team)) {
            currentList.remove(team)
        } else {
            if (currentList.size < 2) {
                currentList.add(team)
            }
        }
    }

    fun areGroupsCompleted(): Boolean {

        return groups.all { _selectedGroupTeams[it.name]?.size == 2 }
    }



    fun prepareQuarterfinals() {
        if (quarterFinalMatches.isNotEmpty()) return


        val groupA = _selectedGroupTeams["A"]!!
        val groupB = _selectedGroupTeams["B"]!!
        val groupC = _selectedGroupTeams["C"]!!
        val groupD = _selectedGroupTeams["D"]!!

        quarterFinalMatches.add(Pair(groupA[0], groupB[1]))
        quarterFinalMatches.add(Pair(groupB[0], groupA[1]))
        quarterFinalMatches.add(Pair(groupC[0], groupD[1]))
        quarterFinalMatches.add(Pair(groupD[0], groupC[1]))
    }

    fun prepareSemifinals() {
        semiFinalMatches.clear()

        val qf1Winner = _knockoutWinners["QF_0"]
        val qf2Winner = _knockoutWinners["QF_1"]
        val qf3Winner = _knockoutWinners["QF_2"]
        val qf4Winner = _knockoutWinners["QF_3"]

        if (qf1Winner != null && qf2Winner != null) semiFinalMatches.add(Pair(qf1Winner, qf2Winner))
        if (qf3Winner != null && qf4Winner != null) semiFinalMatches.add(Pair(qf3Winner, qf4Winner))
    }

    fun prepareFinals() {
        val sf1Winner = _knockoutWinners["SF_0"]
        val sf2Winner = _knockoutWinners["SF_1"]

        if (sf1Winner != null && sf2Winner != null) {
            finalMatch = Pair(sf1Winner, sf2Winner)
        }
    }



    fun selectKnockoutWinner(stagePrefix: String, matchIndex: Int, winner: Team) {
        _knockoutWinners["${stagePrefix}_$matchIndex"] = winner
    }


    fun isQuarterfinalComplete(): Boolean = (0..3).all { _knockoutWinners.containsKey("QF_$it") }
    fun isSemifinalComplete(): Boolean = (0..1).all { _knockoutWinners.containsKey("SF_$it") }
    fun isFinalComplete(): Boolean = _knockoutWinners.containsKey("FINAL_0")

    fun resetGame() {
        _selectedGroupTeams.clear()
        _knockoutWinners.clear()
        quarterFinalMatches.clear()
        semiFinalMatches.clear()
        finalMatch = null
    }
}