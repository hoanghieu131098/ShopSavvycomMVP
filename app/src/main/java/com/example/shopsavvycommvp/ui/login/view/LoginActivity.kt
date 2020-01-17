package com.example.shopsavvycommvp.ui.login.view

import android.content.Intent
import android.util.Log
import com.example.shopsavvycommvp.R
import com.example.shopsavvycommvp.ui.base.view.BaseActivity
import com.example.shopsavvycommvp.ui.login.interactor.LoginMVPInteractor
import com.example.shopsavvycommvp.ui.login.presenter.LoginPresenter
import com.example.shopsavvycommvp.ui.main.activities.view.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginMVPView {
    override val layoutId: Int
        get() = R.layout.activity_login

    private lateinit var callbackManager: CallbackManager
    private lateinit var mAuth: FirebaseAuth
    private val TAG = "checkUserLoginFacebook"
    private val RC_SIGN_IN = 1
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    @Inject
    lateinit var presenter: LoginPresenter<LoginMVPView, LoginMVPInteractor>

    override fun setUp() {
        presenter.onAttach(this)
        callbackManager = CallbackManager.Factory.create()
        mAuth = FirebaseAuth.getInstance()
        googleLogin()
        facebookLogin()
        setonClickLisener()
    }

    private fun setonClickLisener() {

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    //------------------------------------FACEBOOK-----------------------------------------//


    private fun facebookLogin() {
        callbackManager = CallbackManager.Factory.create()
        val btnFbLogin = LoginButton(this)
        btnFbLogin.setReadPermissions(
            Arrays.asList(
                "public_profile", "email"
            )
        )
        btnFbLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })

        btn_Login_FB.setOnClickListener {
            btnFbLogin.performClick()
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth.currentUser
                    openMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }

            }

    }


    //-------------------------------------GOOGLE----------------------------------------//


    private fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        btn_Login_GG.setOnClickListener { login() }
    }

    private fun login() {
        val sigInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(sigInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Google", "signInWithCredential:success")
                   openMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("Google", "signInWithCredential:failure", task.exception)

                }
            }
    }


    /* -----------------------------Activity----------------*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Pass the activity result back to the Facebook SDK
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        //google
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }

        }

    }

    private fun openMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}