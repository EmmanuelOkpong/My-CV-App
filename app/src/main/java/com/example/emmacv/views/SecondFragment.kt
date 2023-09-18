package com.example.emmacv.views

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emmacv.R
import com.example.emmacv.databinding.FragmentSecondBinding
import com.example.emmacv.model.User
import com.example.emmacv.viewmodel.DetailViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    val args : SecondFragmentArgs by navArgs()
    private lateinit var detailViewModel: DetailViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        val edit_fullName=binding.editFullName
        val edit_title=binding.editTitle
        val edit_gitHubHandle=binding.editGitHuHandle
        val edit_slackName=binding.editSlackName
        val edit_shortBio=binding.editShorBio
        //  fullName.text=args.currentDetails.fullName.edi
        edit_fullName.setText(args.currentDetails.fullName)
        edit_title.setText(args.currentDetails.title)
        edit_gitHubHandle.setText(args.currentDetails.gitHandle)
        edit_slackName.setText(args.currentDetails.slackName)
        edit_shortBio.setText(args.currentDetails.shortBio)



        binding.buttonSecond.setOnClickListener {view->
             updateDetails()
        }



    }

    private fun updateDetails() {

        val fullName= binding.editFullName.text.toString()
        val title= binding.editTitle.text.toString()
        val slackName= binding.editSlackName.text.toString()
        val gitHubHandle=binding.editGitHuHandle.text.toString()
        val shortBio= binding.editShorBio.text.toString()


        if (inputCheck(fullName,title,slackName,gitHubHandle,shortBio)){

            val updatedDetails= User(args.currentDetails.id,fullName,title,slackName,gitHubHandle,shortBio)

            detailViewModel.updateUser(updatedDetails)
            Toast.makeText(requireContext(),"Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all field", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(fullName:String,title:String, slackName:String,gitHubHandle:String, shortBio:String,):Boolean{
        return !(TextUtils.isEmpty(fullName) && TextUtils.isEmpty(title) && TextUtils.isEmpty(slackName) && TextUtils.isEmpty(gitHubHandle) && TextUtils.isEmpty(shortBio) )

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}