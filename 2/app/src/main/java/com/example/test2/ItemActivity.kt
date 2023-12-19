package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
//import com.razorpay.Checkout cистема оплаты

// Это активность для отображения информации об отдельном товаре
class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        // Попытки подключить систему оплаты
        //Checkout.preload(applicationContext)
        // Создание нового объекта Checkout
        //val co = Checkout()
        // Установка идентификатора ключа для Checkout
        //co.setKeyID("rzp_live_XXXXXXXXXXXXXX")

        // Получение одного элемента названия товара
        val title: TextView = findViewById(R.id.item_list_title_one)
        // Получение одного элемента полного описания товара
        val text: TextView = findViewById(R.id.item_list_text)

        // Установка текста для TextView из данных, переданных через Intent
        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")
    }
}
