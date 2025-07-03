package com.example.wishlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao : WishDao) {

    suspend fun addWish(wish : Wish){
        wishDao.addWish(wish)
    }

    fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()  //fun getWishes(): Flow<List<Wish>> {
                                                                //return wishDao.getAllWishes()
                                                                //} both are same
    fun getWishById(id:Long):Flow<Wish>{
        return wishDao.getAWishById(id)
    }
    suspend fun updateAWish(wish: Wish){
        wishDao.updateWish(wish)
    }
    suspend fun deleteAWish(wish : Wish){
        wishDao.deleteAWish(wish)
    }


}