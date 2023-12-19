package com.example.test2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Адаптер для отображения элементов в RecyclerView
class ItemsAdapter(var items: List<Item>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    // ViewHolder содержит представления для отображения содержимого элемента
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
        val desc: TextView = view.findViewById(R.id.item_list_desc)
        val price: TextView = view.findViewById(R.id.item_list_price)
        val btn: Button = view.findViewById(R.id.item_list_button)
    }

    // Создание нового элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return MyViewHolder(view)
    }

    // Возвращает количество элементов в списке
    override fun getItemCount(): Int {
        return items.count()
    }

    // Заполнение списка данными
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.desc.text = items[position].desc
        holder.price.text = items[position].price.toString() + "$"

        // Получение идентификатора изображения из ресурсов
        var imageId = context.resources.getIdentifier(
            items[position].image,
            "drawable",
            context.packageName
        )

        // Устанавливление изображения
        holder.image.setImageResource(imageId)

        // Обработка нажатия на кнопку
        holder.btn.setOnClickListener {
            val intent = Intent(context, ItemActivity::class.java)

            // Передает данные выбранного элемента в ItemActivity
            intent.putExtra("itemTitle", items[position].title)
            intent.putExtra("itemText", items[position].text)

            // Запускает ItemActivity
            context.startActivity(intent)
        }
    }
}
