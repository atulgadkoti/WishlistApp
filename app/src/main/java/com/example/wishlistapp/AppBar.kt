package com.example.wishlistapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title : String,
    onBackNavClicked: ()-> Unit = {}
){
    val navigationIcon : (@Composable () -> Unit )? = {
        if(!title.contains("WishList")) {
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }else{
            null
        }
    }

    TopAppBar(title = {Text(text = title,color = colorResource(id = R.color.white),
              modifier = Modifier.padding(start =12.dp).heightIn(max=24.dp)
        )},
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.my_color),
        navigationIcon = navigationIcon
        )
}