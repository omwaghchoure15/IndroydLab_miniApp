package com.example.indroydlab.ui.screen.catalog

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.indroydlab.data.ProductRepository
import com.example.indroydlab.model.ProductModel

class ProductDetailViewModel(val productId: String) : ViewModel() {
    private val _products = mutableStateListOf<ProductModel>()
    val products: List<ProductModel> = _products

    init { loadProducts() }
    private fun loadProducts(){
        _products.clear()
        _products.addAll(ProductRepository.getProducts())
    }

    /**
     * Finds a product by its ID or Name.
     * This supports deep links that use either numeric IDs or descriptive names.
     */
    fun getProductById(identifier: String): ProductModel?{
        return _products.find { 
            it.id.toString() == identifier || it.name.equals(identifier, ignoreCase = true) 
        }
    }


    init {
        println("ProductViewModel: init")
    }
    override fun onCleared() {
        super.onCleared()
        println("ProductViewModel: Cleared")
    }
}