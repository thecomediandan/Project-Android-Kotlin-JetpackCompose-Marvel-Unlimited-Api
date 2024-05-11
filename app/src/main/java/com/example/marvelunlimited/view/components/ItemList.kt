package com.example.marvelunlimited.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

//import com.example.marvelunlimited.model.Creator
//title: String, description: String, imageUrl: String, price: String, creators: List<Any>
@Composable
fun ItemList(modifier: Modifier = Modifier, title: String, price: String, imageUrl: String, creators: String) {
    Surface(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(modifier = Modifier
                .padding(8.dp)
                .size(width = 100.dp, height = 170.dp)
                .clip(shape = MaterialTheme.shapes.medium), model = imageUrl, contentDescription = null, contentScale = ContentScale.FillHeight)
            Column(modifier = Modifier
                .fillMaxSize()
                //.fillMaxHeight()
                //.width(width = 200.dp))
            ){
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), text = title, textAlign = TextAlign.Center,
                    //fontSize = TextUnit(value = 4f, type = TextUnitType.Em),
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif,
                    maxLines = 2)

                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

                    Column(modifier = Modifier.fillMaxWidth().height(height = 70.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "\$us", fontSize = TextUnit(value = 4f, type = TextUnitType.Em), fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                        Text(text = price, fontSize = TextUnit(value = 4f, type = TextUnitType.Em), fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                    }

                    //Text(text = description, overflow = TextOverflow.Ellipsis, maxLines = 6)
                    Text(modifier = Modifier.padding(8.dp), text = creators, maxLines = 2, overflow = TextOverflow.Ellipsis, fontStyle = FontStyle.Italic, color = Color.Magenta)
                }

            }
            /*
            Box(modifier = Modifier.size(width = 60.dp, height = 100.dp)){
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Sus", fontSize = TextUnit(value = 5f, type = TextUnitType.Em), fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                    Text(text = price, fontSize = TextUnit(value = 4f, type = TextUnitType.Em), fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                }
            }
            */

        }
    }
}