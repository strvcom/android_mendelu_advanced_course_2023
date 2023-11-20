package cz.mendelu.pef.compose.todo.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.petstore.ui.theme.basicMargin
import cz.mendelu.pef.petstore.ui.theme.basicTextStyle
import cz.mendelu.pef.petstore.ui.theme.basicTextColor

data class PlaceholderScreenContent(val image: Int?,
                                    val text: String?)

@Composable
fun PlaceHolderScreen(
    modifier: Modifier,
    content: PlaceholderScreenContent){
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)
            .padding(basicMargin())) {

            if (content.image != null) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(300.dp),
                    painter = painterResource(id = content.image),
                    contentDescription = null)
            }
            Spacer(modifier = Modifier.height(basicMargin()))

            if (content.text != null){
                Text(text = content.text,
                    style = basicTextStyle(),
                    textAlign = TextAlign.Center,
                    color = basicTextColor())
            }
        }
    }
}