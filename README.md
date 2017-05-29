- Edited from https://github.com/daimajia/AndroidImageSlider
- Change image loader from Picasso to Glide
 
## Demo
 
![](http://ww3.sinaimg.cn/mw690/610dc034jw1egzor66ojdg20950fknpe.gif)
 
## Usage

### Step 1

#### Gradle

```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

```groovy
dependencies {
    	compile 'com.github.bumptech.glide:glide:3.8.0'
    	compile 'com.nineoldandroids:library:2.4.0'
    	compile 'com.github.firdausmaulan:GlideSlider:1.0.0'
}
```

- If you have problem with duplicate entry: com/nineoldandroids/animation/Animator$AnimatorListener.class
- Just exclude nineoldandroids library

```groovy
dependencies {
    	compile 'com.github.bumptech.glide:glide:3.8.0'
    	compile ('com.github.firdausmaulan:GlideSlider:1.0.0') {
		exclude group: 'com.nineoldandroids', module: 'library'
	}
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
        android:layout_height="200dp"
/>
```        
 
There are some default indicators. If you want to use a provided indicator:
 
```java
<com.glide.slider.library.Indicators.PagerIndicator
        android:id="@+id/custom_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"
        />
```

## Thanks

- [AndroidImageSlider](https://github.com/daimajia/AndroidImageSlider)
- [Glide](https://github.com/bumptech/glide)
- [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)
