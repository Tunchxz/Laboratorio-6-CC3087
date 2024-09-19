package uvg.edu.laboratorio6.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import uvg.edu.laboratorio6.R
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController

// Datos de ejemplo para las recetas
data class Recipe(
    val name: Int,
    val image: Int,
    val time: String,
    val likes: Int,
    val comments: Int,
    val description: Int,
    val shopping: Int,
    val preparation: Int
)

val recipesList = listOf(
    Recipe(R.string.receta_1, R.drawable.pastel_de_chocolate, "5HR", 65, 10, R.string.descripcion_1, R.string.lista_de_compras_1, R.string.preparacion_1),
    Recipe(R.string.receta_2, R.drawable.ensalada_cesar, "30MIN", 50, 8, R.string.descripcion_2, R.string.lista_de_compras_2, R.string.preparacion_2),
    Recipe(R.string.receta_3, R.drawable.pizza_margarita, "1HR", 90, 15, R.string.descripcion_3, R.string.lista_de_compras_3, R.string.preparacion_3),
    Recipe(R.string.receta_4, R.drawable.sopa_de_tomate, "45MIN", 40, 5, R.string.descripcion_4, R.string.lista_de_compras_4, R.string.preparacion_4),
    Recipe(R.string.receta_5, R.drawable.tacos_al_pastor, "2HR", 120, 20, R.string.descripcion_5, R.string.lista_de_compras_5, R.string.preparacion_5),
    Recipe(R.string.receta_6, R.drawable.brownies, "1HR", 70, 12, R.string.descripcion_6, R.string.lista_de_compras_6, R.string.preparacion_6)
)

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf("POSTRES") }
    var isFavorite by remember { mutableStateOf(false) }

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

            // Selector de categorías
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
                            MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
                        } else {
                            MaterialTheme.typography.labelMedium
                        }
                    )
                }
            }

            // LazyRow con tarjetas completas
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recipesList) { recipe ->
                    RecipeCard(recipe, isFavorite, onFavoriteClick = { isFavorite = !isFavorite }) {
                        // Navegar a la pantalla de detalles cuando se hace clic en la imagen
                        navController.navigate(
                            "recipeDetail/${recipe.image}/${recipe.name}/${recipe.description}/${recipe.shopping}/${recipe.preparation}"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, isFavorite: Boolean, onFavoriteClick: () -> Unit, onImageClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(300.dp) // Establece un ancho fijo para las tarjetas
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Imagen de la receta
            Image(
                painter = painterResource(recipe.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onImageClick() } // Ejecutar la acción cuando se hace clic
            )

            // Rating con estrellas
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
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

            // Título y botón de favorito
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(recipe.name), style = MaterialTheme.typography.titleLarge)
                IconButton(onClick = onFavoriteClick) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            // Información adicional: tiempo, likes, comentarios
            Row(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Time")
                    Text(text = recipe.time, modifier = Modifier.padding(start = 4.dp))
                }
                Row {
                    Icon(Icons.Default.ThumbUp, contentDescription = "Likes")
                    Text(text = recipe.likes.toString(), modifier = Modifier.padding(start = 4.dp))
                }
                Row {
                    Icon(Icons.Default.AccountBox, contentDescription = "Comments")
                    Text(text = recipe.comments.toString(), modifier = Modifier.padding(start = 4.dp))
                }
            }

            // Descripción de la receta
            Text(
                text = stringResource(recipe.description),
                modifier = Modifier.padding(vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}