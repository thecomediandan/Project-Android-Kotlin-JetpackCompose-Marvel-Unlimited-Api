import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun TextFieldSearch(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    context: Context,
    suggestions: List<String>
)
{
    Surface(
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            var value by remember { mutableStateOf("") }
            var isSuggestionsVisible by remember { mutableStateOf(false) }

            val filteredSuggestions = remember {
                mutableStateListOf<String>()
            }

            //val localContext = LocalContext.current
            //val keyboardController = LocalSoftwareKeyboardController.current

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(56.dp),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                ),
                placeholder = { Text(stringResource(id = text)) },
                value = value,
                onValueChange = {
                    filteredSuggestions.clear()
                    if (it.isNotBlank() && (it.length > value.length )) {
                        val query = it.lowercase(Locale.getDefault())
                        suggestions.forEach { suggestion ->
                            if (suggestion.lowercase(Locale.getDefault()).contains(query)) {
                                filteredSuggestions.add(suggestion)
                            }
                        }
                    }
                    value = it
                    isSuggestionsVisible = filteredSuggestions.isNotEmpty()
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Log.e("onDone", value)
                        Toast.makeText(context, "Buscando $value", Toast.LENGTH_SHORT).show()
                        value = ""
                        //keyboardController?.hide()
                    },
                )
            )
            DropdownMenu(
                expanded = isSuggestionsVisible,
                onDismissRequest = { isSuggestionsVisible = false }
            ) {
                filteredSuggestions.forEach { suggestion ->
                    DropdownMenuItem(
                        text = {Text(text = suggestion)},
                        onClick = {
                            value = suggestion
                            isSuggestionsVisible = false
                            Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}