package com.example.projectdongthue

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        const val VALUE_MAX = 1000000000
        const val VALUE_PERSON = "Con nguoi"
        const val VALUE_ASSET = "Tai san"
        const val TAG = "Thongbao"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listviewperson: ListView = findViewById(R.id.listviewPerson)
        val listviewasset: ListView = findViewById(R.id.lisviewAsset)
//        val btnPerson: RadioButton = findViewById(R.id.btnPerson)
        val editAsset: TextView = findViewById(R.id.textviewAsset)
        val editvalue: EditText = findViewById(R.id.editAssetValue)
        editvalue.visibility = View.GONE
        val editTextName: EditText = findViewById(R.id.editName)
        val btnResult = findViewById<TextView>(R.id.btnResult)

        var res = "Tai san: "

//        val car = createAsset("Xe Oto",300000000)
//
//        val bike = createAsset("Xe May",100000000)
//
//        val phone = createAsset("Dien Thoai",50000000)

        val taisan:ArrayList<Taisan> = ArrayList()
//        taisan.add(car)
//        taisan.add(bike)
//        taisan.add(phone)
//        taisan.add(no)
//
//        val person1 = createPerson("Nguyen Van Trung", arrayListOf(car, car, car, car, car))
//
//        val person2 = createPerson("Nguyen Van Cuong", arrayListOf(car, car, car, bike))
//
//        val person3 = createPerson("Le Thi Thu", arrayListOf(car, car, car, bike, phone))
//
//        val person4 = createPerson("Le Van Anh", arrayListOf(car, bike, bike))
//
//        val person5 = createPerson("Thi Thi", arrayListOf(car, car, car, phone))

        val person: ArrayList<ConNguoi> = ArrayList()
//        person.add(person1)
//        person.add(person2)
//        person.add(person3)
//        person.add(person4)
//        person.add(person5)

        val sumasset: ArrayList<Int> = ArrayList()

        val countasset: ArrayList<CountAsset> = ArrayList()

        val listObject: ArrayList<Object> = ArrayList()
//        listObject.add(bike)
//        listObject.add(car)
//        listObject.add(phone)
//        listObject.add(person1)
//        listObject.add(person2)
//        listObject.add(person3)
//        listObject.add(person4)
//        listObject.add(person5)

        val assetstorage: ArrayList<Taisan?> = ArrayList()

        listviewperson.adapter = PersonAdapter(this@MainActivity, person)
        listviewasset.adapter = AssetAdapter(this@MainActivity, taisan)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.btnPerson){
                editAsset.visibility = View.VISIBLE
                editvalue.visibility = View.GONE
                Toast.makeText(this, "You have chosen 'Con người'", Toast.LENGTH_SHORT).show()
                val btnAdd = findViewById<Button>(R.id.btnAdd)
                btnAdd.setOnClickListener{
                    val name:String = (editTextName.text.toString())
                    val ps = createPerson(name, assetstorage)
                    person.add(ps)
                    listObject.add(ps)
                    Toast.makeText(this, "Adding $name successfully", Toast.LENGTH_SHORT).show()
                    listviewperson.adapter = PersonAdapter(this@MainActivity, person)
                    res = "Tai san: "
                    editAsset.text = res
                    for (i in assetstorage.size-1  downTo 0){
                        assetstorage.removeAt(i)
                    }
                }
            }
            if (checkedId == R.id.btnAsset){
                editvalue.visibility = View.VISIBLE
                editAsset.visibility = View.GONE
                Toast.makeText(this, "You have chosen 'Tài sản'", Toast.LENGTH_SHORT).show()
                val btnAdd = findViewById<Button>(R.id.btnAdd)
                btnAdd.setOnClickListener {
                    val name:String = (editTextName.text.toString())
                    val value:Int = (editvalue.text.toString()).toInt()
                    val asset = createAsset(name,value)
                    taisan.add(asset)
                    listObject.add(asset)
                    Toast.makeText(this, "Adding $name successfully", Toast.LENGTH_SHORT).show()
                    listviewasset.adapter = AssetAdapter(this@MainActivity, taisan)
                }
            }
        }

        listviewasset.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                assetstorage.add(taisan[position])
                res = createTextviewAsset(res,taisan[position].name)
                editAsset.text = res
            }

        btnResult.setOnClickListener {
            for (i in 0 until person.size) {
                sumasset.add(0)
                for (j in 0 until person[i].Taisans.size) {
                    sumasset[i] = sumasset[i] + person[i].Taisans[j]!!.value
                }
            }

            //Tong so nguoi trong danh sach
            Log.d(TAG, "1/ Tong co ${person.size} nguoi trong danh sach")

            //So nguoi dong thue
            var count = 0
            var count2 = 0
            for (i in 0 until sumasset.size) {
                // dem so ng dong/ kh dong thue
                if (sumasset[i] >= VALUE_MAX) {
                    count++
                } else count2++
            }
            Log.d(TAG, "2/ Co $count nguoi dong thue")
            for (i in 0 until sumasset.size) {
                if (sumasset[i] >= VALUE_MAX) {
                    Log.d(TAG, "- ${person[i].name}: " + formatter(sumasset[i]) + " vnd")
                }
            }

            //So nguoi khong dong thue
            Log.d(TAG, "3/ Co $count2 nguoi khong dong thue")
            for (i in 0 until taisan.size) {
                countasset.add(createCountAsset(taisan[i].name,  taisan[i].value))
            }
            for (i in 0 until person.size) {
                if (sumasset[i] < VALUE_MAX) {
                    Log.d(TAG, "- ${person[i].name}: " + formatter(sumasset[i]) + " vnd")
                    var k = 0
                    for (j in 0 until person[i].Taisans.size) {
                        if (countasset[k].name == person[i].Taisans[j]?.name) {
                            countasset[k].count++
                        } else {
                            k++
                            while (countasset[k].name != person[i].Taisans[j]?.name) {
                                k++
                            }
                            countasset[k].count++
                        }
                    }
                    for (l in 0 until taisan.size) {
                        if (countasset[l].count > 0) {
                            Log.d(
                                TAG,
                                "+ ${countasset[l].count} ${countasset[l].name}: " + formatter(
                                    countasset[l].value * countasset[l].count
                                ) + " vnd"
                            )
                            countasset[l].count = 0
                        }
                    }
                }
            }

            //Thong ke danh sach
            Log.d(TAG, "4/ Thong ke danh sach doi tuong")
            for (i in 0 until listObject.size) {
                Log.d(TAG, listObject[i].getInfo())
            }
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createTextviewAsset(a:String, b:String): String {
        var res:String = a
        res = "$res $b,"
        return res
    }

    private fun createCountAsset(name: String, value: Int): CountAsset {
        val res = CountAsset()
        res.name = name
        res.value = value
        return res
    }

    private fun createPerson(name: String, listAsset: ArrayList<Taisan?>): ConNguoi {
        val person = ConNguoi()
        person.name = name
        for(i in 0 until listAsset.size) {
            person.Taisans.add(listAsset[i])
        }
        return person
    }

    private fun createAsset(name: String, value: Int): Taisan {
        val res = Taisan()
        res.name = name
        res.value = value
        return res
    }

    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

}





