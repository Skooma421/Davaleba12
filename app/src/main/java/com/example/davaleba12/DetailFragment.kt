package com.example.davaleba12

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_product_detail) {

    companion object {
        fun newInstance(product: Product): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle().apply {
                putInt("image", product.image)
                putString("title", product.title)
                putFloat("rating", product.rating)
                putString("numSold", product.numSold)
                putString("price", product.price)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageview: ImageView = view.findViewById(R.id.item_image)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val numbersSoldView: TextView = view.findViewById(R.id.item_numbers_sold)
        val priceView: TextView = view.findViewById(R.id.itemTotalPrice)
        val ratingView: TextView = view.findViewById(R.id.itemReviews)

        val minusButton: ImageView = view.findViewById(R.id.itemMinus)
        val plusButton: ImageView = view.findViewById(R.id.itemPlus)
        val quantityText: TextView = view.findViewById(R.id.itemCount)

        var productPrice = 0.0
        arguments?.let { bundle ->
            val imageRes = bundle.getInt("image")
            val title = bundle.getString("title")
            val numSold = bundle.getString("numSold")
            val price = bundle.getString("price")
            val rating = bundle.getString("rating")

            imageview.setImageResource(imageRes)
            titleView.text = title
            numbersSoldView.text = numSold
            priceView.text = price
            ratingView.text = "$rating"

            productPrice = price?.replace("$", "")?.toDoubleOrNull() ?: 0.0
        }

        var quantity = 1
        quantityText.text = quantity.toString()

        priceView.text = "$${"%.2f".format(productPrice * quantity)}"

        minusButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                quantityText.text = quantity.toString()
                priceView.text = "$${"%.2f".format(productPrice * quantity)}"
            }
        }

        plusButton.setOnClickListener {
            quantity++
            quantityText.text = quantity.toString()
            priceView.text = "$${"%.2f".format(productPrice * quantity)}"
        }


    }

}

