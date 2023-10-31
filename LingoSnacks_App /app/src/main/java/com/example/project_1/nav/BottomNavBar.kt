package com.example.project_1.nav

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun getCurrentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

/**
 * It receives navcontroller to navigate between screens,
*/
@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.height(50.dp).drawBehind {

            val strokeWidth = 2 * density
            val y = 0 + strokeWidth / 2

            drawLine(
                Color.Black,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidth
            )
        }.padding(top = 4.dp)

    ){
        //observe current route to change the icon color,label color when navigated
        val currentRoute = getCurrentRoute(navController)

        val navItems = listOf(Screen.Home)

        //Bottom nav items we declared
        navItems.forEach { navItem ->
            NavigationBarItem(
                //it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,
                modifier = Modifier.padding(0.dp),
                onClick = {
//                    navController.navigate(navItem.route)
                          navController.navigateSingleTopTo(navItem.route)
                },
                icon = {
                    // For each screen either an icon or vector resource is provided
                    navItem.icon ?.let {
                        Icon(imageVector = it,
                            contentDescription = navItem.title,

                            )
                    }

                },
//                label = {
//                    Text(text = navItem.title, color = Color.Black)
//                },
                alwaysShowLabel = false
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route){
        popUpTo(Screen.Home.route){ saveState = true}
        launchSingleTop = true
        restoreState = true
    }
