package cz.mendelu.pef.petstore.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.petstore.ui.theme.Purple40
import cz.mendelu.pef.petstore.ui.theme.getBackgroundColor

@Composable
fun LoadingScreen(
    modifier: Modifier,
){
    Column(modifier = modifier.fillMaxSize()
        .background(getBackgroundColor()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = Purple40,
            strokeWidth = 5.dp)
    }
}