package cz.mendelu.pef.petstore.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Blue,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 0.dp, top = 8.dp, bottom = 0.dp, end = 0.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor,
            Color.White,
            disabledContentColor = Blue
        ),
        onClick = onClick,
        contentPadding = PaddingValues(
            start = 26.dp,
            top = 12.dp,
            end = 26.dp,
            bottom = 12.dp
        ),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(text = text)
    }
}