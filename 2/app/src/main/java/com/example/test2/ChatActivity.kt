package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.message
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Update

class ChatActivity : AppCompatActivity() {

    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatAdapter.Message>()
    private lateinit var bot: Bot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val recyclerView: RecyclerView = findViewById(R.id.chat_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(messages)
        recyclerView.adapter = chatAdapter

        val sendButton: Button = findViewById(R.id.send_button)
        val inputText: EditText = findViewById(R.id.input_text)

        var lastChatId: Long? = null

        bot = bot {
            token = "6582439854:AAHZ_YHOzX3pPcikUletpJjFNXVjw9fCEj0"
            dispatch {
                message {
                    lastChatId = update.message?.chat?.id
                    handleTextMessage(this.bot, this.update)
                }
            }
        }

        bot.startPolling()

        sendButton.setOnClickListener {
            val messageText = inputText.text.toString()
            if (messageText.isNotEmpty()) {
                if (lastChatId != null) {
                    // Отправьте сообщение через Telegram Bot API
                    bot.sendMessage(ChatId.fromId(lastChatId!!), messageText)

                    // Добавьте сообщение в список сообщений и обновите RecyclerView
                    messages.add(ChatAdapter.Message(messageText))
                    chatAdapter.notifyDataSetChanged()

                    // Очистите поле ввода
                    inputText.text.clear()
                } else {
                    Toast.makeText(this, "Бот еще не получил ни одного сообщения", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Сообщение не может быть пустым", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun handleTextMessage(bot: Bot, update: Update) {
        // Обработайте входящие сообщения здесь
        val incomingMessage = update.message?.text
        if (!incomingMessage.isNullOrEmpty()) {
            messages.add(ChatAdapter.Message(incomingMessage))
            runOnUiThread { chatAdapter.notifyDataSetChanged() }
        }
    }

}
