package com.example.projectdongthue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PersonAdapter (var context: Context, var personarray: ArrayList<ConNguoi>) : BaseAdapter() {
    class Viewholder(row: View){
        var personlayout: TextView

        init {
            personlayout = row.findViewById(R.id.personlayout)
        }
    }
    override fun getCount(): Int {
        return personarray.size
    }

    override fun getItem(position: Int): ConNguoi {
        return personarray.get(position)
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
        var person : ConNguoi = getItem(position)
        viewholder.personlayout.text = "- ${person.name}: ${printAsset(person.Taisans)}"
        return view as View
    }

    fun printAsset(person: ArrayList<Taisan?> = ArrayList()): String {
        var res:String = person[0]!!.name
        for (i in 1 until person.size){
            res += ", ${person[i]!!.name}"
        }
        return res
    }

}