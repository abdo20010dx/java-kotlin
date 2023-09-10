package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Lemonade()
                // A surface container using the 'background' color from the theme
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(){
    var result:Int by remember { mutableStateOf(1) }
    val imageSrc:Int
    val txtSrc:Int
    val contentSrc:Int
    when(result){
        1-> {
            imageSrc = R.drawable.lemon_tree
            contentSrc = R.string.lemon_tree_content_description
            txtSrc = R.string.lemon_select
        }
        2-> {
            imageSrc = R.drawable.lemon_squeeze
            contentSrc = R.string.lemon_content_description
            txtSrc = R.string.lemon_squeeze
        }
        3-> {
            imageSrc = R.drawable.lemon_drink
            contentSrc = R.string.lemon_content_description
            txtSrc = R.string.lemon_drink
        }
        4-> {
            imageSrc = R.drawable.lemon_restart
            contentSrc = R.string.empty_glass_content_description
            txtSrc = R.string.lemon_empty_glass
        }
        else -> {
            imageSrc = R.drawable.lemon_restart
            contentSrc = R.string.lemonade_content_description
            txtSrc = R.string.lemon_select
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        onClick = {
            result++
            if (result == 5) result = 1
        }
    ) {
    MakeLemonade(modifier = Modifier,imageSrc,contentSrc,txtSrc)
    }

}
@Composable
fun MakeLemonade(modifier: Modifier = Modifier,imageSrc:Int,contentSrc:Int,txtSrc:Int){
    Column(
        modifier = Modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageSrc) ,
            contentDescription = stringResource(id = contentSrc),
            contentScale = ContentScale.Crop,
        )
Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_vertical)))
        Text(
            text = stringResource(id = txtSrc),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
            )
    }


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemonade()
    }
}