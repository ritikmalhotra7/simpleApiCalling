package com.complete.simpleapicalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.complete.simpleapicalling.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Main Activity"
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding:ActivityMainBinding get() = _binding!!
    private var todoAdapter : TodoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        binding.progressbar.isVisible = true
        val repo = TodoRepo()
        val viewModelFactory = TodoViewModelFactory(repo)
        val viewModel = ViewModelProvider(this,viewModelFactory)
            .get(TodoViewModel::class.java)
        var getAll = viewModel.getTodo()
        viewModel.myResponse.observe(this,{response->
            if(response.isSuccessful && response.body() != null){
                todoAdapter!!.todos = response.body()!!
            }else{
                Log.d(TAG,"Response not Successful")
            }
        })

        binding.progressbar.isVisible = false
        /*lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible = true
            val response = try{
                RetrofitInstance.api.getAll()
            }catch (e:IOException){
                Log.d(TAG,"you might not have internet connectivity")
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.d(TAG,"HTTP Exception")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                todoAdapter!!.todos = response.body()!!
            }else{
                Log.d(TAG,"Response not Successful")
            }
            binding.progressbar.isVisible = false
        }*/


    }

    private fun setUpRecyclerView() = binding.recyclerview.apply{
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}
































