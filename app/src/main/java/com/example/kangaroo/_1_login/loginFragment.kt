package com.example.kangaroo._1_login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.content.Intent
import android.view.inputmethod.EditorInfo
import com.example.kangaroo.R
import kotlinx.android.synthetic.main._1_login.view.*
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task


//1
class loginFragment : Fragment() {
    //2
    val callbackManager = CallbackManager.Factory.create();
    val GOOGLE_LOGIN = 1

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout._1_login, container, false)

        fbLogin(view)
        googleLogin(view)

        view.accountSubmit.setOnClickListener {
            val account = view.account.text.toString()
            val password = view.password.text.toString()
            if(account == "admin" && password == "admin"){
                Toast.makeText(context, "登入成功", Toast.LENGTH_SHORT).show()
                view.img_lock.setImageResource(R.drawable.ic_lock_open_black_24dp)
            }else{
                Toast.makeText(context, "登入失敗", Toast.LENGTH_SHORT).show()
                view.img_lock.setImageResource(R.drawable.ic_lock_outline_black_24dp)

            }
            view.account.text.clear()
            view.password.text.clear()
        }
//        view.password.requestFocus()

        view.account.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                view.account.clearFocus();
                val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.account.getWindowToken(), 0)
                true
            }
            false
        }
        view.password.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                view.password.clearFocus();
                val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.password.getWindowToken(), 0)
                true
            }
            false
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_LOGIN ) {
            googleLoginResult(data)
        }
//        super.onActivityResult(requestCode, resultCode, data)
    }

    fun fbLogin(view :View){
        view.FB_login_button.setReadPermissions("email");
        view.FB_login_button.setFragment(this);
        view.FB_login_button.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onError(error: FacebookException?) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
            }

            override fun onSuccess(result: LoginResult?) {
                val uId: String = result!!.accessToken.userId.toString()
                val token: String = result!!.accessToken.token.toString()
                view.img_lock.setImageResource(R.drawable.ic_lock_open_black_24dp)
                Toast.makeText(context, "user id: "+ result!!.accessToken.userId.toString().substring(0,4) + "XXX 登入", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun googleLogin(view : View){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val signInButton = view.Google_login_button
        val signOutButton = view.Google_logout_button
        val serverClientId = getString(R.string.server_client_id)
        val mGoogleSignInClient = GoogleSignIn.getClient(context!!, gso)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener {
            var mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode(serverClientId)
                .requestEmail()
                .build()
            val mgoogleApiClient = GoogleApiClient
                .Builder(context!!)
                .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
                .build()
            val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(mgoogleApiClient)
            startActivityForResult(signInIntent, GOOGLE_LOGIN)
        }

        signOutButton.setOnClickListener {
            mGoogleSignInClient.signOut()
                .addOnCompleteListener {
                    view.img_lock.setImageResource(R.drawable.ic_lock_outline_black_24dp)
                    view?.Google_login_button?.visibility = View.VISIBLE
                    view?.Google_logout_button?.visibility = View.GONE
                    Toast.makeText(context, "登出 Google", Toast.LENGTH_SHORT).show()
                }
        }

    }

    fun googleLoginResult(data: Intent?){
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        Log.d("login info", data?.extras.toString())
        try {
            val account = task.getResult(ApiException::class.java)
            Toast.makeText(context, "Email: "+ account?.email?.substring(0, 3)+"XXX", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "User id: "+ account?.id?.substring(0, 3)+"XXX", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "User name: "+account?.displayName, Toast.LENGTH_SHORT).show()
            view?.img_lock?.setImageResource(R.drawable.ic_lock_open_black_24dp)
            view?.Google_login_button?.visibility = View.GONE
            view?.Google_logout_button?.visibility = View.VISIBLE
            view?.Google_logout_button?.layoutParams = view?.Google_login_button?.layoutParams
        } catch (e: ApiException) {
            Log.d("error", e.toString())
        }
    }
}