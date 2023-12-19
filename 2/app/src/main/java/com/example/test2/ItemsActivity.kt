package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()
        val linkToChat: TextView = findViewById(R.id.link_to_chat)

        linkToChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        items.add(Item(1, "wifi", "Arduino с Wi-Fi модулем для IoT проектов", "Набор датчиков для IoT проектов на Arduino", "Arduino с Wi-Fi модулем ESP8266 позволяет разрабатывать различные IoT проекты, включая управление умными устройствами, сбор и передачу данных[^1^][3].", 1500))
        items.add(Item(2, "sensors", "Набор датчиков для Arduino", "Набор датчиков для IoT проектов на Arduino", "Набор датчиков для Arduino, включающий датчики температуры, влажности, освещенности и другие, позволяет создавать разнообразные IoT системы[^2^][2].", 1500))
        items.add(Item(3, "cloud", "Arduino с облачной поддержкой", "Arduino с поддержкой облачных сервисов для IoT", "Arduino с поддержкой облачных сервисов позволяет легко интегрировать ваши IoT проекты с облаком для удаленного управления и мониторинга[^3^][1].", 2500))
        items.add(Item(4, "uno", "Arduino Uno", "Arduino Uno - основная плата для начала работы с Arduino", "Arduino Uno - это плата на базе микроконтроллера ATmega328P. Она имеет 14 цифровых входных/выходных пинов, 6 аналоговых входов, кварцевый резонатор 16 МГц.", 1500))
        items.add(Item(5, "mega", "Arduino Mega", "Arduino Mega - плата для более сложных проектов", "Arduino Mega - это плата на базе микроконтроллера ATmega2560. Она имеет 54 цифровых входных/выходных пина, 16 аналоговых входов, 4 UART порта.", 4000))
        items.add(Item(6, "nano", "Arduino Nano", "Arduino Nano - компактная плата для небольших проектов", "Arduino Nano - это маленькая, гибкая плата на базе микроконтроллера ATmega328P. Она имеет 14 цифровых входных/выходных пинов, 8 аналоговых входов.", 1000))
        items.add(Item(7, "leonardo", "Arduino Leonardo", "Arduino Leonardo - плата с встроенной поддержкой USB", "Arduino Leonardo - это плата на базе микроконтроллера ATmega32u4. Она имеет 20 цифровых входных/выходных пинов, 7 аналоговых входов, встроенную поддержку USB.", 2000))
        items.add(Item(8, "due", "Arduino Due", "Arduino Due - первая плата Arduino на базе ARM", "Arduino Due - это плата на базе 32-битного ядра ARM. Она имеет 54 цифровых входных/выходных пина, 12 аналоговых входов, 4 UART порта.", 3500))
        items.add(Item(9, "yun", "Arduino Yun", "Arduino Yun - плата с Wi-Fi и Linux", "Arduino Yun - это плата на базе микроконтроллера ATmega32u4 и Atheros AR9331. Она поддерживает Wi-Fi и Linux, что позволяет создавать сложные IoT проекты.", 5000))
        items.add(Item(10, "mkr1000", "Arduino MKR1000", "Arduino MKR1000 - плата с Wi-Fi для IoT проектов", "Arduino MKR1000 - это плата, разработанная специально для IoT проектов. Она имеет встроенный модуль Wi-Fi и позволяет легко подключаться к интернету.", 3000))

        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)
    }
}