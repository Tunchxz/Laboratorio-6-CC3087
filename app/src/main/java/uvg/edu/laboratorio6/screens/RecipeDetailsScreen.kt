package uvg.edu.laboratorio6.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uvg.edu.laboratorio6.R
import uvg.edu.laboratorio6.ui.theme.Laboratorio6Theme

@Composable
fun CrearImagen(
    @DrawableRes drawable: Int,
    modifier: Modifier,
    contentScale: Boolean
) {
    Image(
        painter = painterResource(drawable),
        contentDescription = null,
        contentScale = if (contentScale) ContentScale.Crop else ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
fun RecipeDetailScreen(
    @DrawableRes recipeImage: Int,
    @StringRes recipeName: Int,
    @StringRes recipeDescription: Int,
    @StringRes recipeShopping: Int,
    @StringRes recipePrep: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CrearImagen(
            drawable = recipeImage,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            true
        )
        CrearImagen(
            drawable = R.drawable.estrellas,
            modifier = Modifier
                .padding(top = 15.dp, bottom = 25.dp)
                .height(35.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = false
        )
        Text(
            text = stringResource(recipeName),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color(0xFF19597D),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 25.dp)
        )
        Text(
            text = stringResource(recipeDescription),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 25.sp,
                color = Color(0xFF757575),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 25.dp)
        )
        //---------- Apartado de Lista de Compras ----------
        CrearImagen(
            drawable = R.drawable.compras,
            modifier = Modifier
                .padding(top = 25.dp, bottom = 10.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = false
        )
        Text(
            text = "Lista de Compras",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color(0xFF19597D),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 25.dp)
        )
        Text(
            text = stringResource(recipeShopping),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 30.sp,
                color = Color(0xFF757575),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 25.dp)
        )
        //---------- Apartado de Preparación ----------
        CrearImagen(
            drawable = R.drawable.preparacion,
            modifier = Modifier
                .padding(top = 35.dp, bottom = 10.dp)
                .size(60.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = false
        )
        Text(
            text = "Preparación",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color(0xFF19597D),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 25.dp)
        )
        Text(
            text = stringResource(recipePrep),
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 25.sp,
                color = Color(0xFF757575),
                textAlign = TextAlign.Justify
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 25.dp, end = 25.dp, bottom = 80.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
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