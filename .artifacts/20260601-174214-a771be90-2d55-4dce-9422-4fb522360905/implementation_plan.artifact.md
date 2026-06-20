# Implementation Plan - Expanding Deep Link Support

This plan outlines the changes to support deep links for the Home and Setting screens, in addition to the existing Product Detail deep links.

## Proposed Changes

### Navigation

#### [Routes.kt](file:///D:/Android-Java-Kotlin%20Apps/Project's/IndroydLab%20Application/app/src/main/java/com/example/indroydlab/ui/navigation/Routes.kt)

- Add `Settings` to the `Routes` sealed interface to allow navigating to the Setting screen.

```kotlin
@Serializable
sealed interface Routes: NavKey {
    // ...
    @Serializable data object Home: Routes, NavKey
    @Serializable data object Catalog: Routes, NavKey
    @Serializable data object Settings: Routes, NavKey // New route
    @Serializable data class ProductDetail(val productId: String) : Routes
}
```

#### [NavigationRoot.kt](file:///D:/Android-Java-Kotlin%20Apps/Project's/IndroydLab%20Application/app/src/main/java/com/example/indroydlab/ui/navigation/NavigationRoot.kt)

- Update `NavigationRoot` to handle the new `Routes.Settings` destination.
- Update `parseDeepLink` to recognize `/Home` and `/Setting` paths.
- Adjust `initialRoutes` logic to handle non-stackable deep links (like Home itself).

```kotlin
private fun parseDeepLink(uri: Uri?): Routes? {
    if (uri == null) return null

    val host = uri.host
    val validHosts = listOf("indroydlab.app", "indroydlab.com", "omwaghchoure15.github.io")
    if (host !in validHosts) return null

    val pathSegments = uri.pathSegments
    if (pathSegments.isEmpty()) return null

    // Check for /Home
    if (pathSegments.any { it.equals("Home", ignoreCase = true) }) {
        return Routes.Home
    }

    // Check for /Setting or /Settings
    if (pathSegments.any { it.equals("Setting", ignoreCase = true) || it.equals("Settings", ignoreCase = true) }) {
        return Routes.Settings
    }

    // Existing /catalog/product/{id} logic
    // ...
}
```

## Verification Plan

### Manual Verification
- Deploy the app to a device/emulator.
- Test deep links using `adb`:
    - `adb shell am start -W -a android.intent.action.VIEW -d "https://omwaghchoure15.github.io/Home" com.example.indroydlab`
    - `adb shell am start -W -a android.intent.action.VIEW -d "https://omwaghchoure15.github.io/Setting" com.example.indroydlab`
    - `adb shell am start -W -a android.intent.action.VIEW -d "https://omwaghchoure15.github.io/catalog/product/Wireless_Headphone" com.example.indroydlab`
- Verify that each link opens the correct screen.
- Verify that pressing back from Setting or Product Detail lands on the Home screen.
