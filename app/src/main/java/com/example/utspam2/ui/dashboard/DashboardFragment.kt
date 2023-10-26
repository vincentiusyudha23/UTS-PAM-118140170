package com.example.utspam2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.utspam2.databinding.FragmentDashboardBinding
import com.example.utspam2.network.SharedPreferenManage

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var sh: SharedPreferenManage

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sh = SharedPreferenManage(requireContext())

        val user = sh.getRememberUser()
        binding.tvUsername.text = user[0]
        binding.tvNim.text = user[3]
        binding.tvGithub.text = user[2]
        binding.tvEmail.text = user[4]

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}