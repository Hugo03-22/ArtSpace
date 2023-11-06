package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace() {
    var artWorkIndex by remember { mutableStateOf(0) }
    if (artWorkIndex == 6) artWorkIndex = 0
    if (artWorkIndex == -1) artWorkIndex = 3

    val artWork: Int
    val artWorkName: Int

    when (artWorkIndex) {
        0 -> {
            artWork = R.drawable.giga
            artWorkName = R.string.giga
        }
        1 -> {
            artWork = R.drawable.rex
            artWorkName = R.string.rex
        }
        2 -> {
            artWork = R.drawable.parasaur
            artWorkName = R.string.para
        }
        3 -> {
            artWork = R.drawable.trike
            artWorkName = R.string.trike
        }
        4 -> {
            artWork = R.drawable.stego
            artWorkName = R.string.stego
        }

        else -> {
            artWork = R.drawable.pachirhinosaur
            artWorkName = R.string.pachi
        }
    }
    Column () {
        Image (
            painter = painterResource(id = artWork),
            contentDescription = stringResource(id = artWorkName),
            modifier = Modifier.padding(30.dp).fillMaxWidth()
        )
    }



    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(colorResource(id = R.color.black), fontWeight = FontWeight.Light, fontSize = 24.sp)
            ) {
                append(stringResource(id = artWorkName))
            }
        },
    )
    Spacer(Modifier.height(28.dp))

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        EditButtons(
            onClick = { artWorkIndex-- },
            buttonColor = R.color.purple_200,
            buttonText = R.string.previous_button,
        )

        EditButtons(
            onClick = { artWorkIndex++ },
            buttonColor = R.color.purple_200,
            buttonText = R.string.next_button,
        )
    }
}

@Composable
fun EditButtons(onClick: () -> Unit, @StringRes buttonText: Int, @ColorRes buttonColor: Int, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(colorResource(id = buttonColor)),
        modifier = modifier.width(140.dp),
    ) {
        Text(text = stringResource(id = buttonText))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}