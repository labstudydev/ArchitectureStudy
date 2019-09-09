package com.exam.elevenstreet.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.elevenstreet.R
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.list_layout.view.*
import java.net.HttpURLConnection as HttpURLConnection1


class ProductAdapter(val productList: ArrayList<ProductResponse>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product: ProductResponse = productList[position]

        holder.productName.text = product.productName
        holder.productPrice.text = product.productPrice
        holder.productCode.text = product.productCode
        holder.productImage.text = product.productImage


//        val image = p0?.itemView.product_image_tv
//        Glide.with(p0?.itemView?.context).load(product.productImage).into(p0?.productimage)


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

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(holder.context).inflate(R.layout.list_layout, holder, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = productList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName = itemView.product_name_tv
        val productPrice = itemView.product_price_tv
        val productCode = itemView.product_code_tv
        val productImage = itemView.product_image_tv


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
//        bitmap = BitmapFactory.decodeStream(inputStream)
//
//
//
//    }catch (e:Exception){
//        e.printStackTrace()
//    }
//
//
//    return bitmap
//}
