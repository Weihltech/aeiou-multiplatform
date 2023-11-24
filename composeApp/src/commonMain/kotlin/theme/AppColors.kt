package theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


/**
 * @desc
 * """
 *
 * `fun lightColors()` 是 Jetpack Compose 中的一个函数，用于创建一个 `ColorPalette` 对象，
 * 该对象包含了 Material Design 中使用的颜色。以下是 `lightColors()` 函数中各个参数的含义：
 *
 * - `primary`: 主要颜色，用于表示应用程序的主要 UI 元素。
 * - `primaryVariant`: 主要变体颜色，用于表示应用程序的主要 UI 元素的变体。
 * - `secondary`: 次要颜色，用于表示应用程序的次要 UI 元素。
 * - `secondaryVariant`: 次要变体颜色，用于表示应用程序的次要 UI 元素的变体。
 * - `background`: 背景颜色，用于表示应用程序的背景。
 * - `surface`: 表面颜色，用于表示应用程序的表面元素。
 * - `error`: 错误颜色，用于表示应用程序中的错误状态。
 * - `onPrimary`: 主要文本颜色，用于表示应用程序中的主要文本。
 * - `onSecondary`: 次要文本颜色，用于表示应用程序中的次要文本。
 * - `onBackground`: 背景文本颜色，用于表示应用程序中的背景文本。
 * - `onSurface`: 表面文本颜色，用于表示应用程序中的表面文本。
 * - `onError`: 错误文本颜色，用于表示应用程序中的错误文本。
 *
 * """
 * @author weihl
 * @date 2023/11/24
 */

@Composable
fun appColors(): Colors {
    return MaterialTheme.colors.copy(
        primary = Color(0xFFDC9C3D),
        primaryVariant = Color(0xFFDC9C3D),
        secondary = Color(0xFFFF9800),
        secondaryVariant = Color(0xFFFF9800),
        error = Color(0xFFB00020),
    )
}

