package uvg.edu.laboratorio6.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uvg.edu.laboratorio6.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    drawerState: DrawerState,
    onMenuClick: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Contenedor completo del menú con fondo rojo
    ModalDrawerSheet(
        modifier = Modifier
            .background(Color(0xFFEB5757))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFFEB5757))
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // El perfil queda en la parte inferior
        ) {

            // Opciones del menú
            Column {
                NavigationDrawerItem(
                    label = { Text("POPULAR RECIPES", color = Color.White, fontSize = 20.sp) },
                    selected = true,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onMenuClick("Principal")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Separador
                NavigationDrawerItem(
                    label = { Text("SAVED RECIPES", color = Color.White, fontSize = 20.sp) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onMenuClick("Saved recipes")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Separador
                NavigationDrawerItem(
                    label = { Text("SHOPPING LIST", color = Color.White, fontSize = 20.sp) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onMenuClick("Shopping list")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Separador
                NavigationDrawerItem(
                    label = { Text("SETTINGS", color = Color.White, fontSize = 20.sp) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        onMenuClick("Settings")
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
            }

            // Contenido del perfil en la parte inferior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.aimep3), // Cambia la imagen al recurso correcto
                    contentDescription = "My Profile",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(50.dp) // Tamaño ajustado para el ícono del perfil
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "MARISOL DOMÍNGUEZ",
                    color = Color.White, // Texto en blanco
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Configuración del drawer con el contenido
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(drawerState = drawerState) { route ->
                // Manejar las rutas de navegación según el ítem seleccionado
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_three_stripes), // Ícono del menú
                    contentDescription = "Abrir menú",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
