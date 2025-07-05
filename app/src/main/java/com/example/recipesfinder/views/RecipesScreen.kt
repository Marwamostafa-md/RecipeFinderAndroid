import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.CachePolicy
import com.example.recipesfinder.R
import com.example.recipesfinder.uirecipesmodels.Recipe
import com.example.recipesfinder.viewmodels.RecipeViewModel.RecipesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = hiltViewModel(),
    isCategory: Boolean = false,
    typeOrCountry: String? = null,
    category: String = "",
    onRecipeClick: (Int) -> Unit?
) {
    val state by viewModel.recipeState.collectAsState()

    LaunchedEffect(Unit) {
        if (state?.recipes.isNullOrEmpty()) {
            viewModel.getRecipes(isCategory, typeOrCountry, category)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(typeOrCountry ?: "All Recipes") }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                state?.isLoading() == true -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state?.isSuccess() == true -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        state?.recipes?.filterNotNull()?.let { recipes ->
                            items(recipes) { recipe ->
                                RecipeCard(recipe = recipe, onClick = { onRecipeClick(recipe.id) })
                            }
                        }
                    }
                }

                state?.isError() == true -> {
                    Text(
                        text = "Error: ${state?.error?.localizedMessage ?: "Unknown error"}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    Text(
                        text = "No recipes found",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(recipe.image)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .placeholder(R.drawable.recipe)
        .error(R.drawable.recipe)
        .build()

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column {
            AsyncImage(
                model = imageRequest,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            )

            Text(
                text = recipe.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}