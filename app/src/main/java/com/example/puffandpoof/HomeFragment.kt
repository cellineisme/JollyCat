package com.example.puffandpoof

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var catRecyclerView: RecyclerView
    private lateinit var catAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        catRecyclerView = view.findViewById(R.id.cat_list_recycler_view)
        catAdapter = CatAdapter(catList, object: CatAdapter.OnItemClickListener{
            override fun onItemClick(item: Cat) {
               val intent = Intent(context, CatDetailPage::class.java)

                intent.putExtra("catName", item.catName)
                intent.putExtra("catDesc", item.catDesc)
                intent.putExtra("catPrice", item.catPrice)
                intent.putExtra("catImg", item.catImg)

                startActivity(intent)
            }
        })
        catRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        catRecyclerView.adapter = catAdapter
        return view
    }
}
