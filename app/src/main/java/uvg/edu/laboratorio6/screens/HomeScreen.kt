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
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uvg.edu.laboratorio6.R
import uvg.edu.laboratorio6.ui.theme.Laboratorio6Theme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableStateOf("POSTRES") }
    var isFavorite by remember { mutableStateOf(false) } // Estado para controlar si está marcado como favorito

    // Lógica de menú lateral
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(drawerState = drawerState) { route -> }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra superior con íconos y título
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
                Text(text = "RECETAS POPULARES", style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = { /* Acción de búsqueda */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }

            LazyRow(
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(listOf("APERITIVOS", "POSTRES", "ENTRADAS")) { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 16.dp),
                        style = if (category == selectedCategory) {
                            MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                        } else {
                            MaterialTheme.typography.labelSmall
                        }
                    )
                }
            }

            // Tarjeta de receta
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pastel_de_chocolate),
                        contentDescription = "Pastel de Chocolate",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Pastel de Chocolate", style = MaterialTheme.typography.titleLarge)

                        IconButton(onClick = { isFavorite = !isFavorite }) { // Cambiar el estado al hacer clic
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) Color.Red else Color.Gray // Cambiar el color según el estado
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(top = 4.dp).align(Alignment.CenterHorizontally)
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

                    Row(
                        modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Time")
                            Text(text = "5HR", modifier = Modifier.padding(start = 4.dp))
                        }
                        Row {
                            Icon(Icons.Default.ThumbUp, contentDescription = "Likes")
                            Text(text = "65", modifier = Modifier.padding(start = 4.dp))
                        }
                        Row {
                            Icon(Icons.Default.AccountBox, contentDescription = "Comments")
                            Text(text = "10", modifier = Modifier.padding(start = 4.dp))
                        }
                    }

                    Text(
                        text = "El pastel de chocolate es un postre delicioso para compartir en familia.",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Laboratorio6Theme {
        HomeScreen()
    }
}