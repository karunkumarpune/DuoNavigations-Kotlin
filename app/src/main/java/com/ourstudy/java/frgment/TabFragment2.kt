package com.ourstudy.java.frgment


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ourstudy.R
import com.ourstudy.java.adapter.ImageAdapter
import com.ourstudy.java.model.Model
import org.json.JSONObject

class TabFragment2 : Fragment() {

    private lateinit var recyclerView:RecyclerView
    private var isVisibles: Boolean = false
    private var isStarted: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v=inflater.inflate(R.layout.tab_fragment1, container, false)

        recyclerView = v.findViewById<View>(R.id.list) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = GridLayoutManager(activity, 2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

        isStarted = true
        if (isVisibles)
            jsonParse()

        return v;
    }



    override fun onStart() {
        super.onStart()
        isStarted = true
        if (isVisibles)
            jsonParse() //your request method
    }

    override fun onStop() {
        super.onStop()
        isStarted = false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isVisibles = isVisibleToUser
        if (isVisible && isStarted)
            jsonParse() //your request method
    }


    private fun jsonParse() {
        val list = ArrayList<Model>()
        val p=ProgressDialog(activity)
        p.setCancelable(false)
        p.setMessage("Please wait...")
        p.show()
        list.clear()

        AndroidNetworking.get("https://raw.githubusercontent.com/karunkumarpune/HappyNewYear/master/gallery_image")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        Log.d("TAGS","gallery_image "+response.toString())
                        val array = response!!.getJSONArray("result")
                        for (i in 0 until array.length()) {
                            val json = array.getJSONObject(i)
                            list.add(Model(json.getString("avatar")))
                        }
                        val adp = ImageAdapter(context!!, list)
                        recyclerView.adapter = adp

                        p.dismiss()
                    }
                    override fun onError(anError: ANError?) {
                        p.dismiss()
                    }
                })
    }


}