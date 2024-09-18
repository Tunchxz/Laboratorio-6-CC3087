package uvg.edu.laboratorio6.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@Composable
fun DrawerContent(
    drawerState: DrawerState, // Estado del menú lateral para manejar su apertura y cierre
    onMenuClick: (String) -> Unit // Callback que maneja la selección del menú
) {
    // Corutina para manejar las acciones asíncronas
    val coroutineScope = rememberCoroutineScope()

    // Contenedor del menú lateral con fondo personalizado
    ModalDrawerSheet(
        modifier = Modifier
            .background(Color(0xFFEB5757)) // Color de fondo del menú
    ) {
        // Columna que organiza los elementos del menú
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFFEB5757)) // Color de fondo del menú
                .padding(16.dp), // Espaciado interno del menú
            verticalArrangement = Arrangement.SpaceBetween // Distribución vertical de los elementos
        ) {
            // Columna para los elementos de navegación
            Column {
                // Elemento del menú para "RECETAS POPULARES"
                NavigationDrawerItem(
                    label = { Text("POPULAR RECIPES", color = Color.White, fontSize = 20.sp) },
                    selected = true, // Indica que este ítem está seleccionado
                    onClick = {
                        coroutineScope.launch { drawerState.close() } // Cierra el menú al hacer clic
                        onMenuClick("Principal") // Llama al callback con la opción seleccionada
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre los ítems
                // Elemento del menú para "RECETAS GUARDADAS"
                NavigationDrawerItem(
                    label = { Text("SAVED RECIPES", color = Color.White, fontSize = 20.sp) },
                    selected = false, // Indica que este ítem no está seleccionado
                    onClick = {
                        coroutineScope.launch { drawerState.close() } // Cierra el menú al hacer clic
                        onMenuClick("Saved recipes") // Llama al callback con la opción seleccionada
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre los ítems
                // Elemento del menú para "LISTA DE COMPRAS"
                NavigationDrawerItem(
                    label = { Text("SHOPPING LIST", color = Color.White, fontSize = 20.sp) },
                    selected = false, // Indica que este ítem no está seleccionado
                    onClick = {
                        coroutineScope.launch { drawerState.close() } // Cierra el menú al hacer clic
                        onMenuClick("Shopping list") // Llama al callback con la opción seleccionada
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre los ítems
                // Elemento del menú para "CONFIGURACIONES"
                NavigationDrawerItem(
                    label = { Text("SETTINGS", color = Color.White, fontSize = 20.sp) },
                    selected = false, // Indica que este ítem no está seleccionado
                    onClick = {
                        coroutineScope.launch { drawerState.close() } // Cierra el menú al hacer clic
                        onMenuClick("Settings") // Llama al callback con la opción seleccionada
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color.Transparent,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
            }

            // Columna para mostrar el perfil del usuario
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Ocupar el ancho completo
                    .padding(16.dp), // Espaciado interno del perfil
                horizontalAlignment = Alignment.Start // Alineación horizontal de los elementos
            ) {
                // Icono del perfil del usuario
                Icon(
                    painter = painterResource(id = R.drawable.aimep3), // Recurso de la imagen del perfil
                    contentDescription = "My Profile", // Descripción para accesibilidad
                    tint = Color.Unspecified, // Color del icono no especificado
                    modifier = Modifier.size(50.dp) // Tamaño del icono
                )
                Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el icono y el texto
                // Nombre del usuario
                Text(
                    text = "MARISOL DOMÍNGUEZ", // Nombre a mostrar
                    color = Color.White, // Color del texto
                    fontSize = 20.sp, // Tamaño de la fuente
                    textAlign = TextAlign.Left // Alineación del texto
                )
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Estado del menú lateral, inicializado como cerrado
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope() // Corutina para manejar acciones asíncronas

    // Navegación modal con el estado del menú
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(drawerState = drawerState) {
                // Callback vacío para manejar clics en el menú
            }
        }
    ) {
        // Columna para organizar los elementos de la pantalla principal
        Column(modifier = Modifier.padding(16.dp)) {
            // Botón para abrir el menú lateral
            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_three_stripes),
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
