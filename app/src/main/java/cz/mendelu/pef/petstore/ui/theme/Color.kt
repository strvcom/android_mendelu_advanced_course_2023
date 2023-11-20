package cz.mendelu.pef.petstore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightBackground = Color(0xFFFFFFFF)
val DarkBackground = Color(0xFF000000)

val LightTextColor = Color(0xFF000000)
val DarkTextColor = Color(0xFFFFFFFF)

@Composable
fun getBackgroundColor() = if (isSystemInDarkTheme()) DarkBackground else LightBackground

@Composable
fun basicTextColor(): Color = if (isSystemInDarkTheme()) DarkTextColor else LightTextColor

@Composable
fun getTintColor() = if (isSystemInDarkTheme()) Color.White else Color.Black

