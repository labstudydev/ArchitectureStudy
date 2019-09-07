//package user.manage.fbmember
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseUser
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_login)
//
//        val accountManager = AccountManager.getInstance()
//
//        Login.setOnClickListener{
//            val nextIntent = Intent(this.MainActivity::class.java)
//
//            accountManager.loginUser("wos94@naver.com", "784512", object : AccountManager.CallBack{
//                override fun onSuccess(user: FirebaseUser?) {
//                    Log.d(TAG, "login : ${user?.email}")
//                    Log.d(TAG, "login : ${user?.displayName}")
//                    Log.d(TAG, "login : ${user?.uid}")
//                    startActivity(nextIntent)
//                    finish()
//                }
//                override fun onFailure(message: String) {
//                    Log.d(TAG, message)
//                }
//            })//login finish
//        }
//
//        Register.setOnClickListener{
//            val nextIntent = Intent(this.RegisterActivity::class.java)
//            startActivity(nextIntent)
//            finish()
//        }
//
//        Exit.setOnClickListener{
//            finish()
//        }
//
//        }
//        accountManager.createUser("wos94@naver.com", "784512", "원우석", object : AccountManager.CallBack {
//            override fun onSuccess(user: FirebaseUser?) {
//                Log.d(TAG, "create : ${user?.email}")
//                Log.d(TAG, "create : ${user?.displayName}")
//                Log.d(TAG, "create : ${user?.uid}")
//
//
//
//                        accountManager.updateUser("784512", "우석", object : AccountManager.CallBack{
//                            override fun onSuccess(user: FirebaseUser?) {
//                                Log.d(TAG, "update : ${user?.email}")
//                                Log.d(TAG, "update : ${user?.displayName}")
//                                Log.d(TAG, "update : ${user?.uid}")
//
//                                accountManager.deleteUser(object : AccountManager.CallBack{
//                                    override fun onSuccess(user: FirebaseUser?) {
//                                        Log.d(TAG, "delete success")
//                                    }
//
//                                    override fun onFailure(message: String) {
//                                        Log.d(TAG, "delete failure")
//                                    }
//                                })//delete finish
//                            }
//
//                            override fun onFailure(message: String) {
//                                Log.d(TAG, "update : $message")
//                            }
//                        })//update finish
//
//
//            override fun onFailure(message: String) {
//                Log.d(TAG, message)
//            }
//        })//create finish
//
//
//
//
//    }
//
//    companion object{
//        private const val TAG = "MainActivity"
//    }
//}
//}*/