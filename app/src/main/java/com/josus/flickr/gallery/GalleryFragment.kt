package com.josus.flickr.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josus.flickr.ItemAdapter
import com.josus.flickr.databinding.FragmentGalleryBinding
import com.josus.flickr.networking.ApiClient
import com.josus.flickr.networking.PhotoDirectory
import retrofit2.Call
import retrofit2.Response


class   GalleryFragment : Fragment() {


    private var binding:FragmentGalleryBinding?=null
private lateinit var apiClient: ApiClient
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_gallery, container, false)
         binding = FragmentGalleryBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding!!.lifecycleOwner = this



       // val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
val recyclerView=binding!!.recyclerView



        return binding!!.root
    }

    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val data=Photo()



       apiClient=ApiClient()


        apiClient.getApiService().getPhotos()
            .enqueue(object :retrofit2.Callback<PhotoDirectory> {
                override fun onResponse(
                    call: Call<PhotoDirectory>,
                    response: Response<PhotoDirectory>
                ) {

                    if (response.isSuccessful) {
                       val ph= response.body()?.photos
                        Log.d("Response",ph.toString())
                        val Photos=ph!!.photo

                        val recycler = binding!!.recyclerView
                        recycler.adapter = context?.let { ItemAdapter(it, Photos) }
                       // layoutManager=GridLayoutManager(context)
                        layoutManager= LinearLayoutManager(context)
                        recycler.layoutManager=layoutManager


                    }
                    else{
                        toast("Error: No Data receives")
                        Log.d("RetroError",response.message().toString())
                    }

                }

                override fun onFailure(call: Call<PhotoDirectory>, t: Throwable) {
                    val errorMsg=t.toString()
                 toast(errorMsg)
                    Log.d("RetroFailure",t.toString())
                }

            } )




    }
    fun toast(msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

}


