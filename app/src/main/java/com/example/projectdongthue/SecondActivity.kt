package com.example.projectdongthue

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    companion object {
        const val ARG_DATA = "ARG_DATA"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnExit: Button = findViewById(R.id.btnExit)

        btnExit.setOnClickListener(){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val listviewperson: TextView = findViewById(R.id.listviewPerson2)
        val listviewasset: TextView = findViewById(R.id.listviewAsset2)
        val count = intent.getIntExtra("data", 0)
        val count2 = intent.getIntExtra("data2", 0)
        val txt1 : TextView = findViewById(R.id.txtcau1)
        val txt2 : TextView = findViewById(R.id.txtcau2)
        val txt3 : TextView = findViewById(R.id.txtcau3)
        var res = ""
        var res2 = ""



        val dataInput = intent.getStringExtra(ARG_DATA)
        if (dataInput != null) {
            val data = Gson().fromJson(dataInput, ResourceData::class.java)
            txt1.text = "Có ${data.person.size} người trong danh sách"
            txt2.text = "Có $count người đóng thuế"
            txt3.text = "Có $count2 người không đóng thuế"
            for (i in 0 until data.sumasset.size) {
                if (data.sumasset[i] >= MainActivity.VALUE_MAX) {
                    res = createTextviewAsset(
                        res,
                        "- ${data.person[i].name}: " + formatter(data.sumasset[i]) + " vnd"
                    )
                    listviewperson.text = res
                } else {
                    res2 = createTextviewAsset(
                        res2,
                        "- ${data.person[i].name}: " + formatter(data.sumasset[i]) + " vnd"
                    )
                    listviewasset.text = res2
                }
            }
        }

    }

    private fun createTextviewAsset(a: String, b: String): String {
        var res:String = a
        res = "$res $b \n"
        return res
    }

    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

}