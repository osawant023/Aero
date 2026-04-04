package com.app.aero.core.ui.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.aero.core.ui.theme.AeroTheme
import com.app.aero.core.ui.theme.LocalSpacing
import kotlinx.coroutines.delay

/* ---------------------------------------------------
   🔵 PRIMARY BUTTON (Gradient CTA)
--------------------------------------------------- */

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

/* ---------------------------------------------------
   ⚪ SECONDARY / GHOST BUTTON
--------------------------------------------------- */

@Composable
fun AppGhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

/* ---------------------------------------------------
   🟢 STATUS BUTTON (Buy / Sell)
--------------------------------------------------- */

@Composable
fun AppButton(
    text: String,
    positive: Boolean,
    modifier: Modifier = Modifier
) {
    val container = if (positive)
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
    else
        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f)

    val contentColor = if (positive)
        MaterialTheme.colorScheme.secondary
    else
        MaterialTheme.colorScheme.tertiary

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(container)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = contentColor)
    }
}

/* ---------------------------------------------------
   📦 CARD (Tonal Layering - No Shadow)
--------------------------------------------------- */

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 0.dp
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}

/* ---------------------------------------------------
   📋 LIST ITEM (No Divider)
--------------------------------------------------- */

@Composable
fun AppListItem(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val containerColor = MaterialTheme.colorScheme.surface

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor)
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface
        )

        subtitle?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/* ---------------------------------------------------
   🔍 TEXT FIELD (Ghost Border)
--------------------------------------------------- */

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                placeholder,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.small
    )
}

/* ---------------------------------------------------
   🏷️ CHIP (Pill)
--------------------------------------------------- */

@Composable
fun AppChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/* ---------------------------------------------------
   📊 DATA STRIP (Ticker Style)
--------------------------------------------------- */

@Composable
fun AppDataStrip(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Column {
        // Top accent line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = content
        )
    }
}

/* ---------------------------------------------------
   🔘 ICON BUTTON (Minimal)
--------------------------------------------------- */

