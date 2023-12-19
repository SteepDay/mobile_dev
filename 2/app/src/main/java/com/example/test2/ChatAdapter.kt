package com.example.test2

import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Адаптер для отображения сообщений в RecyclerView
class ChatAdapter(private val messages: List<Message>) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    // ViewHolder содержит представления для отображения содержимого сообщения
    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.message_text)
    }

    // Модель данных для сообщения
    data class Message(val text: String)

    // Новый элемент списка сообщений
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_in_list, parent, false)
        return MessageViewHolder(view)
    }

    // Возвращает количество сообщений в списке
    override fun getItemCount(): Int {
        return messages.size
    }

    // Заполняет элемент списка данными(сообщениями)
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.messageText.text = messages[position].text
    }
}
