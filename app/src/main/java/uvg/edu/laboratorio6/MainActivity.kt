package uvg.edu.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uvg.edu.laboratorio6.screens.RecipeDetailScreen
import uvg.edu.laboratorio6.ui.theme.Laboratorio6Theme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uvg.edu.laboratorio6.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio6Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home" // Pantalla inicial
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        // Agregamos los argumentos que esperamos recibir
        composable(
            route = "recipeDetail/{recipeImage}/{recipeName}/{recipeDescription}/{recipeShopping}/{recipePrep}",
            arguments = listOf(
                navArgument("recipeImage") { type = NavType.IntType },
                navArgument("recipeName") { type = NavType.IntType },
                navArgument("recipeDescription") { type = NavType.IntType },
                navArgument("recipeShopping") { type = NavType.IntType },
                navArgument("recipePrep") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val recipeImage = backStackEntry.arguments?.getInt("recipeImage") ?: 0
            val recipeName = backStackEntry.arguments?.getInt("recipeName") ?: 0
            val recipeDescription = backStackEntry.arguments?.getInt("recipeDescription") ?: 0
            val recipeShopping = backStackEntry.arguments?.getInt("recipeShopping") ?: 0
            val recipePrep = backStackEntry.arguments?.getInt("recipePrep") ?: 0

            RecipeDetailScreen(
                recipeImage = recipeImage,
                recipeName = recipeName,
                recipeDescription = recipeDescription,
                recipeShopping = recipeShopping,
                recipePrep = recipePrep
            )
        }
    }
}