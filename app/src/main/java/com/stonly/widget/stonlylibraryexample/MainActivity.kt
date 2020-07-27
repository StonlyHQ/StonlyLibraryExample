package com.stonly.widget.stonlylibraryexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.stonly.widget.core.Stonly
import com.stonly.widget.core.config.GuideConfig
import com.stonly.widget.core.config.KnowledgeConfig
import com.stonly.widget.core.config.StonlyWidgetConfig
import com.stonly.widget.core.events.StonlyEventsListener
import com.stonly.widget.core.state.StonlyDataState
import com.stonly.widget.core.state.StonlyDataStateListener
import com.stonly.widget.ui.StonlyDialogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpListeners()
        findViewById<AppCompatTextView>(R.id.version_text).text = Stonly.version
        savedInstanceState?.let {
            return
        }
        Stonly.initialize("0041028e-a25b-11e9-a307-06e18af4fc90")
        Stonly.stateListener = object : StonlyDataStateListener {
            override fun onStateChanged(state: StonlyDataState) {
                Log.d("state", "new state $state")
            }
        }
        Stonly.eventsListener = object : StonlyEventsListener {
            override fun onEvent(mapOfTheEvent: Map<String, Any>) {
                for (item in mapOfTheEvent) {
                    Log.d("event", "key: " + item.key + " value: " + item.value)
                }
            }
        }
    }

    private fun setUpListeners() {
        findViewById<Button>(R.id.modal_btn).setOnClickListener {
            val guideConfig = GuideConfig("197")
            val stonlyDialogFragment: StonlyDialogFragment = StonlyDialogFragment.getGuideInstance(guideConfig)
            stonlyDialogFragment.showStonlyWidget(supportFragmentManager, TAG)
        }

        findViewById<Button>(R.id.fullscreen_btn).setOnClickListener {
            val guideConfig = GuideConfig("197")
            val stonlyDialogFragment = StonlyDialogFragment.getGuideInstance(guideConfig, StonlyWidgetConfig(showOnlyExpanded = true))
            stonlyDialogFragment.showStonlyWidget(supportFragmentManager, TAG)
        }

        findViewById<Button>(R.id.knowledge_btn).setOnClickListener {
            val knowledgeConfig = KnowledgeConfig("https://stonly.com/", "N4rqeKnu0u")
            val stonlyDialogFragment = StonlyDialogFragment.getKnowledgeBaseInstance(knowledgeConfig,  StonlyWidgetConfig(showOnlyExpanded = true))
            stonlyDialogFragment.showStonlyWidget(supportFragmentManager, TAG)
        }
    }

    companion object {
        const val TAG = "StonlyDialogFragment"
    }
}
