package uvg.edu.laboratorio6.screens

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
fun SplashScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Contenido superpuesto (imagen y texto)
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.kitchen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEB5757))     // Fondo con color rojo
                .graphicsLayer {                        // Aplicar filtro de color rojo con opacidad
                    alpha = 0.1f                        // Controlar la intensidad del color
                    shadowElevation = 0f
                }
        )
        // Columna con el Logo y Nombre de la App
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.sombrero_chef),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp)
            )
            Text(
                text = "Chef\nRecipes",
                style = TextStyle(
                    fontSize = 32.sp,               // Tamaño del texto
                    color = Color.White,            // Color del texto
                    fontWeight = FontWeight.Bold,   // Peso de la fuente
                    fontStyle = FontStyle.Italic,   // Estilo de fuente
                    textAlign = TextAlign.Center    // Alineación del texto
                )
            )
        }
    }
}

@Preview(widthDp = 300, heightDp = 600)
@Composable
fun SplashScreenPreview() {
    Laboratorio6Theme {
        SplashScreen()
    }
}