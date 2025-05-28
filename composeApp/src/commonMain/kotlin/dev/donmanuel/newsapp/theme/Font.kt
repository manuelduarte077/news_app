package dev.donmanuel.newsapp.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import news_kmp_app.composeapp.generated.resources.Res
import news_kmp_app.composeapp.generated.resources.grotesk_bold
import news_kmp_app.composeapp.generated.resources.grotesk_light
import news_kmp_app.composeapp.generated.resources.grotesk_medium
import news_kmp_app.composeapp.generated.resources.grotesk_regular
import news_kmp_app.composeapp.generated.resources.grotesk_semiBold
import org.jetbrains.compose.resources.Font

@Composable
fun getFontFamily() = FontFamily(Font(Res.font.grotesk_regular))

@Composable
fun getFontFamilyBold() = FontFamily(Font(Res.font.grotesk_bold))

@Composable
fun getFontFamilySemiBold() = FontFamily(Font(Res.font.grotesk_semiBold))

@Composable
fun getFontFamilyLight() = FontFamily(Font(Res.font.grotesk_light))

@Composable
fun getFontFamilyMedium() = FontFamily(Font(Res.font.grotesk_medium))



