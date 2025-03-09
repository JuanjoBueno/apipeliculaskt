# TMDB Movies App

TMDB Movies App es una aplicación desarrollada en Kotlin utilizando Jetpack Compose y Retrofit para consumir la API de The Movie Database (TMDB). La aplicación permite visualizar películas populares y en cartelera.

## Características principales
- **Consumo de API REST:** Uso de Retrofit para obtener datos de TMDB.
- **Interfaz moderna:** Implementada con Jetpack Compose para una experiencia fluida.
- **Optimización para distintos dispositivos:** Diseño adaptable a orientación vertical y horizontal.
- **Definición clara de componentes:** Implementación de ejemplos funcionales en Jetpack Compose.
- **Gestión eficiente de estados:** Uso correcto de StateFlow y LiveData en la UI.


### ✔️ **Uso adecuado de Retrofit en Jetpack Compose**
- Se usa `viewModel()` para manejar los datos y estados sin perder información en cambios de configuración.
- Se implementa `LazyColumn` y `LazyRow` para mostrar listas de películas de forma eficiente en retrato y paisaje.
- Se usa `StateFlow` y `collectAsState()` para actualizar la UI en tiempo real sin afectar el rendimiento.

## Definición de Componentes en Jetpack Compose

### ✔️ **Ejemplo funcional de componentes**
- Se crean `Composable` bien definidos para cada elemento de la interfaz.
- Uso de `Card`, `Image`, `Text` y `LazyColumn/LazyRow` para una estructura clara y modular.
- Separación de responsabilidades en diferentes funciones para mejorar la reutilización de código.

#### 🔹 **Ejemplo de un Composable bien estructurado**
```kotlin
@Composable
fun MovieItem(movie: Result) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
                contentDescription = "Poster de la película",
                modifier = Modifier.height(280.dp).fillMaxWidth().clip(RoundedCornerShape(12.dp))
            )
            Text(text = movie.title, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        }
    }
}
```

## Gestión de Estados en Jetpack Compose

### ✔️ **Implementación correcta de gestión de estados**
- Se usa `MutableStateFlow` dentro de `ViewModel` para manejar el estado de las películas.
- `StateFlow` se observa mediante `collectAsState()`, asegurando una actualización reactiva de la UI.
- Se mantiene la reactividad sin necesidad de recargar la pantalla manualmente.

#### 🔹 **Ejemplo de gestión de estados en ViewModel**
```kotlin
class MoviesViewModel : ViewModel() {
    private val service = RetrofitServiceFactory.makeRetrofitService()
    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            val movies = service.listNowPlayingMovies("API_KEY", "1")
            _movies.value = movies.results
        }
    }
}
```


## Tecnologías utilizadas
- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Consumo de API:** Retrofit + Gson
- **Gestión de estado:** StateFlow + ViewModel
- **Carga de imágenes:** Coil

## Objetivos no alcanzados

### 4. Uso de Biblioteca Externa para Gráficas
-  Integración de una biblioteca externa para la representación de gráficas.
-  Implementación funcional y adaptada a Jetpack Compose.

### 5. Creación de un Componente de Gráficas sin Biblioteca Externa
-  Desarrollo de un componente que represente gráficas sin depender de librerías externas.
-  Correcto uso de Canvas y otros elementos nativos de Jetpack Compose.

### 6. Comunicación con un Servidor mediante WebSockets
-  Implementación de un sistema funcional de comunicación en tiempo real.
-  Manejo adecuado de eventos y estados en la comunicación con WebSockets.

### 7. Uso de VerticalPager o Similar
- Implementación de VerticalPager o una alternativa adecuada.
- Correcta navegación y adaptación a la interfaz de usuario.



