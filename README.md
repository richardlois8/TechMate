# Techmate 📱

TechMate is a gadget recommendation app that helps users find gadgets that suit their needs. This app uses technology to search for gadgets that best match the user's input based on the 3rd and 4th quarters of 2022. That way, users can choose gadgets that suit their needs and budget more effectively and efficiently.

## 📋 Requirement
- Minimum Android App SDK 24
- PC / Laptop:
  - Processor: Intel Core i3 (Recommended Intel Core i5 above).
  - RAM: 8 GB or more.
  - Screen resolution: 1280 x 800 (Full HD 1920 x 1080 recommended).

## 📚 Libraries Used

| Library | Description | Implementation |
| --- | --- | --- |
| androidx.core:core-ktx | Kotlin Extensions for Android | `implementation 'androidx.core:core-ktx:1.7.0'` |
| androidx.appcompat:appcompat | Backwards-compatible recent features | `implementation 'androidx.appcompat:appcompat:1.6.1'` |
| com.google.android.material:material | Material Design Components | `implementation 'com.google.android.material:material:1.9.0'` |
| androidx.constraintlayout:constraintlayout | Constraint Layout | `implementation 'androidx.constraintlayout:constraintlayout:2.1.4'` |
| androidx.navigation:navigation-fragment | Navigation Component | `implementation 'androidx.navigation:navigation-fragment:2.5.3'` |
| junit:junit | Testing Framework | `testImplementation 'junit:junit:4.13.2'` |
| androidx.test.ext:junit | Android Testing Support Library | `androidTestImplementation 'androidx.test.ext:junit:1.1.5'` |
| androidx.test.espresso:espresso-core | UI Testing Framework | `androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'` |
| de.hdodenhof:circleimageview | Circular ImageView | `implementation 'de.hdodenhof:circleimageview:3.1.0'` |
| androidx.room:room-runtime | Room Persistence Library | `implementation "androidx.room:room-runtime:2.5.1"` |
| androidx.room:room-compiler | Room Compiler | `kapt "androidx.room:room-compiler:2.5.1"` |
| androidx.room:room-ktx | Kotlin Extensions for Room | `implementation "androidx.room:room-ktx:2.5.1"` |
| androidx.lifecycle:lifecycle-viewmodel-ktx | ViewModel | `implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"` |
| androidx.lifecycle:lifecycle-livedata-ktx | LiveData | `implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1"` |
| androidx.lifecycle:lifecycle-common-java8 | Lifecycle components | `implementation "androidx.lifecycle:lifecycle-common-java8:2.6.1"` |
| com.github.bumptech.glide:glide | Image Loading and Caching library | `implementation 'com.github.bumptech.glide:glide:4.14.2'` |
| com.github.bumptech.glide:compiler | Glide compiler | `kapt 'com.github.bumptech.glide:compiler:4.14.2'` |
| com.tbuonomo:dotsindicator | Dots Indicator | `implementation "com.tbuonomo:dotsindicator:4.3"` |

## 📖 How to Use

1. **Install the App** 📲: Install the TechMate app from the Google Play Store on your Android device. Make sure your device supports Android SDK 24 or higher.

2. **Launch the App** 🚀: Open the TechMate app. You'll be greeted with a clean and simple user interface.

3. **Enter Your Preferences** 🕹️: In the search section, enter your preferences. You can input your preferred brand, memory, RAM, price, and any specific features you're looking for in a gadget.

4. **Get Recommendations** 📝: After entering your preferences, tap on the 'Find Recommendation' button. The app will process your input and display a list of gadgets that best match your needs.

5. **Explore Details** 🔍: You can tap on any gadget from the list to view more detailed information about it.

Remember, TechMate is designed to make the gadget selection process as easy as possible. The more precise your input, the better the recommendations!

## 💌 Feedback
We'd love to hear your thoughts on the app. If you have any suggestions or run into any issues, feel free to raise an issue in the Github repository or contact us directly.
