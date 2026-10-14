package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Hardware
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.ShahnamehRepository
import com.example.model.LaborItem
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.Tertiary
import com.example.ui.theme.TertiaryContainer

@Composable
fun HaftKhanScreen(
    selectedKhanIndex: Int,
    onSelectKhan: (Int) -> Unit,
    onEnterBattleSim: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedLabors by remember { mutableStateOf(setOf(1, 3, 7)) }

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
            // Top Nav Context Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.clickable { onBack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "بازگشت",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "رستم دستان / نبردهای حماسی",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.outline)
                    )
                    Text(
                        text = "Epic Battles",
                        style = MaterialTheme.typography.labelSmall.copy(color = SecondaryFixed)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(SurfaceContainerHigh)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "خوان هفتم",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Secondary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            // Title Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "هفت‌خوان رستم",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Secondary.copy(alpha = 0.15f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "HAFT KHAN",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Secondary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        )
                    }
                }

                Text(
                    text = "گذر از مازندران و رهایی کی‌کاووس شاه • Journey to Mazandaran to rescue King Kay Kavus",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Stepper / Quest Progress Tracker (۱۰۰٪ تکمیل مسیر)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLow.copy(alpha = 0.95f)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
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
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "سلوک پهلوانی (۷ از ۷ گذرگاه)",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Text(
                            text = "۱۰۰٪ تکمیل مسیر",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SecondaryFixed,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    // Linear Stepper Track
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 1..7) {
                            val isActive = i == 7
                            val isSelected = i == selectedKhanIndex
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable { onSelectKhan(i) }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(if (isActive) 28.dp else 24.dp)
                                        .clip(CircleShape)
                                        .background(if (isActive) Secondary else PrimaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$i",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (isActive) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimaryContainer,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                                Text(
                                    text = when (i) {
                                        1 -> "شیر"
                                        2 -> "بیابان"
                                        3 -> "اژدها"
                                        4 -> "جادوگر"
                                        5 -> "اولاد"
                                        6 -> "ارژنگ"
                                        else -> "دیو سپید"
                                    },
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = if (isActive) Secondary else MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 9.sp,
                                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }

                    // 3 Metric Pills
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MetricPill(
                            icon = Icons.Default.SportsMartialArts,
                            iconTint = Secondary,
                            title = "۷ منزلگاه",
                            sub = "نبردهای مرگبار",
                            modifier = Modifier.weight(1f)
                        )
                        MetricPill(
                            icon = Icons.Default.Pets,
                            iconTint = Primary,
                            title = "رخش دستان",
                            sub = "یار و همرزم وفادار",
                            modifier = Modifier.weight(1f)
                        )
                        MetricPill(
                            icon = Icons.Default.MilitaryTech,
                            iconTint = SecondaryFixed,
                            title = "پیروزی نهایی",
                            sub = "رهایی شاه ایران",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Featured Battle Hero Showcase Card (خوان واپسین: دیو سپید)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("featured_battle_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh)
            ) {
                Column {
                    // Header Image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        AsyncImage(
                            model = ShahnamehRepository.BATTLE_SCENE_URL,
                            contentDescription = "پیکار با دیو سپید",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, SurfaceContainerHigh.copy(alpha = 0.9f))
                                    )
                                )
                        )

                        // Top Khan Floating Badge
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.85f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
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
                                    text = "خوان واپسین • THE FINAL LABOR",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(14.dp)
                        ) {
                            Text(
                                text = "نبرد در قعر کوه اسپروز • Mt. Asparuz Cavern",
                                style = MaterialTheme.typography.labelSmall.copy(color = Tertiary)
                            )
                            Text(
                                text = "پیکار سهمگین با دیو سپید",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 19.sp
                                )
                            )
                        }
                    }

                    // Card Body
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Classical Couplet Box
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainerLowest.copy(alpha = 0.7f)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp, 1.dp)
                                            .background(Secondary)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.Flare,
                                        contentDescription = null,
                                        tint = Secondary,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "شاهنامه فردوسی طوسی",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontSize = 10.sp
                                        )
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(20.dp, 1.dp)
                                            .background(Secondary)
                                    )
                                }

                                Text(
                                    text = "«بیامد به غار اندرون پهلوان / دلیر و جگرآور و پهلوان»",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                                Text(
                                    text = "«فروهشت شمشیر و ببرید پای / فغان و خروش آمد از خوابگاه»",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }

                        // Tactics & Relics Bento
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "جنگ‌افزارها و شگردهای رستم (TACTICS & RELICS)",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                BentoRelicItem(
                                    icon = Icons.Default.Colorize,
                                    iconTint = Primary,
                                    title = "تیغ گیتی‌فروز",
                                    desc = "شمشیر آبدیده فولادین",
                                    modifier = Modifier.weight(1f)
                                )
                                BentoRelicItem(
                                    icon = Icons.Default.Shield,
                                    iconTint = Secondary,
                                    title = "ببر بیان",
                                    desc = "زره جادویی ضد تیغ و آتش",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                BentoRelicItem(
                                    icon = Icons.Default.WaterDrop,
                                    iconTint = Tertiary,
                                    title = "خون جگر دیو",
                                    desc = "داروی بینایی شاه کاووس",
                                    modifier = Modifier.weight(1f)
                                )
                                BentoRelicItem(
                                    icon = Icons.Default.Explore,
                                    iconTint = MaterialTheme.colorScheme.onSurface,
                                    title = "راهنمایی اولاد",
                                    desc = "شناسایی مخفیگاه دیو",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        // Naqqali Player Bar inside Labor 7
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainerLow
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .clip(CircleShape)
                                            .background(Secondary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = "پخش",
                                            tint = MaterialTheme.colorScheme.onSecondary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "نوا و نقالی شاهنامه",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Text(
                                            text = "صدای مرشد ولی‌الله ترابی (خوان هفتم)",
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = SecondaryFixed,
                                                fontSize = 10.sp
                                            )
                                        )
                                    }
                                }

                                // Audio wave bars
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    listOf(14, 22, 10, 26, 18, 12).forEach { h ->
                                        Box(
                                            modifier = Modifier
                                                .width(3.dp)
                                                .height(h.dp)
                                                .clip(RoundedCornerShape(1.5.dp))
                                                .background(Secondary)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Legendary Armory & Companions Carousel
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
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
                            imageVector = Icons.Default.Hardware,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "یاران و تجهیزات افسانه‌ای رستم",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "۳ نشان ماندگار",
                        style = MaterialTheme.typography.labelSmall.copy(color = Secondary)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ArmoryCard(
                        title = "رخش گلگون",
                        badge = "همسنگر و بیدارباش رستم",
                        desc = "کشتن شیر بیشه در خوان نخست",
                        imageUrl = ShahnamehRepository.RAKHSH_URL
                    )
                    ArmoryCard(
                        title = "ببر بیان",
                        badge = "زره رویین‌تن جادویی",
                        desc = "خنثی‌کننده جادو و آتش اژدها",
                        imageUrl = ShahnamehRepository.BABR_BAYAN_URL
                    )
                    ArmoryCard(
                        title = "گرز گاوسار و کمند",
                        badge = "میراث جهان‌پهلوان سام",
                        desc = "درهم کوبنده سرکشان دیوزاد",
                        imageUrl = ShahnamehRepository.GORZ_GAVSAR_URL
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Labor 3 Deep Dive Spotlight Card (پیکار با اژدها)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerLow
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 10f)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        AsyncImage(
                            model = ShahnamehRepository.DRAGON_BATTLE_URL,
                            contentDescription = "پیکار با اژدها",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.85f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "خوان سوم: آزمون هوشیاری",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.9f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "خطر: سهمگین",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onErrorContainer)
                            )
                        }
                    }

                    // Tazhib Couplet Box
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = SurfaceContainerHighest.copy(alpha = 0.6f)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "«چو بیدار شد رستم و رخش را / برآشفت و بشکست خفتان و خود»",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = Secondary,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                            Text(
                                text = "«بینداخت خنجر چو شیر ژیان / سر اژدها کرد ز تن جدا»",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = Secondary,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }

                    // Insights Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            color = SurfaceContainer
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.NotificationsActive,
                                        contentDescription = null,
                                        tint = Secondary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "نقش هوشیاری رخش",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Text(
                                    text = "سه بار کوبیدن سم بر زمین و بیدار ساختن رستم از خواب مرگ، و گاز گرفتن شانه اژدها.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 10.sp,
                                        lineHeight = 15.sp
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            color = SurfaceContainer
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = null,
                                        tint = Secondary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "پاداش و ثمره خوان",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Text(
                                    text = "باطل شدن طلسم آتشین اژدها، گشوده شدن راه گردنه‌های البرز و شست‌وشوی تن در چشمه پاک.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 10.sp,
                                        lineHeight = 15.sp
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Full 7 Khans Accordion Chronicle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "گاه‌شمار کامل هفت‌خوان رستم (The 7 Epic Labors)",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )

                ShahnamehRepository.labors.forEach { labor ->
                    val isExpanded = expandedLabors.contains(labor.number)
                    LaborAccordionItem(
                        labor = labor,
                        isExpanded = isExpanded,
                        onToggle = {
                            expandedLabors = if (isExpanded) {
                                expandedLabors - labor.number
                            } else {
                                expandedLabors + labor.number
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Launchers
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onEnterBattleSim,
                    colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("btn_start_battle")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SportsMartialArts,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "ورود به شبیه‌ساز نبرد رستم و دیو سپید",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                Button(
                    onClick = onBack,
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
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "مطالعه ابیات کامل فردوسی (متن انتقادی)",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MetricPill(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    title: String,
    sub: String,
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
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp
                )
            )
        }
    }
}

@Composable
fun BentoRelicItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    title: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = SurfaceContainer
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PrimaryContainer.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ArmoryCard(
    title: String,
    badge: String,
    desc: String,
    imageUrl: String
) {
    Surface(
        modifier = Modifier.width(220.dp),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainer
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(SurfaceContainerHigh)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1
                )
                Text(
                    text = badge,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Secondary,
                        fontSize = 9.sp
                    ),
                    maxLines = 1
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun LaborAccordionItem(
    labor: LaborItem,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    val isFinal = labor.number == 7

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        shape = RoundedCornerShape(12.dp),
        color = if (isFinal) SurfaceContainerHigh else SurfaceContainer
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
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
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isFinal) Secondary else PrimaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${labor.number}",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = if (isFinal) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Column {
                        Text(
                            text = labor.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = if (isFinal) Secondary else MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = labor.subtitle,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SecondaryContainer.copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = labor.tag,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = SecondaryFixed,
                                fontSize = 9.sp
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .size(18.dp)
                            .rotate(if (isExpanded) 180f else 0f)
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceContainerLowest.copy(alpha = 0.6f))
                            .padding(8.dp)
                    ) {
                        Text(
                            text = labor.verse,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = SecondaryFixed,
                                fontStyle = FontStyle.Italic,
                                fontSize = 11.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Text(
                        text = labor.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 17.sp
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "موقعیت: ${labor.location}",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.outline)
                        )
                        Text(
                            text = "کلید ظفر: ${labor.keyToVictory}",
                            style = MaterialTheme.typography.labelSmall.copy(color = Secondary)
                        )
                    }
                }
            }
        }
    }
}
