package com.example.projectdongthue

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AssetAdapter (var context: Context, var assetarray: ArrayList<Taisan> ) : BaseAdapter() {
    class Viewholder(row: View){
        var assetlayout: TextView

        init {
            assetlayout = row.findViewById(R.id.personlayout)
        }
    }
    override fun getCount(): Int {
        return assetarray.size
    }

    override fun getItem(position: Int): Taisan {
        return assetarray.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewholder: Viewholder
        if (convertView == null){
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.person_layout,null)
            viewholder = Viewholder(view)
            view.tag = viewholder
        } else {
            view = convertView
            viewholder = convertView.tag as Viewholder
        }
        var asset : Taisan = getItem(position)
        viewholder.assetlayout.text = "+ ${asset.name} - ${formatter(asset.value)}"
        return view as View
    }

    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

}