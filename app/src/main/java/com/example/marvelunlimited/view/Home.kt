import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marvelunlimited.R
import com.example.marvelunlimited.model.Comic
import com.example.marvelunlimited.model.ComicUsesCases
import com.example.marvelunlimited.view.ComicViewModel
import com.example.marvelunlimited.view.components.ItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Home(
    comicViewModel: ComicViewModel,
    comicUsesCases: ComicUsesCases,
    context: Context,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier ) {

    //val cont: MutableState<Int> = remember { mutableIntStateOf(0) } // Mode Standard
    //val cont = remember { mutableIntStateOf(0) } // No survive to resize of screen
    //val cont = rememberSaveable { mutableIntStateOf(0) }

    lateinit var listComics: List<Comic>
    val suggestList: MutableState<List<String>> = rememberSaveable { mutableStateOf(emptyList()) }
    val isLoading = comicViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        try {
            val comics = comicUsesCases.getListComics()
            withContext(Dispatchers.Main){}
            listComics = comics
            Log.e("Result", comics.toString())
            comicViewModel.stopLoading()
            suggestList.value = listComics.map{it.title}.subList(0, 5)
        }catch (e: Exception){
            Log.e("Error de respuesta", e.message.toString())
        }
    }

    Scaffold {
        Box {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(modifier.padding(15.dp)) {
                TextFieldSearch(text = text, context = context,
                    suggestions = suggestList.value, modifier = Modifier.fillMaxWidth())
                if (isLoading.value) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                        items(listComics) {item ->
                            ItemList(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                                    .height(height = 170.dp)
                                    .clip(shape = MaterialTheme.shapes.medium)
                                    .background(color = colorResource(id = R.color.item_background_color)),
                                title = item.title,
                                //description = item.textobjects?.get(0)?.text ?: "Sin descripcion",
                                imageUrl = item.thumbnail.path + "." + item.thumbnail.extension,
                                price = item.prices?.get(0)?.price ?: "0",
                                creators = item.creators.items.joinToString {
                                    it.name
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}

