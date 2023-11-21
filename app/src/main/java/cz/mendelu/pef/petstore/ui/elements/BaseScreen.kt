@file:OptIn(ExperimentalMaterial3Api::class)

package cz.mendelu.pef.petstore.ui.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.compose.todo.ui.elements.PlaceHolderScreen
import cz.mendelu.pef.compose.todo.ui.elements.PlaceholderScreenContent
import cz.mendelu.pef.petstore.R
import cz.mendelu.pef.petstore.ui.theme.basicMargin
import cz.mendelu.pef.petstore.ui.theme.basicTextColor
import cz.mendelu.pef.petstore.ui.theme.getBackgroundColor
import cz.mendelu.pef.petstore.ui.theme.getTintColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(
    topBarText: String?,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    showSidePadding: Boolean = true,
    drawFullScreenContent: Boolean = false,
    placeholderScreenContent: PlaceholderScreenContent? = null,
    showLoading: Boolean = false,
    floatingActionButton: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {

    Scaffold(
        modifier = modifier,
        contentColor = getBackgroundColor(),
        containerColor = getBackgroundColor(),
        floatingActionButton = floatingActionButton,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        if (topBarText != null) {
                            Text(
                                text = topBarText,
                                style = MaterialTheme.typography.bodySmall,
                                color = basicTextColor(),
                                modifier = Modifier
                                    .padding(start = 0.dp)
                                    .weight(1.5f)
                            )
                        }
                    }
                },
                actions = actions,
                navigationIcon = {
                    if (onBackClick != null) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = getTintColor()
                            )
                        }
                    }
                }
            )
        }
    ) {
        if (placeholderScreenContent != null) {
            PlaceHolderScreen(
                modifier = Modifier.padding(it),
                content = placeholderScreenContent
            )
        } else if (showLoading) {
            LoadingScreen(modifier = Modifier.padding(it))
        } else {
            if (!drawFullScreenContent) {
                LazyColumn(modifier = Modifier.padding(it)) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .padding(if (!showSidePadding) basicMargin() else 0.dp)
                        ) {
                            content(it)
                        }
                    }
                }
            } else {
                content(it)
            }
        }
    }

}
