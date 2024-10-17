package com.example.efm

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

data class Car(
    val car_id : Int,
    val name : String,
    val price : Double,
    val image : String,
    val is_Full_Option : Boolean
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//      Objects:
        val car1 = Car(1 , "BMW M3 G90" , 1230000.0 , "https://th.bing.com/th/id/OIP.P4t1PKgpr1zl8zaQFXXlKwAAAA?rs=1&pid=ImgDetMain" , true)
        val car2 = Car(2 , "Mercedes SLC 63S" , 2340000.0 , "https://th.bing.com/th/id/R.59169121c00e61d4e407a04ff6b364c7?rik=kpPzz9atoNwI%2fg&riu=http%3a%2f%2fwww.marinoperformancemotors.com%2fimagetag%2f13228%2f16%2fl%2fUsed-2017-Mercedes-Benz-CLS-AMG-CLS-63-S.jpg&ehk=doVnDz2yCdmOugr7VITKq3qzZzS%2bKs9zaNzpZPDmni0%3d&risl=&pid=ImgRaw&r=0" , false)
        val car3 = Car(3 , "CADILAC Escalade" , 4320000.0 , "https://th.bing.com/th/id/R.27626f775baf615307b0d7cb2e577d62?rik=Anfzn1Wzm5gv4w&riu=http%3a%2f%2fwww.marinoperformancemotors.com%2fimagetag%2f13159%2f11%2fl%2fUsed-2019-Cadillac-Escalade-ESV-Platinum.jpg&ehk=obR1a%2bZqYM12iP1DEb0dAVpH1fAIK9gCYBkG1a9ou2Q%3d&risl=&pid=ImgRaw&r=0" , true)

        val car_list = ArrayList<Car>()

        car_list.add(car1)
        car_list.add(car2)
        car_list.add(car3)

        val str_list = ArrayList<String>()

        for(vtr in car_list){
            str_list.add("${vtr.name}")
        }

        val list_view = findViewById<ListView>(R.id.lv)

        val adapter = ArrayAdapter(applicationContext , android.R.layout.simple_list_item_1 , str_list)
        list_view.adapter = adapter

        list_view.setOnItemClickListener { parent, view, position, id ->
            val selectedCar = car_list[position]

            val imgView = findViewById<ImageView>(R.id.image)
            Glide.with(applicationContext)
                .load(selectedCar.image)
                .into(imgView)

            val priceTxt = findViewById<TextView>(R.id.price)
            priceTxt.text = "Price: ${selectedCar.price}"

            val optionSwitch = findViewById<Switch>(R.id.option)
            if (selectedCar.is_Full_Option){
                optionSwitch.isChecked=true
            }
            else{
                optionSwitch.isChecked= false
            }
        }
    }
}