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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.ShahnamehRepository
import com.example.model.CharacterNode
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
fun CharactersScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSelectCharacter: (CharacterNode) -> Unit,
    onOpenGenealogy: () -> Unit,
    onOpenHaftKhan: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf("همه شخصیت‌ها") }
    val categories = listOf(
        "همه شخصیت‌ها",
        "پهلوانان زابل",
        "شاهان کیانی",
        "پیشدادیان",
        "دیوان و توران",
        "زنان نامدار"
    )

    // Filter characters according to category and search query
    val filteredCharacters = remember(searchQuery, selectedCategory) {
        ShahnamehRepository.characters.filter { char ->
            val matchesSearch = searchQuery.isBlank() ||
                    char.name.contains(searchQuery) ||
                    char.title.contains(searchQuery) ||
                    char.englishName.contains(searchQuery, ignoreCase = true)

            val matchesCategory = when (selectedCategory) {
                "همه شخصیت‌ها" -> true
                "پهلوانان زابل" -> char.dynasty.contains("سیستان") || char.dynasty.contains("سمنگان")
                "شاهان کیانی" -> char.dynasty.contains("کیانیان")
                "پیشدادیان" -> char.dynasty.contains("پیشدادیان")
                "دیوان و توران" -> char.dynasty.contains("اهریمن") || char.dynasty.contains("توران")
                "زنان نامدار" -> char.id == "roudabeh" || char.dynasty.contains("کابلستان")
                else -> true
            }

            matchesSearch && matchesCategory
        }
    }

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
            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "دانشنامه شخصیت‌های شاهنامه",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
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
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(Secondary)
                            )
                            Text(
                                text = "۴۵۰ مدخل",
                                style = MaterialTheme.typography.labelSmall.copy(color = SecondaryFixed)
                            )
                        }
                    }
                }

                Text(
                    text = "پانتئون اساطیری و حماسی فردوسی؛ تبارنامه شهریاران، دلاوران سیستان، دیوان و شاه‌بانوان نامدار",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                )

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("character_search_input"),
                    placeholder = {
                        Text(
                            text = "جستجوی نام پهلوان، شاه، دیو، همسر...",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "جستجو",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "فیلتر",
                            tint = Secondary
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceContainerLow,
                        unfocusedContainerColor = SurfaceContainerLow,
                        focusedBorderColor = Secondary,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )

                // Category Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    categories.forEach { cat ->
                        val isSelected = cat == selectedCategory
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (isSelected) SecondaryContainer else SurfaceContainer)
                                .clickable { selectedCategory = cat }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = cat,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            // Character Spotlight Hero Card (Rostam)
            val rostam = ShahnamehRepository.characters.first()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("spotlight_character_card"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
            ) {
                Column {
                    // Miniature Illustration Frame
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 10f)
                    ) {
                        AsyncImage(
                            model = ShahnamehRepository.ROSTAM_PALACE_URL,
                            contentDescription = rostam.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, SurfaceContainerLow.copy(alpha = 0.9f))
                                    )
                                )
                        )

                        // Badges
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "جهان‌پهلوان ایران‌زمین",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(PrimaryContainer.copy(alpha = 0.85f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "سپهدار تاج‌بخش",
                                    style = MaterialTheme.typography.labelSmall.copy(color = Primary)
                                )
                            }
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
                                imageVector = Icons.Default.Star,
                                contentDescription = "ستاره",
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    // Content
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "رستم دستان (پیل‌تن)",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = Secondary,
                                    fontFamily = FontFamily.Serif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 19.sp
                                )
                            )
                            Text(
                                text = "سلسله کیانیان",
                                style = MaterialTheme.typography.labelSmall.copy(color = Tertiary)
                            )
                        }

                        Text(
                            text = "فرزند زال زر و رودابه • بزرگ‌ترین اسطوره رزم و پهلوانی تاریخ ایران",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium
                            )
                        )

                        // Poetry Inset
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainer
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "«به بازوی رستم میان را ببند / که گیتی ندیده‌ست چون او نژند»",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                                Text(
                                    text = "«هنر نزد ایرانیان است و بس / نیاورند بد سر به پیمان کس»",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }

                        // Power Stats Meters
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainerHigh.copy(alpha = 0.6f)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "⚔️ نیروی پهلوانی و زور بازو (ببر بیان و گرز سام)",
                                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    )
                                    Text(
                                        text = "۱۰۰٪",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(SurfaceContainerLowest)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(4.dp)
                                            .background(Secondary)
                                    )
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "✨ فرّ کیانی و پشتیبانی ایزدی (پاسداری سیمرغ)",
                                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    )
                                    Text(
                                        text = "۹۶٪",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Tertiary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(SurfaceContainerLowest)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.96f)
                                            .height(4.dp)
                                            .background(Tertiary)
                                    )
                                }

                                // 3 Stats columns
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    StatBox(
                                        label = "طول عمر",
                                        value = "۶۰۰ سال",
                                        sub = "کی‌قباد تا بهمن",
                                        modifier = Modifier.weight(1f)
                                    )
                                    StatBox(
                                        label = "مرکب وفادار",
                                        value = "رخش گلگون",
                                        sub = "همزاد و همراه",
                                        modifier = Modifier.weight(1f)
                                    )
                                    StatBox(
                                        label = "پایگاه حکومت",
                                        value = "زابلستان",
                                        sub = "نیمروز اساطیری",
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }

                        // Action Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = onOpenGenealogy,
                                colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AccountTree,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "مشاهده در درخت‌نامه",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Button(
                                onClick = onOpenHaftKhan,
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SportsMartialArts,
                                        contentDescription = null,
                                        tint = Primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "هفت‌خوان و نبردها",
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

            Spacer(modifier = Modifier.height(10.dp))

            // Lineage Connect Banner
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onOpenGenealogy() },
                shape = RoundedCornerShape(14.dp),
                color = SurfaceContainer
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
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
                                .clip(RoundedCornerShape(10.dp))
                                .background(PrimaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Hub,
                                contentDescription = null,
                                tint = Primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "پیمایش در ساختار تبارشناسی",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "اتصال ۳ سلسله پادشاهی و ۱۲ دودمان پهلوانی",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null,
                        tint = Secondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Characters Roster List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "سایر نام‌آوران و اسطوره‌ها",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )

                filteredCharacters.drop(1).forEach { char ->
                    CharacterRosterCard(
                        character = char,
                        onClick = {
                            onSelectCharacter(char)
                            onOpenGenealogy()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Quick Explore Stats
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                color = SurfaceContainerHigh
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(SecondaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.TravelExplore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "آمار پیوندهای تبارشناسی شاهنامه",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "بانک داده تطبیقی خاندان‌ها بر مبنای نسخه مسکو",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            color = SurfaceContainer
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "۵۳ پادشاه",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "از کیومرث تا یزدگرد سوم",
                                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            color = SurfaceContainer
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "۱۴۲ پهلوان",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = Tertiary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "ایران، توران، زابل و کابل",
                                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatBox(
    label: String,
    value: String,
    sub: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = SurfaceContainerLowest.copy(alpha = 0.8f)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 8.sp
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    fontSize = 7.sp
                )
            )
        }
    }
}

@Composable
fun CharacterRosterCard(
    character: CharacterNode,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = SurfaceContainerLow
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
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
                            .size(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerHigh)
                    ) {
                        AsyncImage(
                            model = character.imageUrl,
                            contentDescription = character.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = character.name,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            if (character.badge.isNotEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(TertiaryContainer)
                                        .padding(horizontal = 6.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = character.badge,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Tertiary,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                            }
                        }
                        Text(
                            text = character.title,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Secondary,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = character.biography,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 17.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
