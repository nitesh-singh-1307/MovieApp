package com.example.bookapplication.ui.details.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bookapplication.movie_detail.domain.models.Review
import com.example.bookapplication.ui.home.itemSpacing
import kotlin.math.round

@Composable
fun ReviewItem(
    review: Review,
    modifier: Modifier = Modifier
){

    Column(
        modifier
    ) {
        val nameAnnotatedString = buildAnnotatedString {
            append(review.author)
            append(" . ")
            append(review.createdAt)
        }

        val ratingAnnotatedString = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append(round(review.rating).toString())
            pop()
            // Apply small font size to "10"
            pushStyle(SpanStyle(fontSize = 10.sp))
            append("10")
            pop()
        }
        Text(
            text = nameAnnotatedString,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        CollapsibleText(maxLines = 3, text = review.content , textStyle = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(itemSpacing))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Star Icon")
            Text(
                text = ratingAnnotatedString,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold)


        }

    }
}