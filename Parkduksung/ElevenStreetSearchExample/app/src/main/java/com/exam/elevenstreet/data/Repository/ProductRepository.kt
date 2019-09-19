package com.exam.elevenstreet.data.Repository

import android.content.Context
import android.os.Handler
import com.exam.elevenstreet.util.App
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.io.InputStream
import java.net.URL


class ProductRepository() {

    private val handler = Handler()

    fun getProductlist(filename: String): List<ProductResponse> {
        val context: Context = App.instance.context()
        val inputStream: InputStream = context.assets.open(filename)
        val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)

        return productRepositoryList
    }

    fun getProductlist1(url: String): List<ProductResponse> {


            val targetURL = URL(url)
            val inputStream = targetURL.openStream()
            val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)
            return productRepositoryList



//        val productRepositoryList = ProductXmlPullParserHandler().parse(inputStream)

    }


}

//    private fun htmlParser(url1: String) {
//        //쓰레드에서 작업
//        Thread(Runnable {
//            val stringBuffer = StringBuffer()
//
//            try {
//                val url = URL(url1)
//                //연결
//                val connectUrl = url.openConnection() as HttpURLConnection
//                connectUrl.connectTimeout = 2000
//                connectUrl.useCaches = false
//                if (connectUrl.responseCode
//                    == HttpURLConnection.HTTP_OK){
//                    //데이터 읽기
//
////                    val productRepositoryList = ProductXmlPullParserHandler().parse(connectUrl.inputStream)
//                    val bufferedReader = BufferedReader(InputStreamReader(connectUrl.inputStream,"utf-8"))
//
//                    while(true) {
//                        //더 이상 읽어올 데이터가 없으면 루프 탈출
//                        val line : String = bufferedReader.readLine() ?: break
//                        //원하는 데이터가 있을때까지 찾기.
//
//                        stringBuffer.append(line+"\n")
//
//                    }
//
//
//                    bufferedReader.close()
//                }
//                //연결 종료
//                connectUrl.disconnect()
//                //값 출력하기
//                handler.post { tv.text = stringBuffer.toString() }
//            } catch (e : Exception) {
//                e.printStackTrace()
//            }
//        }).start() // 쓰레드 시작
//    }
