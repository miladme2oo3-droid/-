package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Fort
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.SocialDistance
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.WbSunny
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.ShahnamehRepository
import com.example.model.MythicWaypoint
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.Tertiary
import com.example.ui.theme.TertiaryContainer

@Composable
fun MythicMapScreen(
    selectedWaypoint: MythicWaypoint,
    onSelectWaypoint: (MythicWaypoint) -> Unit,
    onOpenBattleSim: () -> Unit,
    onOpenGenealogy: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("همه قلمروها") }
    var zoomScale by remember { mutableFloatStateOf(1.0f) }

    val filterList = listOf(
        "همه قلمروها",
        "مسیر هفت‌خوان رستم",
        "البرزکوه و دماوند",
        "مرزهای توران و آموی",
        "زابلستان و نیمروز"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "pulsePin")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.dp)
        ) {
            // Header Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
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
                            text = "جغرافیای حماسی شاهنامه • Mythic Cartography",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Secondary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Flare,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = "سال ۲۸۵ کیانی",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                }

                Text(
                    text = "نقشه اساطیری ایران‌زمین و توران",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "کاوش در جغرافیای اساطیری و خط سیر گذرگاه‌های هفت‌خوان رستم دستان از زابلستان تا غارهای دیوان مازندران.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                )

                // Territories Horizon Filter Badges
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filterList.forEach { filter ->
                        val isSelected = filter == selectedFilter
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) Secondary else SurfaceContainerHigh)
                                .clickable { selectedFilter = filter }
                                .padding(horizontal = 14.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text = filter,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            // Interactive Map Canvas Portlet
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(SurfaceContainerLowest)
                    .aspectRatio(4f / 3f)
                    .testTag("mythic_map_canvas")
            ) {
                // Cartographic Image
                AsyncImage(
                    model = ShahnamehRepository.MYTHIC_MAP_URL,
                    contentDescription = "نقشه اساطیری شاهنامه",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(zoomScale)
                )

                // Ambient Scrim
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    SurfaceContainerLowest.copy(alpha = 0.4f),
                                    Color.Transparent,
                                    SurfaceContainerLowest.copy(alpha = 0.75f)
                                )
                            )
                        )
                )

                // Floating Solar Compass
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceContainerLowest.copy(alpha = 0.85f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = Secondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "شمال اساطیری",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                // Map Zoom & Layer HUD
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(
                        onClick = { zoomScale = (zoomScale + 0.2f).coerceAtMost(1.8f) },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "بزرگنمایی",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    IconButton(
                        onClick = { zoomScale = (zoomScale - 0.2f).coerceAtLeast(0.8f) },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "کوچکنمایی",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Layers,
                            contentDescription = "لایه‌ها",
                            tint = Secondary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Interactive Waypoint Pins on the map
                // Pin 1: Zabol (Origin)
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 44.dp, end = 36.dp)
                        .clickable { onSelectWaypoint(ShahnamehRepository.mapWaypoints[0]) }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(Secondary)
                                .border(2.dp, SurfaceContainerLowest, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "۱. زابلستان",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SecondaryFixed,
                                    fontSize = 9.sp
                                )
                            )
                        }
                    }
                }

                // Pin 2: Mazandaran Border / Dragon Pass (Labor 3 & 4)
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { onSelectWaypoint(ShahnamehRepository.mapWaypoints[2]) }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(14.dp)
                                .clip(CircleShape)
                                .background(Primary)
                                .border(2.dp, SurfaceContainerLowest, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "خوان ۳ و ۴",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 9.sp
                                )
                            )
                        }
                    }
                }

                // Pin 3: White Demon Cave (ACTIVE PULSING PIN)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 56.dp)
                        .clickable { onSelectWaypoint(ShahnamehRepository.mapWaypoints[3]) }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .scale(pulseScale)
                                    .clip(CircleShape)
                                    .background(Secondary.copy(alpha = 0.35f))
                            )
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(Secondary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Fort,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(SurfaceContainerLowest)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(5.dp)
                                        .clip(CircleShape)
                                        .background(Secondary)
                                )
                                Text(
                                    text = "غار دیو سپید (فعال)",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                    }
                }

                // Map Bottom Info Prompt Bar
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerLowest.copy(alpha = 0.92f))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AltRoute,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "مسیر هفت‌خوان: زابلستان ⟵ مازندران",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)
                        )
                    }

                    Text(
                        text = "۴۰۰ فرسنگ",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Selected Active Location Dossier
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerHigh.copy(alpha = 0.95f),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Top line
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

                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(PrimaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Landscape,
                                    contentDescription = null,
                                    tint = Secondary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(TertiaryContainer)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "خوان هفتم • نبرد فرجامین",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Tertiary,
                                                fontSize = 9.sp
                                            )
                                        )
                                    }
                                    Text(
                                        text = "• دژ دیوان",
                                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    )
                                }

                                Text(
                                    text = "غار اسپورز در مازندران",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainer)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "نشانه‌گذاری",
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Lore Text
                    Text(
                        text = "غاری مخوف، سرد و مه‌آلود در اعماق ستیغ‌های البرزکوه و بیشه‌زارهای انبوه مازندران. دژ تسخیرناپذیر دیو سپید و قربانگاه تاریکی که کی‌کاووس شاه و لشکر پهلوانان ایران‌زمین در آن نابینا و اسیر گشتند.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 21.sp
                        )
                    )

                    // Couplet Callout
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = SurfaceContainerLow.copy(alpha = 0.8f)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "شاهنامه فردوسی • داستان هفت‌خوان",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontSize = 10.sp
                                )
                            )
                            Text(
                                text = "«بیامد به در غار چون کوهسار / پر از آب دریا و بر گرد غار»",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = SecondaryFixed,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                            Text(
                                text = "«به تاریکی اندر تن دیو دید / بدان غار در، چشمه آب دید»",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }

                    // Environmental parameters
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        EnvParamCard(
                            icon = Icons.Default.AcUnit,
                            iconTint = Primary,
                            label = "اقلیم و آب‌وهوا",
                            value = "سرمای قیرگون",
                            modifier = Modifier.weight(1f)
                        )
                        EnvParamCard(
                            icon = Icons.Default.AutoFixHigh,
                            iconTint = Secondary,
                            label = "ویژگی اسطوره‌ای",
                            value = "جادوی سیاه",
                            modifier = Modifier.weight(1f)
                        )
                        EnvParamCard(
                            icon = Icons.Default.SocialDistance,
                            iconTint = Tertiary,
                            label = "فاصله از زابل",
                            value = "۴۰۰ فرسنگ",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seven Trials Stepper
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                        Icon(
                            imageVector = Icons.Default.Route,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "منزل‌گاه‌های هفت‌خوان رستم",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "۷ مرحله کامل",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                ShahnamehRepository.labors.forEach { labor ->
                    val isFinal = labor.number == 7
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenBattleSim() },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isFinal) SurfaceContainerHigh else SurfaceContainerLow
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (isFinal) Secondary else PrimaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isFinal) {
                                    Icon(
                                        imageVector = Icons.Default.Stars,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                } else {
                                    Text(
                                        text = "${labor.number}",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = labor.title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = if (isFinal) Secondary else MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp
                                    )
                                )
                                Text(
                                    text = labor.subtitle,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 11.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cartographic Legend (راهنمای قلمروها)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLow
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            Icon(
                                imageVector = Icons.Default.Map,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "راهنمای رنگ‌بندی و عوارض نقشه اساطیری",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Text(
                            text = "۳ اقلیم اصلی",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }

                    LegendRow(
                        dotColor = Secondary,
                        label = "قلمرو شاهنشاهی ایران (فره ایزدی)",
                        capital = "پایتخت: استخر و بلخ"
                    )
                    LegendRow(
                        dotColor = MaterialTheme.colorScheme.error,
                        label = "سرزمین توران و ترکان (افراسیاب)",
                        capital = "رود آموی و قنق"
                    )
                    LegendRow(
                        dotColor = Primary,
                        label = "اقلیم مازندران و کوهسار دیوان جادوز",
                        capital = "غار هفت‌کوه"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Action Launcher
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onOpenBattleSim,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("btn_enter_battle_sim")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsMartialArts,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "ورود به خوان هفتم: مصاف با دیو سپید",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Secondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Button(
                    onClick = onOpenGenealogy,
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountTree,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "کاوش تبارشناسی ساکنان و دیوان این منطقه در شجره‌نامه",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EnvParamCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = SurfaceContainer
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
fun LegendRow(
    dotColor: Color,
    label: String,
    capital: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = SurfaceContainer
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(dotColor)
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Text(
                text = capital,
                style = MaterialTheme.typography.labelSmall.copy(color = dotColor)
            )
        }
    }
}
