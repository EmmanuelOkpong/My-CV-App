package com.example.emmacv.views

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emmacv.R
import com.example.emmacv.database.DetailDatabase
import com.example.emmacv.databinding.FragmentFirstBinding
import com.example.emmacv.model.User
import com.example.emmacv.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    lateinit var dt: User
    lateinit var detailDatabase:DetailDatabase
    private lateinit var detailViewModel: DetailViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        insertDataToDatabase()
        detailViewModel.readAllDetail.observe(viewLifecycleOwner, Observer{ user ->
            setDetail(user)
        })
        dt = User(
            id ,
            binding.fullNameID.text.toString(),
            binding.titleName.text.toString(),
            binding.slackName.text.toString(),
            binding.gitHubHandle.text.toString(),
            binding.shortBio.text.toString()
        )

        binding.buttonFirst.setOnClickListener {
            val myAction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(dt)
            Navigation.findNavController(view).navigate(myAction)

            //  findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    //function to add data into database
    private fun insertDataToDatabase() {
        val fullName=binding.fullNameID.text.toString()
        val tilteName=binding.titleName.text.toString()
        val slackName =binding.slackName.text.toString()
        val gitHubName=binding.gitHubHandle.text.toString()
        val shortBio=binding.shortBio.text.toString()

        if (inputCheck(fullName,tilteName,slackName,gitHubName,shortBio)){
            val user=User(0,fullName,tilteName,slackName,gitHubName,shortBio)
            detailViewModel.addUser(user)

        }
    }
    private fun inputCheck(fullName:String,titleName:String,slackName:String,gitHubName:String,shortBio:String):Boolean{
        return !(TextUtils.isEmpty(fullName) && TextUtils.isEmpty(titleName) && TextUtils.isEmpty(slackName) && TextUtils.isEmpty(gitHubName) && TextUtils.isEmpty(shortBio) )

    }
    fun setDetail(user: User){
        dt= user
        dt.id
        binding.fullNameID.text=dt.fullName
        binding.titleName.text=dt.title
        binding.gitHubHandle.text=dt.gitHandle
        binding.slackName.text=dt.slackName
        binding.shortBio.text=dt.shortBio
        // notifyDataSetChanged( )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}