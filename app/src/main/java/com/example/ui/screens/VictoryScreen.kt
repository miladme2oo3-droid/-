package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WorkspacePremium
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
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.Secondary
import com.example.ui.theme.SecondaryContainer
import com.example.ui.theme.SecondaryFixed
import com.example.ui.theme.SecondaryFixedDim
import com.example.ui.theme.SurfaceBright
import com.example.ui.theme.SurfaceContainer
import com.example.ui.theme.SurfaceContainerHigh
import com.example.ui.theme.SurfaceContainerHighest
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.Tertiary
import com.example.ui.theme.TertiaryContainer

@Composable
fun VictoryScreen(
    onGoToGenealogy: () -> Unit,
    onContinueStory: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isPlayingCelebrationAudio by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Modal Frame Container
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("victory_screen_card"),
                shape = RoundedCornerShape(18.dp),
                color = SurfaceContainerLow,
                shadowElevation = 16.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Top Radiant Badge & Close Trigger
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(SurfaceContainerHigh)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Stars,
                                    contentDescription = null,
                                    tint = Secondary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "پیروزی جاودان • پایان هفت‌خوان رستم",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = onClose,
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerHighest)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "بستن",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Grand Title & Subtitle
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "شفا و روشنایی دیدگان کی‌کاووس شاه",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Secondary,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp
                            )
                        )
                        Text(
                            text = "چکاندن سه قطره از خون جگر دیو سپید در چشمان نابینای شهریار ایران و طلوع دوباره نور ایزدی",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 20.sp
                            )
                        )
                    }

                    // Hero Miniature Artwork Panel
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(14.dp))
                            .border(
                                2.dp,
                                Brush.linearGradient(listOf(Secondary, PrimaryContainer, SecondaryContainer)),
                                RoundedCornerShape(14.dp)
                            )
                    ) {
                        AsyncImage(
                            model = ShahnamehRepository.HEALING_KAVUS_URL,
                            contentDescription = "شفای دیدگان کی‌کاووس شاه",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Authentic Couplet Banner at bottom
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = SurfaceContainerLowest.copy(alpha = 0.9f)
                        ) {
                            Text(
                                text = "«چو اندر کشیدند در چشم شاه / بشد تیرگی باز و روشن نگاه»",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = SecondaryFixed,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                ),
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                            )
                        }
                    }

                    // Miracle & Transformation Status Card
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = SurfaceContainer
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
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
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(Secondary)
                                    )
                                    Text(
                                        text = "معجزه خوان هفتم: اعاده فرّ کیانی",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(SurfaceContainerHighest)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "تکمیل ۱۰۰٪",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
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
                                    color = SurfaceContainerLow
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Visibility,
                                                contentDescription = null,
                                                tint = Secondary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = "دیدگان شاهنشاه",
                                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            )
                                        }
                                        Text(
                                            text = "روشنایی کامل",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                        Text(
                                            text = "فرّ کیانی تجدید گردید",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = SecondaryFixed,
                                                fontSize = 9.sp
                                            )
                                        )
                                    }
                                }

                                Surface(
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    color = SurfaceContainerLow
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Shield,
                                                contentDescription = null,
                                                tint = Primary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = "دلاوران ایران‌زمین",
                                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            )
                                        }
                                        Text(
                                            text = "۱۲,۰۰۰ سوار",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                        Text(
                                            text = "رهایی از جادوی دیوان",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Primary,
                                                fontSize = 9.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 4 Unlocked Mythic Rewards & Spoils
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "غنیمت‌ها و دستاوردهای حماسی رستم",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "۴ پاداش بازگشایی شد",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }

                        RewardCard(
                            icon = Icons.Default.WorkspacePremium,
                            iconTint = Secondary,
                            containerTint = PrimaryContainer,
                            title = "لقب جاودان «جهان‌پهلوان»",
                            tag = "اسطوره‌ای",
                            tagBg = SurfaceContainerHighest,
                            tagColor = Secondary,
                            desc = "تاج‌بخش و سپهدار اعظم تمام ارتش شاهنامه"
                        )

                        RewardCard(
                            icon = Icons.Default.MilitaryTech,
                            iconTint = Secondary,
                            containerTint = SurfaceContainerHighest,
                            title = "خلعت شاهوار و تاج سیمین",
                            tag = "پیشکش کی‌کاووس",
                            tagBg = PrimaryContainer,
                            tagColor = Primary,
                            desc = "دیبای زرین سغدی، خنجر مرصع و کمر زرین"
                        )

                        RewardCard(
                            icon = Icons.Default.AutoAwesome,
                            iconTint = MaterialTheme.colorScheme.onSecondaryContainer,
                            containerTint = SecondaryContainer,
                            title = "+۵,۰۰۰ امتیاز فرّ ایزدی",
                            tag = "رتبه ۱ تبارنامه",
                            tagBg = SurfaceContainerHighest,
                            tagColor = SecondaryFixedDim,
                            desc = "ثبت رسمی نام رستم دستان بر فراز دودمان سام نریمان"
                        )

                        RewardCard(
                            icon = Icons.Default.MonetizationOn,
                            iconTint = Tertiary,
                            containerTint = SurfaceContainerHigh,
                            title = "گنج بادآورد مازندران",
                            tag = "+۱,۵۰۰ دینار زر",
                            tagBg = SurfaceContainerHighest,
                            tagColor = Tertiary,
                            desc = "جام باده خسروانی و مشک و عنبر غار دیو سپید"
                        )
                    }

                    // Naqqali Celebration Player Bar
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = SurfaceContainerHighest.copy(alpha = 0.8f)
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
                                IconButton(
                                    onClick = { isPlayingCelebrationAudio = !isPlayingCelebrationAudio },
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Secondary)
                                ) {
                                    Icon(
                                        imageVector = if (isPlayingCelebrationAudio) Icons.Default.Pause else Icons.Default.PlayArrow,
                                        contentDescription = "پخش/مکث",
                                        tint = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column {
                                    Text(
                                        text = "آوای کرنای و نقالی پیروزی",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = "نواخته شدن کوس بشارت در درگاه کیانی",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontSize = 10.sp
                                        )
                                    )
                                }
                            }

                            // Wave bars
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                listOf(12, 20, 8, 16).forEach { h ->
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

                    // Action Footers
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = onGoToGenealogy,
                            colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountTree,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "ثبت در شجره‌نامه و دریافت خلعت",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Button(
                            onClick = onContinueStory,
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
                                    imageVector = Icons.Default.Celebration,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "ادامه داستان و جشن در ایوان شاه",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
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

@Composable
fun RewardCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    containerTint: Color,
    title: String,
    tag: String,
    tagBg: Color,
    tagColor: Color,
    desc: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        color = SurfaceContainer
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(containerTint),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(tagBg)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = tagColor,
                                fontSize = 9.sp
                            )
                        )
                    }
                }
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
