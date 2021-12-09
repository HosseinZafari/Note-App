package com.hosseinzafari.github.framework.todoapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hosseinzafari.github.framework.todoapp.R

// font family
val fonts = FontFamily(
    Font(
        resId = R.font.iran_light ,
        weight = FontWeight.Light ,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.iran_md ,
        weight = FontWeight.Medium ,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.iran_bold ,
        weight = FontWeight.Bold ,
        style = FontStyle.Normal
    ),
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ) ,
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)