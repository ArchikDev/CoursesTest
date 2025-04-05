package com.example.coursestest.presentation.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coursestest.R
import com.example.coursestest.presentation.navigation.FavoriteRoute
import com.example.coursestest.presentation.navigation.MainRoute
import com.example.coursestest.presentation.navigation.ProfileRoute
import com.example.coursestest.presentation.theme.DarkGray
import com.example.coursestest.presentation.theme.Green
import com.example.coursestest.presentation.theme.LightGray
import com.example.coursestest.presentation.theme.Stroke
import com.example.coursestest.presentation.theme.White
import com.example.coursestest.utils.DisableRippleEffect
import com.example.coursestest.utils.HEIGHT_NAVIGATION_BAR

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        NavigationItem.Main,
        NavigationItem.Favorite,
        NavigationItem.Profile,
    )

    val currentRoute = items.find { item ->
        currentDestination?.hasRoute(item.route::class) == true
    }?.route

    val selectedIndex = remember { mutableIntStateOf(0) }
    val colorItemBar = remember { Animatable(LightGray) }

    LaunchedEffect(selectedIndex) {
        colorItemBar.animateTo(DarkGray, animationSpec = tween(300))
    }

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .background(DarkGray)
            .height(HEIGHT_NAVIGATION_BAR)
    ) {
        HorizontalDivider(
            modifier = Modifier,
            thickness = 1.dp, color = Stroke
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            items.forEach { item ->
                if (currentRoute != null) {
                    ItemBar(
                        item = item,
                        currentRoute = currentRoute
                    ) {
                        navController.navigate(item.route)
                    }
                }
            }

        }
    }
}

@Composable
fun ItemBar(
    item: NavigationItem,
    currentRoute: Any,
    onClick: () -> Unit
) {
    val durationMillis = 200

    val animatedColor by animateColorAsState(
        targetValue = if (currentRoute == item.route) Green else White,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearEasing,
        ),
        label = "color"
    )

    val animatedColorBg by animateColorAsState(
        targetValue = if (currentRoute == item.route) LightGray else DarkGray,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = LinearEasing,
        ),
        label = "color"
    )

    DisableRippleEffect {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Box(modifier = Modifier
                .width(64.dp)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(animatedColorBg)
            ) {
                Icon(
                    painter = painterResource(id = item.icon.resId),
                    contentDescription = null,
                    tint = animatedColor,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(item.icon.width)
                        .height(item.icon.height)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(id = item.titleResId),
                style = MaterialTheme.typography.labelSmall,
                color = animatedColor
            )
        }
    }
}

sealed class NavigationItem(
    val route: Any,
    val titleResId: Int,
    val icon: IconInfo,
) {
    data object Main: NavigationItem(
        route = MainRoute,
        titleResId = R.string.navigation_item_home,
        icon = IconInfo(R.drawable.icon_home, 20.dp, 19.dp)
    )

    data object Favorite: NavigationItem(
        route = FavoriteRoute,
        titleResId = R.string.navigation_item_favorite,
        icon = IconInfo(R.drawable.icon_fav)
    )


    data object Profile: NavigationItem(
        route = ProfileRoute,
        titleResId = R.string.navigation_item_profile,
        icon = IconInfo(R.drawable.icon_user)
    )
}

data class IconInfo(
    val resId: Int,
    val width: Dp = 16.dp,
    val height: Dp = 20.dp,
)