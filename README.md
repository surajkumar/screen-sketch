# Screen Sketch
A desktop application written in Java to allow you to draw all over your entire screen.

This program allows you to draw shapes or add text straight onto your desktop. Similar to the [ScreenBrush](https://apps.apple.com/us/app/screenbrush/id1233965871?mt=12) application
for macOS. There are no free alternatives that support the Windows operating system making this a great tool for those
who want to draw on their screens.

## Key Benefits
* A powerful way for quick on screen illustrations when demonstrating presentations or doing walk through.
* Get real-time interaction and engagement with your audience as you unfold ideas visually to the screen.
* Simple to use user interface without any complicated setup or learning curve.

## Features
* Drawing shapes such as Squares, Rectangles, Circles and arrows to the screen
* Free-hand drawing capabilities
* Colour picker to customize each drawn element
* Export your drawings as screenshots

## Development
### Pre-Requisites
#### Hooks
**Important**: After cloning the repository, run the following command to install the necessary pre-commit hooks:
```
pre-commit install
```
If you don't have pre-commit installed, refer to the [pre-commit website](https://pre-commit.com/) for installation instructions.

#### Environment
Ensure you have the following tools installed:
* Java 21 or higher.
* Gradle 8.5 or higher (Alternatively, prefer to use the included Gradle wrapper)

#### Detect-Secrets
This repository utilizes [Detect-Secrets](https://github.com/Yelp/detect-secrets) to prevent the inadvertent inclusion of secrets like API keys or passwords in commits. To create a baseline of potential secrets currently in the repository, run:
```
detect-secrets scan > .secrets.baseline
```
If you don't have Detect-Secrets installed, follow the instructions on their [Github README](https://github.com/Yelp/detect-secrets?tab=readme-ov-file#installation).
