## Instalation

To start using our integration library you have to add our maven repository to your project gralde file

```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url  "https://dl.bintray.com/stonly/androidIntegration"
        }
    }
}
```

On your module gradle file you should set compile options

```
 compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
      
```

and add dependency to library

```
dependencies {
    implementation 'com.stonly:integration:1.0.2'
    ....
    }
```

## Usage

### Initialisation

To use the library you need to initialize it first with your widget id. 

```
Stonly.initialize("your widget id")
```

Initilization is a network operation that downloads your configuration, so it may take some time to be completed. To monitor the state of the initizalization you can setup a listener.

```
Stonly.stateListener = object : StonlyDataStateListener {
   override fun onStateChanged(state: StonlyDataState) {
       // here comes updates of the library state
   }
}
```

There are few possible states:
* NOT_INITIALIZED - a default state of the library without loaded configuration
* LOADING_ERROR - occurs when we couldn’t download your configuration, for example because of the lack of internet connection
* NO_PERMISSION - it may happen if you have no permission to use the library
* READY - when your configuration was successfully loaded and you have permission to use it

### Widgets

At this moment the library provides two type of widgets- a modal one called StonlyDialogFragment, and a persistent layout called StonlyWidgetView that to be used needs to be added to your layout the same as the other regular views, for example you can add it to your layout xml file:

```
<com.stonly.widget.ui.StonlyWidgetView
   android:id="@+id/stonly_view"
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   app:layout_constraintBottom_toBottomOf="parent"
   app:layout_constraintEnd_toEndOf="parent"
   app:layout_constraintStart_toStartOf="parent" />
```

Both of them are also using StonlyWidgetConfig object to pass user custom configuration of the widget. You can customize widget with fields:
- theme: Int - you can pass an android theme (it is used onyl with StonlyDialogFragment)
- peekHeight: Float - height of the collapsed state of the widget in dp
- showOnlyExpanded: Boolean - you can use it to skip the collapsed state and show the widget only in fullscreen mode

### Showing Guide

To show a guide you need to prepare the `GuideConfig` which needs a field called `containerId`. You may also specify a `stepId`, `accent`(in a hexadecimal form - for example “#FF0000”) and `language`(two letters format ISO 639-1 - for example “fr”).

You can display a guide in a modal StonlyDialogFragment:

```
val guideConfig = GuideConfig("197")
val stonlyDialogFragment = StonlyDialogFragment.getGuideInstance(guideConfig)
stonlyDialogFragment.showStonlyWidget(supportFragmentManager, TAG)
```

or in StonlyWidgetView:

```
val guideConfig = GuideConfig("197")
val stonlyView = findViewById<StonlyWidgetView>(R.id.stonly_view)
stonlyView.showGuide(guideConfig)
```

### Showing Knowledge Base

To show a knowledge base you need to prepare the `KnowledgeConfig` which needs a field called `baseUrl`. You may also specify a `containerId`, `stepId`, `accent` and `language`.

You can display a knowledge base in a modal StonlyDialogFragment:

```
val knowledgeConfig = KnowledgeConfig("https://stonly.com/", "N4rqeKnu0u")
val stonlyDialogFragment = StonlyDialogFragment.getKnowledgeBaseInstance(knowledgeConfig)
stonlyDialogFragment.showStonlyWidget(supportFragmentManager, TAG)
```

or in StonlyWidgetView:

```
val stonlyView = findViewById<StonlyWidgetView>(R.id.stonly_view)
val knowledgeConfig = KnowledgeConfig("https://stonly.com/", "N4rqeKnu0u")
stonlyView.showKnowledgeBase(knowledgeConfig)
```

### Events Listening

Library have also an event listener, you can use it like on the example below:

```
Stonly.eventsListener = object : StonlyEventsListener {
   override fun onEvent(mapOfTheEvent: Map<String, Any>) {
        // here you can consume an events
    }
}
```

Events are provided with a Map<String, Any> it may contain a lot of key value pairs and different value types
