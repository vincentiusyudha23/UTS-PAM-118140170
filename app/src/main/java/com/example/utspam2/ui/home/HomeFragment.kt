package com.example.utspam2.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utspam2.databinding.FragmentHomeBinding
import com.example.utspam2.model.DataItem
import com.example.utspam2.model.ResponseUser
import com.example.utspam2.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var rvUsers : RecyclerView
    lateinit var adapter: HomeAdapter
    private lateinit var DataUser : ArrayList<DataItem>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val lm = LinearLayoutManager(activity)
        lm.orientation = LinearLayoutManager.VERTICAL

        DataUser = arrayListOf()

        adapter = HomeAdapter(DataUser)

        val recyclerView = binding.rvUsers
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = lm
        recyclerView.adapter = adapter

        getUser()
        buildSearchView()


        return root
    }

    private fun getUser(){
        val client = ApiConfig.getApiServices().getListUsers("20")

        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>){
                if (response.isSuccessful){
                    val dataArray = response.body()?.data as List<DataItem>
                    for (data in dataArray){
                        adapter.addUser(data)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseUser>, t: Throwable){
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    private fun buildSearchView(){
        val searchView = binding.srUser
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                Handler(Looper.getMainLooper()).postDelayed({
                    if(newText != null){
                        val filteredList = ArrayList<DataItem>()
                        for (item in DataUser){
                            if(item.firstName?.lowercase(Locale.ROOT)!!.contains(newText) || item.lastName?.lowercase(
                                    Locale.ROOT)!!.contains(newText)){
                                filteredList.add(item)
                            }
                        }
                        if(filteredList.isEmpty()){
                            Toast.makeText(requireContext(),"Data Not Found", Toast.LENGTH_SHORT)
                        } else{
                            adapter.setFilteredUser(filteredList)
                        }
                    }

                },500)
                return false
            }
        })
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}