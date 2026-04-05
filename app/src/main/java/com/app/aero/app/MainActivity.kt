package com.app.aero.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.aero.R
import com.app.aero.core.navigation.Graph
import com.app.aero.core.navigation.Route
import com.app.aero.core.navigation.TopLevelRoute
import com.app.aero.core.snackbar.SnackBarController
import com.app.aero.core.ui.theme.AeroTheme
import com.app.aero.core.util.CollectFlowEvents
import com.app.aero.presentation.component.UiSplashScreen
import com.app.aero.presentation.component.UiTitleSubtitleScreen
import com.app.aero.presentation.feature_feed.UiFeedScreen
import com.app.aero.presentation.feature_feed_details.UiFeedDetailsScreen
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

val LocalNavController =
    compositionLocalOf<NavHostController> { error("CompositionLocal LocalNavController not present") }
val topLevelRoutes = listOf(
    TopLevelRoute("Market", Graph.Market, R.drawable.icon_market),
    TopLevelRoute("Portfolio", Graph.Portfolio, R.drawable.icon_portfolio),
    TopLevelRoute("Orders", Graph.Orders, R.drawable.icon_order),
    TopLevelRoute("User", Graph.User, R.drawable.icon_user),
)

class MainActivity : ComponentActivity() {

    val networkViewModel : ConnectivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AeroTheme {
                val isConnectedToNetwork = networkViewModel.isConnected.collectAsStateWithLifecycle()
                Box{
                    App()
                    if(!isConnectedToNetwork.value){
                        UiTitleSubtitleScreen(
                            "Opps... No internet connection!",
                            "Please check with your internet connection, and try again.",
                            imageVector = Icons.Filled.NetworkCheck
                        )
                    }
                }

            }
        }
    }
}



@Preview(showBackground = true)
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    CollectFlowEvents(SnackBarController.events) {
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            snackBarHostState.showSnackbar(message = it.message, duration = SnackbarDuration.Short)
        }
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Box {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    AnimatedVisibility(
                        visible = currentDestination?.hasRoute<Route.SplashScreen>() != true,
                    ){
                        NavigationBar(
                            modifier = Modifier
                                .wrapContentSize()
                                .shadow(10.dp),
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.surface
                        ) {
                            topLevelRoutes.forEach { topLevelRoute ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(topLevelRoute.icon),
                                            contentDescription = topLevelRoute.name,
                                        )
                                    },
                                    label = { Text(topLevelRoute.name) },
                                    selected = currentDestination
                                        ?.hierarchy
                                        ?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                                    onClick = {
                                        navController.navigate(topLevelRoute.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // re-selecting the same item
                                            launchSingleTop = true
                                            // Restore state when re-selecting a previously selected item
                                            restoreState = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors().copy(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        selectedIndicatorColor = Color.Transparent,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.5f
                                        ),
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.5f
                                        ),
                                    )
                                )
                            }
                        }
                    }
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .imePadding(),
                    startDestination = Route.SplashScreen
                ) {
                    composable<Route.SplashScreen> {
                        UiSplashScreen()
                    }
                    navigation<Graph.Market>(startDestination = Route.FeedList) {
                        allRoutes()
                    }

                    navigation<Graph.Portfolio>(startDestination = Route.ComingSoon) {
                        allRoutes()
                    }

                    navigation<Graph.Orders>(startDestination = Route.ComingSoon) {
                        allRoutes()
                    }

                    navigation<Graph.User>(startDestination = Route.ComingSoon) {
                        allRoutes()
                    }

                }
            }
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopCenter),
                snackbar = { snackBarData ->
                    Snackbar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .windowInsetsTopHeight(WindowInsets.statusBars.add(WindowInsets(top = 60.dp))),
                        shape = RectangleShape,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .safeDrawingPadding(),
                            text = snackBarData.visuals.message,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            )
        }
    }
}


private fun NavGraphBuilder.allRoutes() {
    composable<Route.FeedList>{
        UiFeedScreen()
    }
    composable<Route.FeedDetails> {
        val user: Route.FeedDetails = it.toRoute()
        UiFeedDetailsScreen(user.symbol)
    }
    composable<Route.ComingSoon> {
        UiTitleSubtitleScreen(){}
    }
}