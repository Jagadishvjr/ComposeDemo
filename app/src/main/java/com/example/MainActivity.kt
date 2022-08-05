package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigation.SetUpNavigation
import com.example.ui.theme.AppComposeTheme
import com.example.ui.viewmodels.ProposalDetailsScreenViewModel
import com.example.ui.viewmodels.ProposalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController : NavHostController
    private val proposalViewModel : ProposalViewModel by viewModels()
    private val proposalDetailViewModel : ProposalDetailsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeTheme {
                //ScaffoldSample()
                navController = rememberNavController()
                SetUpNavigation(
                    proposalDetailViewModel = proposalDetailViewModel,
                    proposalViewModel = proposalViewModel,
                    navController = navController
                )
            }
        }
    }


    @Composable
    fun ScaffoldSample() {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopAppBar(title = {Text("Top App Bar")},backgroundColor = MaterialTheme.colors.primary)  },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = { FloatingActionButton(onClick = {}){
                Icon(imageVector = Icons.Default.Add, contentDescription = "fab icon")
            } },
            drawerContent = { Text(text = "Drawer Menu 1") },
            content = { Text("Content") },
            bottomBar = { BottomAppBar(backgroundColor = MaterialTheme.colors.primary) { Text("Bottom App Bar") } }
        )
    }
}