package com.example.bookapplication.ui.details.components

import android.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

@Composable
fun CollapsibleText(
    modifier: Modifier = Modifier,
    maxLines: Int =  3,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
){
    var isExpanded by remember { mutableStateOf(false) }

    val annotatedString = buildAnnotatedString {
        if (!isExpanded){
            withStyle(textStyle.toSpanStyle()){
                append("Read More")
            }
        }else{
            withStyle(textStyle.toSpanStyle()){
                append("Read Less")
            }
        }
    }
    Text(
        text = text,
        modifier = modifier,
        maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
        overflow = TextOverflow.Ellipsis,
        style = textStyle
    )
    Text(
        text = annotatedString,
        modifier = modifier,
        maxLines = if (isExpanded) Int.MAX_VALUE else maxLines,
        overflow = TextOverflow.Ellipsis,
        style = textStyle
    )
}