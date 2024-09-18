package uvg.edu.laboratorio6.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uvg.edu.laboratorio6.R

@Composable
fun RecipeScreen() {
    var selectedCategory by remember { mutableStateOf("ENTREES") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title and Icons at the top
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* Acción del menú lateral */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
            Text(text = "POPULAR RECIPES", style = MaterialTheme.typography.bodySmall)
            IconButton(onClick = { /* Acción de búsqueda */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(listOf("APPETIZERS", "ENTREES", "DESSERT")) { category ->
                Text(
                    text = category,
                    modifier = Modifier
                        .clickable { selectedCategory = category }
                        .padding(horizontal = 16.dp),
                    style = if (category == selectedCategory) {
                        MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary) // Adjust `colorScheme` if using Material 3
                    } else {
                        MaterialTheme.typography.labelSmall
                    }
                )
            }
        }


        // Recipe card
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Image
                Image(
                    painter = painterResource(R.drawable.pollo_asado), // Reemplazar con tu imagen
                    contentDescription = "Prime Rib Roast",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                // Title and rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Prime Rib Roast", style = MaterialTheme.typography.bodyLarge)
                    IconButton(onClick = { /* Acción del favorito */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
                    }
                }

                // Rating stars
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    repeat(4) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Empty Star",
                        tint = Color.Gray
                    )
                }

                // Time, likes, and comments
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Time")
                        Text(text = "5HR", modifier = Modifier.padding(start = 4.dp))
                    }
                    Row {
                        Icon(Icons.Default.ThumbUp, contentDescription = "Likes")
                        Text(text = "685", modifier = Modifier.padding(start = 4.dp))
                    }
                    Row {
                        Icon(Icons.Default.AccountBox, contentDescription = "Comments")
                        Text(text = "107", modifier = Modifier.padding(start = 4.dp))
                    }
                }

                // Description
                Text(
                    text = "The Prime Rib Roast is a classic and tender cut of beef...",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
