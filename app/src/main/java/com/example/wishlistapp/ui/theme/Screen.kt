package com.example.wishlistapp.ui.theme

//sealed class mtlb isme se koi bhi inherit nhi kr skta
sealed class Screen(val route:String) {
    object HomeScreen : Screen("home_screen")
    object AddScreen : Screen("add_screen")
}