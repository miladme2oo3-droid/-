package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterCenterFocus
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.ShahnamehRepository
import com.example.model.CharacterNode
import com.example.ui.theme.GoldGlow
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.PurpleGlow
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.SurfaceVariant
import com.example.ui.theme.Tertiary
import com.example.ui.theme.TertiaryContainer
import kotlinx.coroutines.launch

@Composable
fun GenealogyScreen(
    selectedCharacter: CharacterNode,
    isDossierExpanded: Boolean,
    onSelectCharacter: (CharacterNode) -> Unit,
    onToggleDossier: () -> Unit,
    onOpenHaftKhan: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    var zoomScale by remember { mutableFloatStateOf(1.0f) }

    // Pulsing aura animation for Rostam
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val auraScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "auraScale"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Decorative background geometric pattern
        Canvas(modifier = Modifier.fillMaxSize()) {
            val gridSpacing = 80.dp.toPx()
            for (x in 0..(size.width / gridSpacing).toInt() + 1) {
                for (y in 0..(size.height / gridSpacing).toInt() + 1) {
                    drawCircle(
                        color = Color(0xFF2C263F).copy(alpha = 0.25f),
                        radius = 1.5f,
                        center = Offset(x * gridSpacing, y * gridSpacing)
                    )
                }
            }
        }

        // Main Vertical Tree Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = if (isDossierExpanded) 340.dp else 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dynastic Era Sub-Header Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = SurfaceContainerLowest.copy(alpha = 0.75f)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Secondary)
                            )
                            Text(
                                text = "تبارشناسی حماسی ایران",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = Secondary,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(TertiaryContainer.copy(alpha = 0.4f))
                                .padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Explore,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "نمای عمودی پویا",
                                style = MaterialTheme.typography.labelSmall.copy(color = Tertiary)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Era Selector Floating Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(SurfaceContainerHigh)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "👑 پیشدادیان",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(PrimaryContainer, TertiaryContainer, PrimaryContainer)
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Secondary)
                                )
                                Text(
                                    text = "کیانیان و خاندان سام",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Secondary)
                                        .padding(horizontal = 4.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "فعال",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(SurfaceContainerHigh)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "⚔️ تبار پهلوانان سیستان",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tree Area with Zoom scale
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(zoomScale)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // Curved Connector Canvas behind the nodes
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(680.dp)
                ) {
                    val w = size.width
                    val centerX = w / 2

                    // Line from Level 1 (Kayumars & Jamshid) to Fereydun
                    val dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    drawLine(
                        color = Tertiary.copy(alpha = 0.5f),
                        start = Offset(centerX - 70.dp.toPx(), 45.dp.toPx()),
                        end = Offset(centerX, 110.dp.toPx()),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = dashedEffect
                    )
                    drawLine(
                        color = Tertiary.copy(alpha = 0.5f),
                        start = Offset(centerX + 70.dp.toPx(), 45.dp.toPx()),
                        end = Offset(centerX, 110.dp.toPx()),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = dashedEffect
                    )

                    // Fereydun -> Sam Nariman
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Secondary, Primary)),
                        start = Offset(centerX, 165.dp.toPx()),
                        end = Offset(centerX, 215.dp.toPx()),
                        strokeWidth = 2.5.dp.toPx()
                    )

                    // Sam -> Zal
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Primary, Secondary)),
                        start = Offset(centerX, 275.dp.toPx()),
                        end = Offset(centerX - 40.dp.toPx(), 330.dp.toPx()),
                        strokeWidth = 2.5.dp.toPx()
                    )

                    // Cross link Sam / Kayanian to Siyavash
                    drawLine(
                        color = Tertiary.copy(alpha = 0.4f),
                        start = Offset(centerX, 275.dp.toPx()),
                        end = Offset(centerX + 90.dp.toPx(), 330.dp.toPx()),
                        strokeWidth = 1.5.dp.toPx(),
                        pathEffect = dashedEffect
                    )

                    // Zal -> Rostam (Divine radiant trunk line)
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Secondary, SecondaryContainer)),
                        start = Offset(centerX - 40.dp.toPx(), 395.dp.toPx()),
                        end = Offset(centerX, 455.dp.toPx()),
                        strokeWidth = 3.dp.toPx()
                    )

                    // Rostam -> Descendants (Sohrab on left, Faramarz center, Kay Khosrow right)
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Secondary, Tertiary)),
                        start = Offset(centerX, 545.dp.toPx()),
                        end = Offset(centerX - 100.dp.toPx(), 595.dp.toPx()),
                        strokeWidth = 2.dp.toPx()
                    )
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Secondary, Primary)),
                        start = Offset(centerX, 545.dp.toPx()),
                        end = Offset(centerX, 595.dp.toPx()),
                        strokeWidth = 2.dp.toPx()
                    )
                    drawLine(
                        brush = Brush.verticalGradient(listOf(Secondary, Tertiary)),
                        start = Offset(centerX, 545.dp.toPx()),
                        end = Offset(centerX + 100.dp.toPx(), 595.dp.toPx()),
                        strokeWidth = 2.dp.toPx()
                    )
                }

                // Vertical Tree Nodes
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // LEVEL 1: Kayumars & Jamshid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val kayumars = ShahnamehRepository.characters.find { it.id == "kayumars" }
                        val jamshid = ShahnamehRepository.characters.find { it.id == "jamshid" }

                        kayumars?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 54,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }

                        Spacer(modifier = Modifier.width(60.dp))

                        jamshid?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 54,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // LEVEL 2: Fereydun
                    val fereydun = ShahnamehRepository.characters.find { it.id == "fereydun" }
                    fereydun?.let {
                        TreeNodeItem(
                            character = it,
                            isSelected = selectedCharacter.id == it.id,
                            sizeDp = 60,
                            onSelect = { onSelectCharacter(it) }
                        )
                    }

                    Spacer(modifier = Modifier.height(26.dp))

                    // LEVEL 3: Sam Nariman
                    val sam = ShahnamehRepository.characters.find { it.id == "sam" }
                    sam?.let {
                        TreeNodeItem(
                            character = it,
                            isSelected = selectedCharacter.id == it.id,
                            sizeDp = 58,
                            onSelect = { onSelectCharacter(it) }
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // LEVEL 4: Zal Zar & Siyavash
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val zal = ShahnamehRepository.characters.find { it.id == "zal" }
                        val siyavash = ShahnamehRepository.characters.find { it.id == "siyavash" }

                        zal?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 62,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }

                        Spacer(modifier = Modifier.width(70.dp))

                        siyavash?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 54,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // LEVEL 5: Apex Hero - ROSTAM DASTAN
                    val rostam = ShahnamehRepository.characters.find { it.id == "rostam" }
                    rostam?.let {
                        Box(
                            modifier = Modifier
                                .clickable { onSelectCharacter(it) }
                                .testTag("node_rostam"),
                            contentAlignment = Alignment.Center
                        ) {
                            // Radiant halo aura
                            Box(
                                modifier = Modifier
                                    .size(108.dp)
                                    .scale(auraScale)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.radialGradient(
                                            listOf(GoldGlow, PurpleGlow, Color.Transparent)
                                        )
                                    )
                            )

                            // Main circular portrait frame
                            Box(
                                modifier = Modifier
                                    .size(86.dp)
                                    .clip(CircleShape)
                                    .border(
                                        2.5.dp,
                                        Brush.sweepGradient(listOf(Secondary, Primary, Secondary)),
                                        CircleShape
                                    )
                                    .background(SurfaceContainerLowest),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = it.imageUrl,
                                    contentDescription = it.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                )

                                // Live Selected Status Pill
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter)
                                        .offset(y = (-10).dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Secondary)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "شخصیت برگزیده",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp
                                        )
                                    )
                                }

                                // Bottom Token
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .offset(y = 8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(PrimaryContainer)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "🛡️ جهان‌پهلوان",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            fontSize = 8.sp
                                        )
                                    )
                                }
                            }
                        }

                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Secondary,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = "Rostam — The Peerless Champion",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // LEVEL 6: Descendants (Sohrab, Faramarz, Kay Khosrow)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val sohrab = ShahnamehRepository.characters.find { it.id == "sohrab" }
                        val faramarz = ShahnamehRepository.characters.find { it.id == "faramarz" }
                        val khosrow = ShahnamehRepository.characters.find { it.id == "kaykhosrow" }

                        sohrab?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 52,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }

                        faramarz?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 52,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }

                        khosrow?.let {
                            TreeNodeItem(
                                character = it,
                                isSelected = selectedCharacter.id == it.id,
                                sizeDp = 52,
                                onSelect = { onSelectCharacter(it) }
                            )
                        }
                    }
                }
            }
        }

        // Floating Tree Canvas Controls (Zoom In, Zoom Out, Center Focus)
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 110.dp, end = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = SurfaceContainerLowest.copy(alpha = 0.88f),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { zoomScale = (zoomScale + 0.15f).coerceAtMost(1.5f) },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "بزرگ‌نمایی",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = { zoomScale = (zoomScale - 0.15f).coerceAtLeast(0.7f) },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "کوچک‌نمایی",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            zoomScale = 1.0f
                            scope.launch { scrollState.animateScrollTo(600) }
                        },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterCenterFocus,
                            contentDescription = "تمرکز روی رستم",
                            tint = Secondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Interactive Character Dossier Bottom Sheet
        CharacterDossierSheet(
            character = selectedCharacter,
            isExpanded = isDossierExpanded,
            onToggleExpand = onToggleDossier,
            onOpenBattles = onOpenHaftKhan,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun TreeNodeItem(
    character: CharacterNode,
    isSelected: Boolean,
    sizeDp: Int,
    onSelect: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onSelect() }
            .testTag("node_${character.id}")
    ) {
        Box(
            modifier = Modifier
                .size(sizeDp.dp)
                .clip(CircleShape)
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) Secondary else Primary.copy(alpha = 0.5f),
                    shape = CircleShape
                )
                .background(SurfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )

            if (character.badgeIcon.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(PrimaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = character.badgeIcon, fontSize = 10.sp)
                }
            }
        }

        Text(
            text = character.name,
            style = MaterialTheme.typography.labelSmall.copy(
                color = if (isSelected) Secondary else MaterialTheme.colorScheme.onSurface,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 11.sp
            ),
            modifier = Modifier.padding(top = 3.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = character.englishName,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 8.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CharacterDossierSheet(
    character: CharacterNode,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onOpenBattles: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("character_dossier_sheet"),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = SurfaceContainerLowest.copy(alpha = 0.96f),
        shadowElevation = 24.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            // Gold Tazhib top line accent
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color.Transparent, Secondary, Color.Transparent)
                        )
                    )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Sheet Drag Handle & Title Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(Secondary)
                    )
                    Text(
                        text = "شناسنامه جهان‌پهلوان",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Secondary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(SurfaceVariant)
                )

                IconButton(
                    onClick = onToggleExpand,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandMore else Icons.Default.ExpandLess,
                        contentDescription = "بستن پنل",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Dossier Header: Portrait and Titles
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.5.dp, Secondary, RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = character.imageUrl,
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = character.title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Secondary,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = character.dynasty,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Bio text
                    Text(
                        text = character.biography,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                            lineHeight = 18.sp,
                            fontSize = 11.sp
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Kinship & Family Relations 2x2 Grid
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(SurfaceContainer.copy(alpha = 0.6f))
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "🪶 پدر: ${character.father.ifEmpty { "سام نریمان" }}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 10.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "💍 همسر: ${character.spouse.ifEmpty { "تهمینه" }}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 10.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "🌸 مادر: ${character.mother.ifEmpty { "رودابه" }}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 10.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "⚔️ فرزندان: ${character.children.ifEmpty { "سهراب، فرامرز" }}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 10.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Timeline Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val chips = if (character.timelineHighlights.isNotEmpty()) {
                            character.timelineHighlights
                        } else {
                            listOf("هفت‌خوان رستم", "نبرد با دیو سپید", "تراژدی سهراب", "رزم اسفندیار")
                        }
                        chips.forEach { item ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(SurfaceContainerHigh)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary,
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Action CTAs (نبردها, دلدادگی, سرنوشت)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onOpenBattles,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryContainer
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                                .testTag("btn_battles")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SportsMartialArts,
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp),
                                    tint = Secondary
                                )
                                Text(
                                    text = "نبردها",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SurfaceContainerHigh
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp),
                                    tint = Secondary
                                )
                                Text(
                                    text = "دلدادگی",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                        }

                        Button(
                            onClick = onOpenBattles,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SecondaryContainer
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(38.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoStories,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp),
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                                Text(
                                    text = "سرنوشت",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
