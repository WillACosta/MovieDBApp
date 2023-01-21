package com.will.moviedbapp.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.will.moviedbapp.R

@Composable
fun WelcomeScreen() {
    WelcomeContent()
}

@Preview
@Composable
fun WelcomeContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.movie_bg),
                contentDescription = stringResource(id = R.string.movie_bg_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
            )

            Box(
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            0.1F to Color.Transparent,
                            .5F to Color.Black.copy(alpha = 0.5F),
                            1F to Color.Black
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 22.dp, end = 22.dp, bottom = 22.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.welcome_text),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.welcome_description),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.gray)
                    ),
                    textAlign = TextAlign.Center
                )

            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Text(text = "GET STARTED")
            }
        }
    }
}
