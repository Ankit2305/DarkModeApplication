@@ ... @@
# Dark Mode Animation

<p align="center">
  <img src="demo.gif" alt="Dark Mode Animation Demo" width="300"/>
</p>

A beautiful Android application demonstrating a smooth circular reveal animation when toggling between light and dark themes. This implementation provides a visually appealing transition that enhances user experience.

## Features

✨ **Elegant Theme Transition** - Smooth circular reveal animation when switching themes  
🌙 **Dark Mode Support** - Full implementation of Android's native dark mode  
📱 **Memory Efficient** - Optimized bitmap handling to minimize memory usage  
⚡ **Hardware Accelerated** - Utilizes hardware acceleration for smooth animations  

## How It Works

1. When the user toggles the theme icon, a screenshot of the current screen is taken
2. A transition activity with a transparent background is launched
3. The screenshot is displayed while the theme is changed in the background
4. A circular reveal animation starting from the toggle button reveals the new theme
5. Once animation completes, control returns to the main activity with the new theme applied

## Technical Implementation

The implementation uses several key techniques:

- **Bitmap Screenshots**: Captures the current UI state before theme changes
- **Porter-Duff Xfermode**: Creates the circular mask effect
- **Hardware Acceleration**: Ensures smooth animations even on complex UIs
- **Activity Transitions**: Seamless handling between activities

## Code Structure

- `TransitionUtil`: Utility class handling screenshots and theme switching
- `CircularRevealImageView`: Custom view implementing the reveal animation
- `TransitionActivity`: Transparent activity that performs the theme transition
- `MainActivity`: Demo activity with theme toggle functionality

## Requirements

- Android 5.0 (API level 21) or higher
- AndroidX dependencies

## Installation

1. Clone this repository
2. Open the project in Android Studio
3. Run the application on an emulator or physical device

## Usage

```kotlin
// Example of how to trigger the theme change animation
themeIcon.setOnClickListener {
    isDarkMode = !isDarkMode
    
    // Take a screenshot of the current state
    val rootView = window.decorView.rootView
    TransitionUtil.transitionBitmap = rootView.takeScreenshot()

    // Launch the transition with animation from the icon's center
    TransitionUtil.switchToTheme(this, isDarkMode, themeIcon.center())
}
```

## Customization

You can customize various aspects of the animation:

- **Animation Duration**: Modify the duration in the `CircularRevealImageView` class
- **Animation Center**: Change the center point to start the animation from any location
- **Interpolators**: Customize the animation timing curve by changing the interpolator

## Performance Considerations

- The screenshot bitmap is cleared from memory after use to prevent leaks
- RGB_565 bitmap format is used to reduce memory usage by 50% compared to ARGB_8888
- Hardware acceleration is enabled for the animation view

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by Material Design motion principles
- Uses AndroidX and Material Components for Android

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/amazing-feature`)
3. Commit your Changes (`git commit -m 'Add some amazing feature'`)
4. Push to the Branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request