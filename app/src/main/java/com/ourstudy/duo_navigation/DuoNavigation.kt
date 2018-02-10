package com.ourstudy.duo_navigation

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.ourstudy.R
import com.ourstudy.android.AndroidActivity
import com.ourstudy.java.JavaActivity


import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle


class DuoNavigation : AppCompatActivity() {


    private lateinit var mViewHolder: ViewHolder
    private lateinit var mActivity:AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in, R.anim._slide_out)
        setContentView(R.layout.activity_main)

        mActivity=this
        mViewHolder = ViewHolder()
        handleDrawer()

    }


    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)



        mViewHolder.btn_1.setOnClickListener { v ->
            startActivity(Intent(mActivity, JavaActivity::class.java))
            overridePendingTransition(R.anim.slide_in, R.anim._slide_out)
        }

         mViewHolder.btn_2.setOnClickListener { v ->
            startActivity(Intent(mActivity, AndroidActivity::class.java))
             overridePendingTransition(R.anim.slide_in, R.anim._slide_out)
         }


        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

        mViewHolder.mToolbar.setNavigationIcon(R.drawable.ic_lines_menu)
        mViewHolder.mToolbar.hideOverflowMenu();
        mViewHolder.mToolbar.showContextMenu();


    }


    //  mViewHolder.mDuoDrawerLayout.closeDrawer();

    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout
        val mToolbar: Toolbar
        val btn_1: TextView
        val btn_2: TextView

        init {
            mDuoDrawerLayout = findViewById(R.id.drawer)
            mToolbar = findViewById(R.id.toolbar)
            btn_1 = findViewById(R.id.btn_1)
            btn_2 = findViewById(R.id.btn_2)
        }
    }


    override fun onBackPressed() {

        if (mViewHolder.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setMessage(resources.getString(R.string.txt_close_app))
                .setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ -> finish() }
                .setNegativeButton(resources.getString(R.string.txt_No), null)
                .show()
    }


    override fun onResume() {
        super.onResume()

        if (mViewHolder.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
    }


    override fun onStop() {
        if (mViewHolder.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        super.onStop()
    }

}
