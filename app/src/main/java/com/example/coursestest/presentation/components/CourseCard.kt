package com.example.coursestest.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.coursestest.R
import com.example.coursestest.data.models.Course
import com.example.coursestest.presentation.theme.DarkGray
import com.example.coursestest.presentation.theme.Glass
import com.example.coursestest.presentation.theme.Green
import com.example.coursestest.presentation.theme.White50
import com.example.coursestest.utils.ShiftAlignment
import com.example.coursestest.utils.dateFormat


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseCard(
    course: Course,
    onClickFavorite: (course: Course) -> Unit
) {
    val verticalShift = LocalDensity.current.run { (-30).dp.toPx() }

    ElevatedCard(
        modifier = Modifier.padding(bottom = 16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(114.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = course.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = ShiftAlignment(verticalShift)
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                ) {
                    IconButton(
                        onClick = {
                            onClickFavorite(course)
                        },
                        modifier = Modifier
                            .size(28.dp)
                            .align(Alignment.TopEnd),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Glass,
                        ),
                    ) {
                        if (course.hasLike) {
                            Icon(
                                painter = painterResource(R.drawable.icon_fav_active),
                                contentDescription = null,
                                tint = Green
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.icon_fav),
                                contentDescription = null
                            )
                        }

                    }
                    Row(
                        modifier = Modifier.align(Alignment.BottomStart),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier
                                .height(22.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Glass)
                                .padding(horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icon_star),
                                contentDescription = null,
                                tint = Green
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = course.rate,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }


                        Spacer(modifier = Modifier.width(4.dp))
                        Row(
                            modifier = Modifier
                                .height(22.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Glass)
                                .padding(horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dateFormat(course.publishDate),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = course.text,
                    style = MaterialTheme.typography.bodySmall,
                    color = White50,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${course.price} ₽",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        modifier = Modifier.clickable {},
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.MoreTxt),
                            style = MaterialTheme.typography.labelMedium,
                            color = Green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_arrow_right),
                            contentDescription = null,
                            tint = Green
                        )
                    }
                }
            }

        }
    }
}