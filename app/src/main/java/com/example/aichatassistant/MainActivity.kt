package com.example.aichatassistant

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private var statusText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 40, 32, 32)
            setBackgroundColor(Color.rgb(245, 247, 250))
        }

        fun addText(text: String, size: Float, bold: Boolean = false) {
            val view = TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(Color.rgb(35, 45, 60))
                if (bold) setTypeface(null, 1)
                setPadding(0, 12, 0, 12)
            }
            root.addView(view)
        }

        fun addButton(text: String, action: () -> Unit) {
            val button = Button(this).apply {
                this.text = text
                textSize = 16f
                setOnClickListener { action() }
            }
            root.addView(button, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        addText("AI 聊天助手", 28f, true)
        addText("AIChatAssistant Pro · 1.0", 14f)

        addText("运行状态", 18f, true)

        statusText = TextView(this).apply {
            text = "当前状态：已停止"
            textSize = 17f
            setTextColor(Color.rgb(40, 120, 70))
            setPadding(0, 16, 0, 20)
        }
        root.addView(statusText)

        addButton("启动") {
            statusText?.text = "当前状态：已启动（界面演示）"
        }

        addButton("停止") {
            statusText?.text = "当前状态：已停止"
        }

        addButton("紧急停止") {
            statusText?.text = "当前状态：紧急停止"
        }

        addText("AI 配置", 20f, true)

        val apiKey = EditText(this).apply {
            hint = "请输入 DeepSeek API Key"
            singleLine = true
        }
        root.addView(apiKey)

        val model = EditText(this).apply {
            hint = "模型名称，例如 deepseek-chat"
            setText("deepseek-chat")
            singleLine = true
        }
        root.addView(model)

        addButton("保存配置") {
            statusText?.text = "配置界面已就绪，安全存储功能待开发"
        }

        addText("人物设定", 20f, true)

        val persona = EditText(this).apply {
            hint = "在这里填写 AI 人物的性格、背景和聊天规则"
            minLines = 4
            gravity = Gravity.TOP
        }
        root.addView(persona)

        addButton("保存人物设定") {
            statusText?.text = "人物设定界面已就绪"
        }

        addText("版本：第一阶段界面原型", 13f)

        val scroll = ScrollView(this)
        scroll.addView(root)
        setContentView(scroll)
    }
}
