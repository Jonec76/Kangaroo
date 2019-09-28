package com.example.kangaroo

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.*
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.kangaroo._1_login.loginFragment
import com.example.kangaroo._2_fragmentDemo.fragmentDemo
import android.widget.EditText
import android.view.animation.*
import com.example.kangaroo._3_viewPager.viewPager
import com.example.kangaroo._4_todoList.todoList
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val fm = supportFragmentManager
    private var mp : MediaPlayer? = null
    private var sp = SoundPool.Builder().setMaxStreams(8).build();
    private val sounds = mutableListOf<Int>()



    inner class animListnerEnd():Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {
        }

        override fun onAnimationEnd(p0: Animation?) {
            drawer_layout.visibility = VISIBLE
            sp.pause(sounds[0])

        }
        override fun onAnimationStart(p0: Animation?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sounds.add(sp.load(this, R.raw.star, 1))
        sp.setOnLoadCompleteListener { soundPool, sampleId, status ->
            soundPool.play(sounds[0], 1.0f, 1.0f, 1, 2, 1.0f)
            setStartAnim()
        }
        setContentView(R.layout.activity_main)
        configNavDefaultWidget()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                v.clearFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_1 -> {
                var tmpFragment: Fragment? = fm.findFragmentByTag("loginFragment")
                if(tmpFragment == null)
                    tmpFragment = loginFragment()

                fm.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.loginFrame, tmpFragment, "loginFragment")
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_2 -> {
                var tmpFragment: Fragment? = fm.findFragmentByTag("fragmentDemo")
                if(tmpFragment == null)
                    tmpFragment = fragmentDemo()

                fm.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.loginFrame, tmpFragment, "fragmentDemo")
                    .addToBackStack(null)
                    .commit()
            }

            R.id.nav_3 ->{
                var tmpFragment: Fragment? = fm.findFragmentByTag("viewPager")
                if(tmpFragment == null)
                    tmpFragment = viewPager()

                fm.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.loginFrame, tmpFragment, "viewPager")
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_4 ->{
                var tmpFragment: Fragment? = fm.findFragmentByTag("todoList")
                if(tmpFragment == null)
                    tmpFragment = todoList()

                fm.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.loginFrame, tmpFragment, "todoList")
                    .addToBackStack(null)
                    .commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("state", "fragme")
    }

    fun setStartAnim(){
        val decorView = window.decorView as ViewGroup
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val startLayoutView = inflater.inflate(R.layout.start_layout, null)
        var performAnimation:Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_out);

        decorView.addView(startLayoutView, 0)
        val animListnerEnd = animListnerEnd()
        performAnimation.setAnimationListener(animListnerEnd)
        startLayoutView.startAnimation(performAnimation)
    }

    fun configNavDefaultWidget(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }
}

