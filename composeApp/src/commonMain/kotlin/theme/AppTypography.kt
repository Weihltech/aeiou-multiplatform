package theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun appTypography(): Typography {
    return MaterialTheme.typography.apply {
        copy(button = button.copy(fontWeight = FontWeight.Normal))
    }
}