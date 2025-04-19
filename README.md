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

