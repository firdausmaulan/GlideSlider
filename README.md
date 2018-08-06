- Edited from https://github.com/daimajia/AndroidImageSlider
- Change image loader from Picasso to Glide
 
## Demo
 
![](http://ww3.sinaimg.cn/mw690/610dc034jw1egzor66ojdg20950fknpe.gif)
 
## Usage

### Step 1

#### Gradle

```groovy
buildscript {

    ext.support_version = '27.1.1'
    ext.glide_version = '4.7.1'
    ext.glide_slider_version = '1.3.2'
	
	// use this config if you want to load svg
	//ext.glide_slider_version = '1.3.1'
	//ext.androidsvg_version = '1.2.1'

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.1.3'
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url "https://jitpack.io"
        }
    }
}
```

```groovy
dependencies {
    implementation "com.github.firdausmaulan:GlideSlider:$glide_slider_version"
}
```

### Step 2

Add permissions (if necessary) to your `AndroidManifest.xml`

```xml
<!-- if you want to load images from the internet -->
<uses-permission android:name="android.permission.INTERNET" /> 

<!-- if you want to load images from a file OR from the internet -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

**Note:** If you want to load images from the internet, you need both the `INTERNET` and `READ_EXTERNAL_STORAGE` permissions to allow files from the internet to be cached into local storage.

If you want to load images from drawable, then no additional permissions are necessary.

### Step 3

Add the Slider to your layout:
 
```java
<com.glide.slider.library.SliderLayout
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
```        
 
There are some default indicators. If you want to use a provided indicator:
 
```java
<com.glide.slider.library.Indicators.PagerIndicator
        android:id="@+id/custom_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"/>
```

```java
Add <color name="glide_slider_indicator_color">#yourhex</color> to change indicator color.
```

## Example
- [version 1.3.1]https://github.com/firdausmaulan/GlideSlider-Example/tree/feature/1.3.1)
- [version 1.3.2]https://github.com/firdausmaulan/GlideSlider-Example/tree/feature/1.3.2)

## Thanks

- [AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)
- [Glide](https://github.com/bumptech/glide)
- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)
- [androidsvg](https://code.google.com/archive/p/androidsvg/)
