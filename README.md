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
    implementation 'com.stonly:integration:1.0.0'
    ....
    }
```
