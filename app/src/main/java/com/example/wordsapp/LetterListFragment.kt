package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class LetterListFragment : Fragment() {
  private var _binding : FragmentLetterListBinding? = null

  private val binding get() = _binding!!
  private lateinit var recyclerView: RecyclerView
  private var isLinearLayoutManager = true


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentLetterListBinding.inflate(inflater, container, false)
    return binding.root
//    return super.onCreateView(inflater, container, savedInstanceState)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    recyclerView = binding.recyclerView
    chooseLayout()
//    super.onViewCreated(view, savedInstanceState)
  }

  private fun chooseLayout() {
    if (isLinearLayoutManager) {
      recyclerView.layoutManager = LinearLayoutManager(requireContext())
    } else {
      recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
    }
    recyclerView.adapter = LetterAdapter()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.layout_menu, menu)

    val layoutButton = menu.findItem(R.id.action_switch_layout)
    setIcon(layoutButton)
  }

  private fun setIcon(menuItem : MenuItem?){
    if(menuItem === null)
      return

    menuItem.icon =
      if(isLinearLayoutManager)
        ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_grid_layout)
      else ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_linear_layout)
  }


  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    Toast.makeText(requireContext(), requireContext().resources.getResourceEntryName(item.itemId).toString(), Toast.LENGTH_SHORT).show()
    // how to get resource name by resource identifier

    return when (item.itemId){
      R.id.action_switch_layout -> {

        isLinearLayoutManager = !isLinearLayoutManager
//        Toast.makeText(requireContext(), isLinearLayoutManager.toString(), Toast.LENGTH_SHORT).show()

        chooseLayout()
        setIcon(item)
        return true
      }

      R.id.random_button -> {
//        Toast.makeText(requireContext(), "On Press App Icon", Toast.LENGTH_SHORT).show()

        return true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

}