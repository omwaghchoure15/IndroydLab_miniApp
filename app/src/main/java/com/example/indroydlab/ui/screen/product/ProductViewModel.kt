package com.example.indroydlab.ui.screen.product

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.indroydlab.data.ProductRepository
import com.example.indroydlab.model.ProductModel

class ProductViewModel() : ViewModel() {
    private val _products = mutableStateListOf<ProductModel>()
    val products: List<ProductModel> = _products

    init { loadProducts() }
    private fun loadProducts(){
        _products.clear()
        _products.addAll(ProductRepository.getProducts())
    }

    init {
        println("ProductViewModel: init")
    }
    override fun onCleared() {
        super.onCleared()
        println("ProductViewModel: Cleared")
    }
}