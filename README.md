- Edited from https://github.com/daimajia/AndroidImageSlider
- Change image loader from Picasso to Glide
 
## Demo

![](https://s2.gifyu.com/images/glide_slider.gif)
 
## Usage

### Step 1

#### Gradle

```groovy
buildscript {

    ext.compile_sdk_version = 28
    ext.min_sdk_version = 14
    ext.target_sdk_version = 28
    ext.support_version = '28.0.0'
    ext.glide_version = '4.8.0'
    ext.glide_slider_version = '1.3.2'
	
	// use this config if you want to load svg
	//ext.glide_slider_version = '1.3.1'
	//ext.androidsvg_version = '1.2.1'

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
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

You can customize this library via style.xml or color.xml

style.xml
```java
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">

    <style name="GlideSliderCardStyle" parent="Base.CardView">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
    </style>

    <style name="GlideSliderBackgroundStyle">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:background">@color/glide_slider_background_color</item>
    </style>

    <style name="GlideSliderImageStyle">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
    </style>

    <style name="GlideSliderLoadingStyle" parent="Base.Widget.AppCompat.ProgressBar">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_centerInParent">true</item>
    </style>

    <style name="GlideSliderDescriptionBackgroundStyle">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_alignParentBottom">true</item>
        <item name="android:background">@color/glide_slider_description_background_color</item>
        <item name="android:gravity">center_vertical</item>
        <item name="android:minHeight">30dp</item>
        <item name="android:orientation">vertical</item>
        <item name="android:paddingLeft">10dp</item>
        <item name="android:paddingRight">10dp</item>
    </style>

    <style name="GlideSliderDescriptionTextStyle" parent="android:Widget.TextView">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:textColor">@color/glide_slider_description_color</item>
        <item name="android:fontFamily" tools:targetApi="jelly_bean">sans-serif</item>
    </style>
</resources>
```

color.xml
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="glide_slider_background_color">#000000</color>
    <color name="glide_slider_indicator_color">#FFFFFF</color>
    <color name="glide_slider_description_color">#FFFFFF</color>
    <color name="glide_slider_description_background_color">#77000000</color>
</resources>
```

## Example
- [version 1.3.1]https://github.com/firdausmaulan/GlideSlider-Example/tree/feature/1.3.1)
- [version 1.3.2]https://github.com/firdausmaulan/GlideSlider-Example/tree/feature/1.3.2)
- [version 1.4.0]https://github.com/firdausmaulan/GlideSlider-Example/tree/feature/1.4.0)

## Thanks

- [AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)
- [Glide](https://github.com/bumptech/glide)
- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)
- [androidsvg](https://code.google.com/archive/p/androidsvg/)
