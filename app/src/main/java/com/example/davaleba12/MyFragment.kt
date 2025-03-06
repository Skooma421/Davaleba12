import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba12.DetailFragment
import com.example.davaleba12.Product
import com.example.davaleba12.ProductAdapter
import com.example.davaleba12.R

class MyFragment : Fragment(R.layout.fragment) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var productList: List<Product>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchEditText: AppCompatEditText = view.findViewById(R.id.itemSearch)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        productList = listOf(
            Product(R.drawable.img_1, "Tall Lamp", 4.9f, "8,567 sold", "$79.00"),
            Product(R.drawable.img_1, "Tall Lamp", 4.9f, "8,567 sold", "$79.00"),
            Product(R.drawable.img_1, "Tall Lamp", 4.9f, "8,567 sold", "$79.00"),
            Product(R.drawable.img_1, "Tall Lamp", 4.9f, "8,567 sold", "$79.00"),
            Product(R.drawable.img_1, "Tall Lamp", 4.9f, "8,567 sold", "$79.00")
        )


        adapter = ProductAdapter(productList) { selectedProduct ->
            val detailFragment = DetailFragment.newInstance(selectedProduct)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(query.toString())
            }
        })
    }

    private fun filterList(query: String) {
        val filteredList = if (query.isEmpty()) {
            productList
        } else {
            productList.filter { it.title.contains(query, ignoreCase = true) }
        }
        adapter.updateList(filteredList)
    }
}