@Composable
fun AppIconButton(
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    selected: Boolean = false
) {
    val bg = if (selected)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
    else Color.Transparent

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(bg)
            .clickable { onClick() }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

/* ---------------------------------------------------
   🟢🔴 CONNECTION STATUS
   Top bar dot + label (Connected / Disconnected)
--------------------------------------------------- */

@Composable
fun AppConnectionStatus(
    connected: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (connected)
        MaterialTheme.colorScheme.secondary
    else
        MaterialTheme.colorScheme.tertiary

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = if (connected) "Connected" else "Disconnected",
            color = color,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

/* ---------------------------------------------------
   📈 PRICE ROW
   Feed screen list row — flashes green/red for 1s on update
--------------------------------------------------- */

@Composable
fun AppPriceRow(
    symbol: String,
    exchange: String,
    price: String,
    change: String,
    isPositive: Boolean,
    flashKey: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val positiveColor = MaterialTheme.colorScheme.secondary
    val negativeColor = MaterialTheme.colorScheme.tertiary
    val priceColor    = if (isPositive) positiveColor else negativeColor

    var flashing by remember { mutableStateOf(false) }
    LaunchedEffect(flashKey) {
        if (flashKey > 0) {
            flashing = true
            delay(1000)
            flashing = false
        }
    }

    val flashBg = if (isPositive)
        MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
    else
        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)

    val bgColor by animateColorAsState(
        targetValue = if (flashing) flashBg else Color.Transparent,
        animationSpec = tween(400),
        label = "flashBg"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = symbol,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Text(
                text = exchange,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = price,
                color = priceColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Text(
                text = change,
                color = priceColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/* ---------------------------------------------------
   💰 PRICE DISPLAY
   Details screen hero — large price + change inline
--------------------------------------------------- */

@Composable
fun AppPriceDisplay(
    price: String,
    change: String,
    isPositive: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (isPositive)
        MaterialTheme.colorScheme.secondary
    else
        MaterialTheme.colorScheme.tertiary

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = price,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Text(
            text = change,
            color = color,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.padding(bottom = 3.dp)
        )
    }
}

/* ---------------------------------------------------
   🏢 SYMBOL HEADER
   Details screen — symbol, exchange badge, company name
--------------------------------------------------- */

@Composable
fun AppSymbolHeader(
    symbol: String,
    exchange: String,
    companyName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = symbol,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = exchange,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        Text(
            text = companyName,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/* ---------------------------------------------------
   🔲 SECTION HEADER
   "ABOUT NVIDIA CORPORATION", "MARKET DEPTH" etc.
--------------------------------------------------- */

@Composable
fun AppSectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title.uppercase(),
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.labelSmall,
        letterSpacing = 1.sp,
        modifier = modifier
    )
}

// ─────────────────────────────────────────────────────────────────────────────
//  PREVIEW
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun DesignSystemPreview() {
    AeroTheme {
        val spacing = LocalSpacing.current

        var text by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(spacing.lg.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.lg.dp)
        ) {

            /* -------------------------------
               🔤 TYPOGRAPHY
            -------------------------------- */

            AppCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Display", style = MaterialTheme.typography.displayLarge)
                    Text("Headline", style = MaterialTheme.typography.headlineMedium)
                    Text("Body text example", style = MaterialTheme.typography.bodyMedium)
                    Text("Label small", style = MaterialTheme.typography.labelSmall)
                }
            }

            /* -------------------------------
               🔘 BUTTONS
            -------------------------------- */

            AppCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    AppPrimaryButton(
                        text = "Primary Action",
                        onClick = {}
                    )

                    AppGhostButton(
                        text = "Ghost Button",
                        onClick = {}
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AppButton("Buy", positive = true)
                        AppButton("Sell", positive = false)
                    }
                }
            }

            /* -------------------------------
               🔍 INPUT
            -------------------------------- */

            AppCard {
                AppTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = "Search..."
                )
            }

            /* -------------------------------
               🏷️ CHIP
            -------------------------------- */

            AppCard {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppChip("Active")
                    AppChip("Filter")
                }
            }

            /* -------------------------------
               📋 LIST
            -------------------------------- */

            AppCard {
                Column {
                    AppListItem("Item One", "Secondary text")
                    AppListItem("Item Two", "More info")
                    AppListItem("Item Three")
                }
            }

            /* -------------------------------
               📊 DATA STRIP
            -------------------------------- */

            AppDataStrip {
                Text("AAPL", color = MaterialTheme.colorScheme.onSurface)
                Text("+1.25%", color = MaterialTheme.colorScheme.secondary)
                Text("145.32", color = MaterialTheme.colorScheme.onSurface)
            }

            /* -------------------------------
               🔘 ICON BUTTONS
            -------------------------------- */

            AppCard {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppIconButton(
                        icon = { Text("🏠") },
                        onClick = {},
                        selected = true
                    )
                    AppIconButton(
                        icon = { Text("🔍") },
                        onClick = {}
                    )
                    AppIconButton(
                        icon = { Text("👤") },
                        onClick = {}
                    )
                }
            }

            /* -------------------------------
               🟢🔴 CONNECTION STATUS
            -------------------------------- */

            AppCard {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AppConnectionStatus(connected = true)
                    AppConnectionStatus(connected = false)
                }
            }

            /* -------------------------------
               📈 PRICE ROW
            -------------------------------- */

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column {
                    AppPriceRow(
                        symbol = "NVDA", exchange = "NASDAQ",
                        price = "882.12", change = "+14.22 (1.64%)",
                        isPositive = true, flashKey = 0, onClick = {}
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    AppPriceRow(
                        symbol = "TSLA", exchange = "NASDAQ",
                        price = "163.57", change = "-4.12 (-2.46%)",
                        isPositive = false, flashKey = 0, onClick = {}
                    )
                }
            }

            /* -------------------------------
               💰 PRICE DISPLAY + SYMBOL HEADER
            -------------------------------- */

            AppCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppSymbolHeader(
                        symbol = "NVDA",
                        exchange = "NASDAQ",
                        companyName = "NVIDIA Corporation"
                    )
                    AppPriceDisplay(
                        price = "135.58",
                        change = "+4.48 (3.42%)",
                        isPositive = true
                    )
                }
            }

            /* -------------------------------
               🔲 SECTION HEADERS
            -------------------------------- */

            AppCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppSectionHeader("About NVIDIA Corporation")
                    AppSectionHeader("Market Depth")
                }
            }
        }
    }
}

@Preview(name = "Light", showSystemUi = true, showBackground = true)
@Composable fun PreviewLight() { DesignSystemPreview() }

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable fun PreviewDark() { DesignSystemPreview() }
