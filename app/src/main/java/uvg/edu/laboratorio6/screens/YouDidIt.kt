package uvg.edu.laboratorio6.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.edu.laboratorio6.R
import uvg.edu.laboratorio6.ui.theme.Laboratorio6Theme

@Composable
fun YouDidIt(
    onDismissRequest: () -> Unit,
    onShareClicked: (String) -> Unit,
    onRatingChanged: (Int) -> Unit,
    currentRating: Int = 0
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable(onClick = onDismissRequest)
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "YOU DID IT!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Let your friends know about it",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { onShareClicked("GooglePlus") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.gppicon),
                            contentDescription = "Google Plus",
                            tint = Color.Unspecified
                        )
                    }
                    IconButton(onClick = { onShareClicked("Facebook") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.fbicon),
                            contentDescription = "Facebook",
                            tint = Color.Unspecified
                        )
                    }
                    IconButton(onClick = { onShareClicked("Twitter") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.twittericon),
                            contentDescription = "Twitter",
                            tint = Color.Unspecified
                        )
                    }
                    IconButton(onClick = { onShareClicked("Instagram") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.igicon),
                            contentDescription = "Instagram",
                            tint = Color.Unspecified
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Leave a review",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating Star",
                            tint = if (i <= currentRating) Color.Yellow else Color.Gray,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { onRatingChanged(i) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YouDidItPreview() {
    Laboratorio6Theme {
        YouDidIt(
            onDismissRequest = {},
            onShareClicked = { platform -> println("Shared on $platform") },
            onRatingChanged = { rating -> println("Rating changed to $rating") },
            currentRating = 2
        )
    }
}
