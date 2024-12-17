package com.bangkit.skincareku.view.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.skincareku.networking.response.Article
import com.bangkit.skincareku.networking.response.Product

class DashboardViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError
    private val _productRecommendationList = MutableLiveData<ArrayList<Product>>()
    val productRecommendationList: LiveData<ArrayList<Product>> = _productRecommendationList
    private val _articleList = MutableLiveData<ArrayList<Article>>()
    val articleList: LiveData<ArrayList<Article>> = _articleList

    fun getProductRecommendation () {
        _isLoading.value = true
        _isError.value = false
        val productRecommendation = ArrayList<Product>()
        productRecommendation.add(
            Product(
                "Product 1",
                "Description 1",
                10
            )
        )
        productRecommendation.add(
            Product(
                "Product 2",
                "Description 2",
                20
            )
        )
        productRecommendation.add(
            Product(
                "Product 3",
                "Description 3",
                30
            )
        )
        productRecommendation.add(
            Product(
                "Product 4",
                "Description 4",
                40
            )
        )
        productRecommendation.add(
            Product(
                "Product 5",
                "Description 5",
                50
            )
        )
        _productRecommendationList.value = productRecommendation
        _isLoading.value = false
    }

    fun getArticle () {
        _isLoading.value = true
        _isError.value = false
        val article = ArrayList<Article>()
        article.add(
            Article(
                "Article 1",
                20,
            )
        )
        article.add(
            Article(
                "Article 2",
                10,
            )
        )
        article.add(
            Article(
                "Article 3",
                25,
            )
        )
        article.add(
            Article(
                "Article 4",
                20,
            )
        )
        article.add(
            Article(
                "Article 5",
                30,
            )
        )
        _articleList.value = article
        _isLoading.value = false
    }
}