package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.model.Story
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
fun EpicTalesScreen(
    isPlayingNaqqali: Boolean,
    onToggleNaqqali: () -> Unit,
    onOpenHaftKhan: () -> Unit,
    onOpenGenealogy: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedEpoch by remember { mutableStateOf("همه داستان‌ها") }
    val epochs = listOf(
        "همه داستان‌ها",
        "پیشدادیان • نخستین شهریاران",
        "کیانیان • جام گیتی‌نما",
        "پهلوانی • خاندان زال و رستم",
        "تاریخی • اشکانیان و ساسانیان"
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
            // Secondary Epoch Navigation Chips Filter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                epochs.forEach { epoch ->
                    val isSelected = epoch == selectedEpoch
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) Secondary else SurfaceContainerHigh)
                            .clickable { selectedEpoch = epoch }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Stars,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                            Text(
                                text = epoch,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            // Featured Master Epic Card: Rostam & Sohrab
            val masterStory = ShahnamehRepository.stories.first()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("featured_epic_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
            ) {
                Column {
                    // Top Gold Accent
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Secondary.copy(alpha = 0.2f), Secondary, Secondary.copy(alpha = 0.2f))
                                )
                            )
                    )

                    // Hero Miniature Visual Frame
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = masterStory.imageUrl,
                            contentDescription = masterStory.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Gradient Scrim
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, SurfaceContainerLow.copy(alpha = 0.85f))
                                    )
                                )
                        )

                        // Top Badges
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(PrimaryContainer.copy(alpha = 0.9f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = masterStory.statusBadge,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontSize = 10.sp
                                )
                            )
                        }

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp)
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerLowest.copy(alpha = 0.8f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.BookmarkAdd,
                                contentDescription = "نشانه کردن",
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Card Content Body
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = masterStory.chapter,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Secondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = masterStory.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )

                        Text(
                            text = masterStory.excerpt,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 21.sp
                            )
                        )

                        // Calligraphic Verse Frame (Tazhib Inset)
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainerHighest.copy(alpha = 0.6f)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                masterStory.coupletLines.forEach { line ->
                                    Text(
                                        text = line,
                                        style = MaterialTheme.typography.headlineSmall.copy(
                                            color = SecondaryFixed,
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 13.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                                Text(
                                    text = "— حکیم ابوالقاسم فردوسی —",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        // Meta indicators (۳۵۰۰ بیت, ۴۵ دقیقه, نقالی کامل)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            MetaCard(
                                icon = Icons.Default.FormatQuote,
                                title = masterStory.versesCount,
                                subtitle = "بیت فردوسی",
                                modifier = Modifier.weight(1f)
                            )
                            MetaCard(
                                icon = Icons.Default.Schedule,
                                title = masterStory.readTime,
                                subtitle = "زمان خوانش",
                                modifier = Modifier.weight(1f)
                            )
                            MetaCard(
                                icon = Icons.Default.Headphones,
                                title = "نقالی کامل",
                                subtitle = "پرده‌خوانی کهن",
                                modifier = Modifier.weight(1f)
                            )
                        }

                        // Call to Action Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = onToggleNaqqali,
                                colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isPlayingNaqqali) Icons.Default.Pause else Icons.Default.PlayCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = if (isPlayingNaqqali) "مکث نقالی" else "آغاز نقالی صوتی",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Button(
                                onClick = onOpenGenealogy,
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(44.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountTree,
                                        contentDescription = null,
                                        tint = Primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "مشاهده در شجره‌نامه",
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

            Spacer(modifier = Modifier.height(14.dp))

            // Curated Thematic Collections (۴ مجموعه تخصصی)
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
                        Box(
                            modifier = Modifier
                                .size(6.dp, 16.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Secondary)
                        )
                        Text(
                            text = "دفترهای حماسی و موضوعات کهن",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "۴ مجموعه تخصصی",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }

                // 2x2 Grid for thematic collections
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ThematicCard(
                        title = "هفت‌خوان‌ها و نبردها",
                        desc = "پیکار با اژدها، جادوگران و دیوان سفید",
                        count = "۱۲ داستان",
                        icon = Icons.Default.SportsMartialArts,
                        iconTint = Secondary,
                        containerTint = PrimaryContainer,
                        onClick = onOpenHaftKhan,
                        modifier = Modifier.weight(1f)
                    )
                    ThematicCard(
                        title = "سوگ‌سروده‌ها",
                        desc = "سیاوش در آتش، مرگ فرود و سوگ اسفندیار",
                        count = "۸ سوگ‌نامه",
                        icon = Icons.Default.LocalFireDepartment,
                        iconTint = MaterialTheme.colorScheme.error,
                        containerTint = MaterialTheme.colorScheme.errorContainer,
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ThematicCard(
                        title = "عاشقانه‌های کهن",
                        desc = "زال و رودابه سیمین‌تن، بیژن و منیژه",
                        count = "۶ منظومه",
                        icon = Icons.Default.Favorite,
                        iconTint = Secondary,
                        containerTint = SurfaceContainerHighest,
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )
                    ThematicCard(
                        title = "پادشاهی و دادگری",
                        desc = "کیخسرو پرهیزگار، عهد فریدون و بوذرجمهر",
                        count = "۱۵ عهدنامه",
                        icon = Icons.Default.Shield,
                        iconTint = Tertiary,
                        containerTint = TertiaryContainer,
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Reading Progress Showcase Widget (۶۸٪ دوران پهلوانی)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(14.dp),
                color = SurfaceContainerLow
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .border(3.dp, Secondary, CircleShape)
                                .background(SurfaceContainerLowest),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "۶۸٪",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Column {
                            Text(
                                text = "پیگیری سیر مطالعه",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                            Text(
                                text = "خوانش دوران پهلوانی",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "۳۲ از ۵۴ داستان بازخوانی شده",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = SecondaryFixed,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Button(
                        onClick = onOpenHaftKhan,
                        colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHighest),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "ادامه مطالعه",
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Story Listing Feed
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "فهرست برگزیده داستان‌ها",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )

                // List stories 2 through end
                ShahnamehRepository.stories.drop(1).forEach { story ->
                    StoryFeedItem(
                        story = story,
                        onOpen = {
                            if (story.id == "haft_khan_tale") {
                                onOpenHaftKhan()
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Floating Audio Naqqali Strip ("رزم رستم و اشکبوس کشانی" - مرشد ولی‌الله ترابی)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("audio_player_strip"),
                shape = RoundedCornerShape(14.dp),
                color = PrimaryContainer.copy(alpha = 0.9f),
                shadowElevation = 8.dp
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
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(Secondary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.GraphicEq,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Column {
                                Text(
                                    text = "نغمه نقالی هم‌اکنون در حال پخش",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = SecondaryFixed,
                                        fontSize = 9.sp
                                    )
                                )
                                Text(
                                    text = "رزم رستم و اشکبوس کشانی",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
                                Icon(
                                    imageVector = Icons.Default.SkipPrevious,
                                    contentDescription = "پیشین",
                                    tint = Secondary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            IconButton(
                                onClick = onToggleNaqqali,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Secondary)
                            ) {
                                Icon(
                                    imageVector = if (isPlayingNaqqali) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = "پخش/مکث",
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            IconButton(onClick = {}, modifier = Modifier.size(32.dp)) {
                                Icon(
                                    imageVector = Icons.Default.SkipNext,
                                    contentDescription = "پسین",
                                    tint = Secondary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    // Progress bar and duration
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(SurfaceContainerLowest)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.42f)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(Secondary)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "۱۲:۱۸",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                            Text(
                                text = "نقالی مرشد ولی‌الله ترابی",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                            Text(
                                text = "۲۹:۰۰",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Scholarly Quote & Ferdowsi Seal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = Secondary,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = "«بسی رنج بردم در این سال سی / عجم زنده کردم بدین پارسی»",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Secondary,
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Text(
                    text = "طرح پژوهشی ساماندهی متون شاهنامه شاه‌طهماسبی و بایسنقری",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Composable
fun MetaCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = SurfaceContainer
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Secondary,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 9.sp
                )
            )
        }
    }
}

@Composable
fun ThematicCard(
    title: String,
    desc: String,
    count: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    containerTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(containerTint),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    modifier = Modifier.padding(top = 6.dp)
                )

                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        lineHeight = 14.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Text(
                text = count,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = SecondaryFixed,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun StoryFeedItem(
    story: Story,
    onOpen: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(14.dp),
        color = SurfaceContainerLow
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    if (story.statusBadge.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SecondaryContainer.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = story.statusBadge,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = SecondaryFixed,
                                    fontSize = 9.sp
                                )
                            )
                        }
                    }

                    Text(
                        text = story.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(SurfaceContainerHigh)
                ) {
                    AsyncImage(
                        model = story.imageUrl,
                        contentDescription = story.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(
                text = story.excerpt,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    story.tags.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(SurfaceContainerHighest)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 9.sp
                                )
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(SurfaceContainerHigh),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "خواندن",
                        tint = Secondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
