# Student Form – Jetpack Compose

![](https://raw.githubusercontent.com/AlexMigriauli/assignment-N2/master/rr.mov)

A custom-styled Android application built with **Jetpack Compose** and **Material 3** that collects student information through a polished, non-default UI.

---

## 📋 Features

| Feature | Details |
|---|---|
| **Input Fields** | First Name, Last Name, Email (OutlinedTextField) |
| **Date Picker** | DatePickerDialog → displayed as `DD/MM/YYYY` |
| **RadioButtons** | "Your Favorite Direction" – Android / iOS / Web / Backend |
| **Switch** | "I agree to the terms and conditions" |
| **Validation** | All fields + radio + switch must be filled before submit |
| **Toast – Error** | `შეავსეთ ყველა ველი!` |
| **Toast – Success** | `მონაცემები გაიგზავნა!` |

---

## 🎨 Custom UI

- Deep Indigo / Electric Violet gradient header
- Rounded card sections with shadow elevation
- Highlighted radio rows with lavender selection state
- Mint-green Switch track on acceptance
- No default Material Design appearance

---

## 🗂 State Variable Naming

| Variable | Purpose |
|---|---|
| `firstNameState` | First name text field |
| `lastNameState` | Last name text field |
| `emailState` | Email text field |
| `dateState` | Selected date value |
| `selectedOption` | RadioButton selection |
| `isAgreed` | Switch state |

---

## 🚀 How to Run

1. Clone the repository
2. Open in **Android Studio Hedgehog** (or newer)
3. Sync Gradle
4. Run on emulator or physical device (API 26+)

---

## 📹 Demo Video

> **Place your screen recording (GIF or MP4) here.**
>
> The recording must show:
> 1. Typing your real First & Last Name into the Name field
> 2. Tapping Submit with empty fields → Error Toast
> 3. Opening the Date Picker and selecting a date
> 4. Filling all remaining fields → Success Toast

---

## 🔧 Tech Stack

- Kotlin
- Jetpack Compose + Material 3
- `DatePickerDialog` (android.app)
- `Toast` for user feedback
- Min SDK: 26 | Target SDK: 34
