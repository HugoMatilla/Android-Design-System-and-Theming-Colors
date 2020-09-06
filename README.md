# Intro

# 1 Color naming 
First of all we have to select our colors. As far as in Android Studio 4.0.1if you create a new project with an empty activity
The colors files looks like this.

```xml
<resources>
  <color name="colorPrimary">#6200EE</color>
  <color name="colorPrimaryDark">#3700B3</color>
  <color name="colorAccent">#03DAC5</color>
</resources>
```

As it is recommended th colors should have literal names (describe the value not how it's used). You can find how to name colors [here](), use the [Material colors guide](), use an Plugin like [this one]() or a mix of all of †hem.

  e
```xml
<resources>
  <color name="brandPink">#990066</color>
  <color name="brandPinkDark">#66003c</color>
  <color name="brandGreen">#009933</color>
</resources>
```  

Now that we have our colors set them in out theme attributes.

> You can learn about theme attributes in my previous post in the section [Theme attrs in less than 100 words.]
  

# 2 Color theme attributes

An image is worth a 1000 words. So lets get that image.

![color_attributes](imgs/color_attributes)

Here we can see th 12 attributes that the Material Design Library offer us.

```
colorPrimary
colorPrimaryVariant
colorOnPrimary

colorSecondary
colorSecondaryVariant
colorOnSecondary

android:colorBackground
colorOnBackground

colorSurface
colorOnSurface

colorError
colorOnError

```

As you can see the colors attributes are divided in some colors like `colorPrimary`, and in how should the background of that color be like `colorOnPrimary`.

This is very helpfull because we have now one place where we define our colors and also how the foreground of that color is, so we know that there wont be visibility problems, like if a dark colored button has dark colored text.
There is a relation btween primary/varinat and/ on. If you change one check the others 
`Despite being separate attributes, there’s an inherent link between a color, its variant (if one exists), and its “On” color (eg. colorPrimary, colorPrimaryVariant and colorOnPrimary). Overriding one means you need to check the others to see if they make sense and meet accessibility requirements.`


# 3 Update your theme with your colors

First of all and form Androdis Studio 4.0.1 the Empty Activity wizard creates  this style
```xml
<resources>
  <!-- Base application theme. -->
  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
  </style>

</resources>
```

Lets clen this up.

First, make the parent to inherit from a MD theme aike `Theme.MaterialComponents.DayNight.NoActionBar` For that first get the implementation `com.google.android.material:material:1.2.1` library.

Second change the name to reflect that it is a theme. 
> About naming Themes with Theme prefix Widgets with Widge prefix


We can keep most of th 'stand colrs the same and jsut focus on the one that give our app it uniqueness. primary , secondary and its variantes.

`values/themes.xml`
```xml
  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!-- Customize your theme here. -->
    <item name="colorPrimary">@color/brandPink</item>
    <item name="colorPrimaryVariant">@color/brandPinkDark</item>
    <item name="colorSecondary">@color/brandGreen</item>
    <item name="colorSecondaryVariant">@color/brandGreenDark</item>
  </style>
    
```

Finally and most important move it to a themes.xml file.

> Explain files and files names
>

# 6 But 12 attributes are not enough for my app
One of the power of theming is the avility to change the full UI styles easily. You can have 2 themes for for example normal and premium users.
The primary color is different for both, just creating a  new theme with the differnt primary color will be enough and and just chaning the theme for each ot the user types will be sufficient.

But image that our design team does not want that the FAB buttons color change. In that case and becasue FAB buttons use color primary attribute to tint it we need a solution.

In that case is very easy, we can define a custom theme attribute that will be used to tint the fab buttons, instead XXX attribute.

```xml
<!-- In res/values/attrs.xml -->
<attr name="fabColor" format="color" />

<!-- In res/values/themes.xml -->
<style name="Theme.App" parent="Theme.MaterialComponents.*">
    <item name="fabColor">?:attr/secondary</item>
</style>

<style name="Theme.App.Premium" parent="Theme.MaterialComponents.*">
    <item name="fabColor">@color/orangePremium</item>
</style>
``` 

> Check it in the companion app

# Extra points
## Colors with alpha
Instead of 
`<color name=”navy_700_alpha_60”>#9937596D</color>`
With this solution we are breakig the power of theming. If we change our color primary to stop being `navy_700` `#993759` we will need to rewrite this.
Or for the premium theme.

Instead use a color sate list that reference the primary colors like:

`res/color/primary_60.xml`
```xml
<selector>
    <item android:alpha="0.6" android:color="?attr/colorPrimary" />
</selector>
```

Becasue we ar epointing to the theme attribute for future changes or in app theme change normal to premium we wont need to change nothing. 

## Theme overlays
Already mentioned in my prevous post, but because is so powerful lets explain it again.
**What are deafault styles?**
>When applying theme overlays in XML, there are two options to consider:
android:theme: Works with all widgets, doesn’t work in default styles
app:materialThemeOverlay: Only works with MDC widgets, works in default styles`


# MDC Widgtes : Other post
## Custom Widgets

# DarkMode

# Use a Base Theme

#NOTES
`colorPrimaryDark` is neccesary to tint the status bar