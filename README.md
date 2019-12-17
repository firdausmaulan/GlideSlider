[![](https://jitpack.io/v/firdausmaulan/glideslider.svg)](https://jitpack.io/#firdausmaulan/glideslider)

- Edited from https://github.com/daimajia/AndroidImageSlider
- Change image loader from Picasso to Glide
 
## Demo

![](https://s2.gifyu.com/images/glide_slider.gif)
 
## Usage

### Step 1

#### Gradle

add jitpack.io

```groovy
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.5.1'
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

add GlideSlider

```groovy
dependencies {
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation "com.github.bumptech.glide:glide:4.9.0"
    annotationProcessor "com.github.bumptech.glide:compiler:4.9.0"
    
    implementation "com.github.firdausmaulan:GlideSlider:1.5.1"
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
 
```xml
<com.glide.slider.library.SliderLayout
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
```        
 
There are some default indicators. If you want to use a provided indicator:
 
```xml
<com.glide.slider.library.indicators.PagerIndicator
        android:id="@+id/custom_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"/>
```

You can customize this library via styles.xml or colors.xml

styles.xml
```xml
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

colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="glide_slider_background_color">#000000</color>
    <color name="glide_slider_indicator_color">#FFFFFF</color>
    <color name="glide_slider_description_color">#FFFFFF</color>
    <color name="glide_slider_description_background_color">#77000000</color>
</resources>
```

## Example
- [Example Project](https://github.com/firdausmaulan/GlideSlider-Example)

## Thanks

- [AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)
- [Glide](https://github.com/bumptech/glide)
- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)
- [androidsvg](https://code.google.com/archive/p/androidsvg/)
