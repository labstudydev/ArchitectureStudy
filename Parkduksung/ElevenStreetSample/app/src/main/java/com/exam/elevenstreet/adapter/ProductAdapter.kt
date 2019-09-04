package com.exam.elevenstreet.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import java.net.HttpURLConnection as HttpURLConnection1


class ProductAdapter(val productList: ArrayList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val product : ProductResponse = productList[p1]

        p0?.productname?.text = product.productName
        p0?.productprice?.text = product.productPrice
        p0?.productcode?.text = product.productCode
        p0?.productimage?.text = product.productImage

        //TODO : 이미지 작업할 곳
//        Log.d("ProductAdapter", "${loadImageUsingHttpUrlConnection()}")
//        p0?.productimage?.setImageBitmap(loadImageUsingHttpUrlConnection())
//        val image = p0?.itemView.product_image_tv
//
//        image.setImageBitmap(loadImageUsingHttpUrlConnection())
//        Log.d("111111111111111111111", "${p0?.itemView?.context}")
//        Log.d("2222222222222222222222", "${product.productImage}")
//        Log.d("33333333333333333333", "${p0?.productimage}")
//        Picasso.with(p0?.itemView?.context).load(product.productImage).into(p0?.productimage)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.list_layout, p0, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return productList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productname  = itemView.findViewById(R.id.product_name_tv) as TextView
        val productprice = itemView.findViewById(R.id.product_price_tv) as TextView
        val productcode = itemView.findViewById(R.id.product_code_tv) as TextView
        val productimage = itemView.findViewById(R.id.product_image_tv) as TextView


    }



}
//TODO : 이미지 작업할 곳
//private fun loadImageUsingHttpUrlConnection() : Bitmap? {
//    var imgUri = "http://i.011st.com/t/250/pd/17/5/9/1/4/5/1/nEapZ/500591451_L300.jpg"
//
//    //val connection  = URL(imgUri).openConnection()
//    val url = URL(imgUri)
//    val urlConnection = url.openConnection() as HttpURLConnection1
//
//    var bitmap: Bitmap? = null
//    try {
//        //urlConnection.connect()
//        val inputStream  = BufferedInputStream(urlConnection.inputStream)
//
//        bitmap = BitmapFactory.decodeStream(inputStream)
//
//
//    }catch (e:Exception){
//        e.printStackTrace()
//    }
//
//
//    return bitmap
//}
