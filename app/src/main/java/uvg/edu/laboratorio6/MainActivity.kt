package uvg.edu.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uvg.edu.laboratorio6.screens.RecipeDetailScreen
import uvg.edu.laboratorio6.ui.theme.Laboratorio6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio6Theme {
                RecipeDetailScreen(
                    R.drawable.pastel_de_chocolate,
                    R.string.receta_1,
                    R.string.descripcion_1,
                    R.string.lista_de_compras_1,
                    R.string.preparacion_1
                )
            }
        }
    }
}