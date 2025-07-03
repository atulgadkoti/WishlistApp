package com.example.wishlistapp

import android.text.Layout.Alignment
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapp.data.DummyWish
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.ui.theme.Navigation
import com.example.wishlistapp.ui.theme.Screen
import com.example.wishlistapp.ui.theme.WishViewmodel
import kotlin.time.TestTimeSource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
             navController: NavController,
             viewModel: WishViewmodel
             ){

    Scaffold(modifier = Modifier.fillMaxSize().windowInsetsPadding(
        WindowInsets.systemBars),//isse phonee ke upr wale functions app ke sath collide
        // nhi krte

        topBar = {AppBarView(title = "WishList")},

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all=20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {// TODO add navigation to add screen
                     navController.navigate(Screen.AddScreen.route + "/0L")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
            }
        }

    ) { innerPadding ->
        //Navigation(modifier = Modifier.padding(innerPadding))
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(wishList.value,key = {wish -> wish.id }){
                    wish ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(state = dismissState,
                    background ={
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection
                                ==DismissDirection.EndToStart )Color.Red else Color.Transparent,
                            label = ""
                        )
                        val alignment = androidx.compose.ui.Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(Icons.Default.Delete, contentDescription = null ,
                                 tint = Color.White
                                )
                        }

                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {FractionalThreshold(0.5f)},
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }

                    }
                    )




            }
        }
    }

}

@Composable
fun WishItem(wish: Wish,onClick :  ()-> Unit){
     Card(modifier = Modifier.fillMaxSize()
         .padding(top=8.dp,start = 8.dp, end = 8.dp )
         .clickable {
             onClick()
         },
         elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
         colors = CardDefaults.cardColors(containerColor = Color.White )

     ) {
         Column(modifier = Modifier.padding(16.dp)) {
             Text(text=wish.title , fontWeight = FontWeight.ExtraBold, color = Color.Black)
             Text(text = wish.description, color = Color.DarkGray)
         }

     }

}