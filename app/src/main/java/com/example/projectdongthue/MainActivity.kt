package com.example.projectdongthue

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
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

        val car = createAsset("Xe Oto",300000000)

        val bike = createAsset("Xe May",100000000)

        val phone = createAsset("Dien Thoai",50000000)

        val taisan:ArrayList<Taisan> = ArrayList()
        taisan.add(car)
        taisan.add(bike)
        taisan.add(phone)

        val person1 = createPerson("Nguyen Van Trung", arrayListOf(car, car, car, car, car))

        val person2 = createPerson("Nguyen Van Cuong", arrayListOf(car, car, car, bike))

        val person3 = createPerson("Le Thi Thu", arrayListOf(car, car, car, bike, phone))

        val person4 = createPerson("Le Van Anh", arrayListOf(car, bike, bike))

        val person5 = createPerson("Thi Thi", arrayListOf(car, car, car, phone))

        val person: ArrayList<ConNguoi> = ArrayList()
        person.add(person1)
        person.add(person2)
        person.add(person3)
        person.add(person4)
        person.add(person5)

        val sumasset: ArrayList<Int> = ArrayList()

        val listObject: ArrayList<Object> = ArrayList()
        listObject.add(bike)
        listObject.add(car)
        listObject.add(phone)
        listObject.add(person1)
        listObject.add(person2)
        listObject.add(person3)
        listObject.add(person4)
        listObject.add(person5)


        for (i in 0 until person.size) {
            sumasset.add(0)
            for (j in 0 until person[i].Taisans.size) {
                sumasset[i] = sumasset[i] + person[i].Taisans[j].value
            }
        }

        //Tong so nguoi trong danh sach
        Log.d(TAG, "1/ Tong co ${person.size} nguoi trong danh sach")

        //So nguoi dong thue
        var dem = 0
        var dem2 = 0
        for (i in 0 until sumasset.size) {
            // diem so ng dong/ kh dong thue
            if (sumasset[i] >= VALUE_MAX) {
                dem++
            }else dem2++
        }
        Log.d(TAG, "2/ Co $dem nguoi dong thue")
        for (i in 0 until sumasset.size) {
            if (sumasset[i] >= VALUE_MAX) {
                Log.d(TAG,"- ${person[i].name}: " + formatter(sumasset[i]) + " vnd")
            }
        }

        //So nguoi khong dong thue
        Log.d(TAG, "3/ Co $dem2 nguoi khong dong thue")
        var demoto = 0
        var demxemay = 0
        var demdt = 0
        for (i in 0 until sumasset.size) {
            if (sumasset[i] < VALUE_MAX) {
                Log.d(TAG,"- ${person[i].name}: " + formatter(sumasset[i]) + " vnd")
                for (j in 0 until person[i].Taisans.size ){
                    when (person[i].Taisans[j].name){
                        "Xe Oto" -> demoto ++
                        "Xe May" -> demxemay ++
                        "Dien Thoai" -> demdt ++
                    }
                }
                if (demoto >=1) Log.d(TAG, "+ $demoto Xe Oto: " + formatter(car.value*demoto) + " vnd")
                if (demxemay >=1) Log.d(TAG, "+ $demxemay Xe May: " + formatter(bike.value*demxemay) + " vnd")
                if (demdt >=1) Log.d(TAG, "+ $demdt Dien Thoai: " + formatter(phone.value*demdt) + " vnd")
                demoto = 0
                demxemay = 0
                demdt = 0
            }
        }

        //Thong ke danh sach
        Log.d(TAG,"4/ Thong ke danh sach doi tuong")
        for(i in 0 until listObject.size) {
            Log.d(TAG, listObject[i].getInfo())
        }
    }

    private fun createPerson(name: String, listAsset: ArrayList<Taisan>): ConNguoi {
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


