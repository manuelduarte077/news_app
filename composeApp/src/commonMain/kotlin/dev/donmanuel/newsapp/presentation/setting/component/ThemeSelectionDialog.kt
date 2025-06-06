package dev.donmanuel.newsapp.presentation.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.donmanuel.newsapp.theme.getFontFamilyMedium
import dev.donmanuel.newsapp.theme.mediumPadding
import dev.donmanuel.newsapp.theme.xLargePadding
import dev.donmanuel.newsapp.theme.xSmallPadding
import dev.donmanuel.newsapp.utils.Theme
import news_kmp_app.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    onThemeChange: (Theme) -> Unit, onDismissRequest: () -> Unit, currentTheme: String
) {

    var currentSelectedTheme by remember { mutableStateOf(Theme.valueOf(currentTheme)) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest, content = {
            Surface(
                modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {
                Column(
                    modifier = Modifier.padding(mediumPadding)
                ) {
                    Text(
                        text = stringResource(Res.string.choose_a_theme),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(xSmallPadding),
                        fontFamily = getFontFamilyMedium()
                    )
                    Theme.entries.forEach { theme ->
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable {
                                currentSelectedTheme = theme
                            }, horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentSelectedTheme == theme,
                                onClick = { currentSelectedTheme = theme },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary
                                )
                            )
                            Text(
                                text = stringResource(theme.title),
                                fontFamily = getFontFamilyMedium()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(xLargePadding))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onDismissRequest) {
                            Text(
                                text = stringResource(Res.string.cancel),
                                fontFamily = getFontFamilyMedium()
                            )
                        }
                        Spacer(modifier = Modifier.width(mediumPadding))
                        TextButton(onClick = { onThemeChange(currentSelectedTheme) }) {
                            Text(
                                text = stringResource(Res.string.apply),
                                fontFamily = getFontFamilyMedium()
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun BookmarkDialog(
    onDeleteHistory: () -> Unit, onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                stringResource(Res.string.delete_bookmark),
                fontFamily = getFontFamilyMedium()
            )
        },
        text = {
            Text(
                stringResource(Res.string.delete_bookmark_description),
                fontFamily = getFontFamilyMedium()
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete, contentDescription = stringResource(Res.string.delete_bookmark)
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteHistory()
                }) {
                Text(
                    stringResource(Res.string.delete),
                    fontFamily = getFontFamilyMedium()
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    stringResource(Res.string.cancel),
                    fontFamily = getFontFamilyMedium()
                )
            }
        },
    )
}
