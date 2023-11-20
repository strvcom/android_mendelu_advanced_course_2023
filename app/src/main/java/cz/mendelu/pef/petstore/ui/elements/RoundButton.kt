package cz.mendelu.pef.petstore.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(text: String,
                onClick: () -> Unit,
                modifier: Modifier = Modifier,
                backgroundColor: Color = Blue,
                ) {
    Button(
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
        modifier = modifier.fillMaxWidth()
            .padding(PaddingValues(start = 0.dp, top = 8.dp, bottom = 0.dp, end = 0.dp)),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(text = text)
    }
}