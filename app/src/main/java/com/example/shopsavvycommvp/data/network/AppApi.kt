package com.example.shopsavvycommvp.data.network


import com.example.shopsavvycommvp.data.network.response.Category
import com.example.shopsavvycommvp.data.network.response.Comment
import com.example.shopsavvycommvp.data.network.response.Product
import com.example.shopsavvycommvp.data.network.response.Response
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET("category.php")
    fun getCategory(): Observable<List<Category>>
    @GET("product.php")
    fun getProduct(@Query("key_product") key: String): Observable<List<Product>>
    @GET("selectCommentByUser.php")
    fun getComment(@Query("idProduct") keyProduct: String): Observable<List<Comment>?>

    @GET("uploadReply.php")
    fun uploadReply(@Query("username") username: String,
                    @Query("image") images: String,
                    @Query("content") content: String,
                    @Query("idComment") idComment: Int) : Observable<String>

    @GET("uploadComment.php")
    fun uploadComment(@Query("username") username: String,
                      @Query("image") images: String,
                      @Query("content") content: String,
                      @Query("review") reviews: Int,
                      @Query("idProduct") idProduct: Int) : Observable<String>

    @GET("searchProduct.php")
    fun searchProduct(@Query("q") q: String?) : Observable<List<Product>>
}