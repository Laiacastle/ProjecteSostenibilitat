package org.project.dietes.DietScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


class DietVM : ViewModel(){
    val listDiets = mutableStateOf<List<Diet>>(listOf())
    fun addDiet(){
        val tokenuserID = null
        if(tokenuserID != null){
            TODO()
        }
    }
    fun refreshDietList(){
        viewModelScope.launch(Dispatchers.Default) {
            listDiets.value = MyApi.listDiet()
        }
    }
    init{
        viewModelScope.launch(Dispatchers.Default) {
            listDiets.value = MyApi.listDiet()
        }
    }
}