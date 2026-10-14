package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Cyclone
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Dangerous
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsMartialArts
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WaterDrop
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
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
import com.example.model.BattleSkill
import com.example.model.BattleState
import com.example.ui.theme.Error
import com.example.ui.theme.ErrorContainer
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
fun BattleSimulationScreen(
    battleState: BattleState,
    onExecuteSkill: (BattleSkill) -> Unit,
    onUseNoshdaru: () -> Unit,
    onUseOil: () -> Unit,
    onExecuteFinisher: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulseFinisher")
    val finisherPulse by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "finisherPulse"
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
                .padding(bottom = 80.dp)
        ) {
            // Battle Header & Status Bar
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
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerHigh),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SportsMartialArts,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "پیکار با دیو سپید",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = Secondary,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp
                                    )
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(SecondaryContainer.copy(alpha = 0.2f))
                                        .padding(horizontal = 6.dp, vertical = 1.dp)
                                ) {
                                    Text(
                                        text = "خوان هفتم",
                                        style = MaterialTheme.typography.labelSmall.copy(color = Secondary)
                                    )
                                }
                            }
                            Text(
                                text = "Labor VII: Duel in the Dark Cavern",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerHigh)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
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
                                    text = "نوبت ${battleState.turn}",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = onClose,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainer)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "بستن",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Battle Phase Banner
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = SurfaceContainerLow
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Explore,
                                contentDescription = null,
                                tint = Tertiary,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "غار تاریک مازندران • اعماق هفت‌خوان",
                                style = MaterialTheme.typography.labelSmall.copy(color = Tertiary)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.HourglassTop,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "زمان تصمیم: ${battleState.secondsLeft} ثانیه",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }

            // Dual Combatant HUD Gauges (Rostam vs Div-e Sepid)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // HERO: Rostam Card
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    color = SurfaceContainerHigh.copy(alpha = 0.9f)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = Secondary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "رستم دستان",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "پهلوان جهان • تهمتن",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                        }

                        // Hero HP Bar
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Text(
                                        text = "سلامت",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                                Text(
                                    text = "${battleState.heroHp} / ${battleState.heroMaxHp}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                            val hpRatio = (battleState.heroHp.toFloat() / battleState.heroMaxHp.toFloat()).coerceIn(0f, 1f)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(SurfaceContainerLowest)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(hpRatio)
                                        .height(6.dp)
                                        .background(Brush.horizontalGradient(listOf(Secondary, SecondaryFixed)))
                                )
                            }
                        }

                        // Farr Meter
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Flare,
                                        contentDescription = null,
                                        tint = Tertiary,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Text(
                                        text = "فرّ ایزدی",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Tertiary,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                                Text(
                                    text = "${battleState.heroFarr}٪",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Tertiary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                            val farrRatio = (battleState.heroFarr / 100f).coerceIn(0f, 1f)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(2.5.dp))
                                    .background(SurfaceContainerLowest)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(farrRatio)
                                        .height(5.dp)
                                        .background(Brush.horizontalGradient(listOf(PrimaryContainer, Tertiary)))
                                )
                            }
                        }

                        // Status Perks
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            PerkChip(text = "ببر بیان", color = Secondary)
                            PerkChip(text = "پاسداری یزدان", color = Tertiary)
                        }
                    }
                }

                // BOSS: Div-e Sepid Card
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    color = SurfaceContainerHigh.copy(alpha = 0.9f)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(TertiaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Dangerous,
                                    contentDescription = null,
                                    tint = Primary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "دیو سپید",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "سالار جادوان مازندران",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.error,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                        }

                        // Boss HP Bar
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.HeartBroken,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Text(
                                        text = "جان اهریمنی",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                                Text(
                                    text = "${battleState.bossHp} / ${battleState.bossMaxHp}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.error,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                            val bossHpRatio = (battleState.bossHp.toFloat() / battleState.bossMaxHp.toFloat()).coerceIn(0f, 1f)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(SurfaceContainerLowest)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(bossHpRatio)
                                        .height(6.dp)
                                        .background(Brush.horizontalGradient(listOf(ErrorContainer, Error)))
                                )
                            }
                        }

                        // Dark Magic
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cyclone,
                                        contentDescription = null,
                                        tint = Primary,
                                        modifier = Modifier.size(11.dp)
                                    )
                                    Text(
                                        text = "جادوی سیاه",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Primary,
                                            fontSize = 9.sp
                                        )
                                    )
                                }
                                Text(
                                    text = "${battleState.darkMagic}٪",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    )
                                )
                            }
                            val magicRatio = (battleState.darkMagic / 100f).coerceIn(0f, 1f)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .clip(RoundedCornerShape(2.5.dp))
                                    .background(SurfaceContainerLowest)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(magicRatio)
                                        .height(5.dp)
                                        .background(Brush.horizontalGradient(listOf(TertiaryContainer, PrimaryContainer)))
                                )
                            }
                        }

                        // Status Perks
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            PerkChip(text = "زخمی از تیغ", color = MaterialTheme.colorScheme.error)
                            PerkChip(text = "خشم شب", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Epic Center Arena Visual Display
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 10f)
                    ) {
                        AsyncImage(
                            model = ShahnamehRepository.BATTLE_SCENE_URL,
                            contentDescription = "میدان نبرد",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Overlays
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, SurfaceContainerLowest.copy(alpha = 0.85f))
                                    )
                                )
                        )

                        // Floating indicators
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SurfaceContainerLowest.copy(alpha = 0.85f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = null,
                                        tint = Secondary,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "بخش حساس: آسیب‌پذیری سینه دیو",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(PrimaryContainer.copy(alpha = 0.85f))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "رخش در ورودی غار",
                                    style = MaterialTheme.typography.labelSmall.copy(color = Primary)
                                )
                            }
                        }

                        // Bottom Couplet Banner
                        Surface(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = SurfaceContainerLowest.copy(alpha = 0.92f)
                        ) {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "شاهنامه فردوسی • پادشاهی کی‌کاووس",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Secondary.copy(alpha = 0.8f),
                                        fontSize = 9.sp
                                    )
                                )
                                Text(
                                    text = "«فروهشت شمشیر و ببرید پای / فغان و خروش آمد از خوابگاه»",
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        color = Secondary,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    }

                    // Realtime Action Narrative Ticker
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = SurfaceContainerLow
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "${battleState.logSpeaker}: ",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = battleState.latestLog,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tactical Commands & Martial Actions Grid (4 Skills)
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
                            imageVector = Icons.Default.SportsMartialArts,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "فرمان‌های پهلوانی",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "مصرف فرّ ایزدی برای هر فن",
                        style = MaterialTheme.typography.labelSmall.copy(color = Tertiary)
                    )
                }

                // 2x2 Grid of Battle Skills
                val skills = ShahnamehRepository.battleSkills
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CombatSkillCard(
                        skill = skills[0],
                        icon = Icons.Default.SportsMartialArts,
                        iconTint = Secondary,
                        containerTint = SecondaryContainer.copy(alpha = 0.2f),
                        onClick = { onExecuteSkill(skills[0]) },
                        modifier = Modifier.weight(1f)
                    )
                    CombatSkillCard(
                        skill = skills[1],
                        icon = Icons.Default.AllInclusive,
                        iconTint = Tertiary,
                        containerTint = TertiaryContainer.copy(alpha = 0.3f),
                        onClick = { onExecuteSkill(skills[1]) },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CombatSkillCard(
                        skill = skills[2],
                        icon = Icons.Default.CrueltyFree,
                        iconTint = Primary,
                        containerTint = PrimaryContainer.copy(alpha = 0.4f),
                        onClick = { onExecuteSkill(skills[2]) },
                        modifier = Modifier.weight(1f)
                    )
                    CombatSkillCard(
                        skill = skills[3],
                        icon = Icons.Default.Shield,
                        iconTint = SecondaryFixed,
                        containerTint = SecondaryContainer.copy(alpha = 0.2f),
                        onClick = { onExecuteSkill(skills[3]) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Quick Inventory & Sacred Relics Row
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "کوله‌بار و داروی پهلوان",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Item 1: Nosh-daru
                    InventoryItemCard(
                        icon = Icons.Default.Medication,
                        iconTint = Secondary,
                        title = "نوشدارو",
                        sub = "+۳۰۰ سلامت",
                        isUsed = battleState.noshdaruUsed,
                        onClick = onUseNoshdaru,
                        modifier = Modifier.weight(1f)
                    )

                    // Item 2: Light Oil
                    InventoryItemCard(
                        icon = Icons.Default.Lightbulb,
                        iconTint = Tertiary,
                        title = "روغن روشنایی",
                        sub = "رفع تاریکی غار",
                        isUsed = battleState.oilUsed,
                        onClick = onUseOil,
                        modifier = Modifier.weight(1f)
                    )

                    // Item 3: Liver Extraction Vial
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        color = PrimaryContainer.copy(alpha = 0.25f)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(SecondaryContainer.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.WaterDrop,
                                    contentDescription = null,
                                    tint = Secondary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Text(
                                text = "آبگینه خون جگر",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "هدف درمان شاه",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 8.sp
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Objective Card & Dynamic Finisher Strike Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Objective Card
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = SurfaceContainerLow
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Secondary.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Secondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "هدف نهایی هفت‌خوان",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "شکافتن سینه دیو سپید و چکاندن سه قطره از خون جگر در چشمان کاووس‌شاه و لشکریان ایران برای بازیافتن بینایی.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp
                                )
                            )
                        }
                    }
                }

                // Finisher Strike Button
                Button(
                    onClick = onExecuteFinisher,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .scale(finisherPulse)
                        .background(
                            Brush.horizontalGradient(
                                listOf(PrimaryContainer, TertiaryContainer, SecondaryContainer)
                            ),
                            RoundedCornerShape(14.dp)
                        )
                        .testTag("btn_finisher_strike")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Flare,
                            contentDescription = null,
                            tint = Secondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "اجرای ضربه نهایی: شکافتن سینه دیو",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PerkChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SurfaceContainer)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(color = color, fontSize = 8.sp)
        )
    }
}

@Composable
fun CombatSkillCard(
    skill: BattleSkill,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    containerTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(96.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerHigh
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(SurfaceContainer)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "${skill.cost} فر",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Secondary,
                            fontSize = 9.sp
                        )
                    )
                }
            }

            Column {
                Text(
                    text = skill.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = skill.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp,
                        lineHeight = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun InventoryItemCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    title: String,
    sub: String,
    isUsed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable(enabled = !isUsed) { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (isUsed) SurfaceContainer.copy(alpha = 0.4f) else SurfaceContainer
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = if (isUsed) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = if (isUsed) "مصرف شد" else sub,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = if (isUsed) MaterialTheme.colorScheme.outline else iconTint,
                    fontSize = 8.sp
                )
            )
        }
    }
}
