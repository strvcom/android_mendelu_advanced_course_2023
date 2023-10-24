package com.strv.mendelu2023.pushnotifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.strv.mendelu2023.pushnotifications.ui.theme.PushNotificationsTheme

@Composable
fun MainScreen(
    notificationEnabled: Boolean,
    onAllowNotification: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        if (!notificationEnabled) {
            Button(
                onClick = onAllowNotification,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.allow_notifications_button))
            }
        } else {
            Text(
                text = stringResource(R.string.notifications_allowed_description),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview(
    @PreviewParameter(NotificationEnabledPreviewParameterProvider::class) enabled: Boolean,
) {
    PushNotificationsTheme {
        Surface {
            MainScreen(notificationEnabled = enabled, onAllowNotification = {})
        }
    }
}

class NotificationEnabledPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}
