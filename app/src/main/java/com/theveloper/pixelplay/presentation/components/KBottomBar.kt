package com.theveloper.pixelplay.presentation.components





import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theveloper.pixelplay.R

@Composable
fun kBottomBar(
    onSelectTab: (kBottomBatTab) -> Unit
){
    var selectedTab : MutableState<kBottomBatTab>  =
        remember { mutableStateOf(kBottomBatTab.HOME) }
    Row(modifier = Modifier.fillMaxWidth()
        .height(64.dp)
        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape =  RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp,topStart = 12.dp, topEnd = 12.dp))
        .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {
        SelectionTab("Home",R.drawable.home_24_rounded_filled,selectedTab.value == kBottomBatTab.HOME,
            onClick = {selectedTab.value =
            kBottomBatTab.HOME})
        SelectionTab("Search",R.drawable.rounded_search_24,selectedTab.value == kBottomBatTab.SEARCH,onClick = {selectedTab.value =
            kBottomBatTab.SEARCH})
        SelectionTab("Library",R.drawable.round_library_music_24,selectedTab.value == kBottomBatTab.LIBRARY,onClick = {selectedTab.value =
            kBottomBatTab.LIBRARY})
    }
}

@Composable
private fun SelectionTab(title: String,iconRes: Int,selected: Boolean, onClick: () -> Unit){
    val color = animateColorAsState(if(selected) MaterialTheme.colorScheme.secondary.copy(alpha = 0.65f)
    else MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f))
    val iconColor = animateColorAsState(if(selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.95f)
    else MaterialTheme.colorScheme.secondary.copy(alpha = 0.65f))
    val fontSize = animateIntAsState(if(selected) 22 else 18)
    Row(modifier = Modifier
        .background(color = color.value, shape = CircleShape)
        .clip(shape = CircleShape)
        .clickable(onClick = onClick)
        .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically) {
        Box() {
            Icon(modifier = Modifier.size(36.dp),painter = painterResource(iconRes),contentDescription = null, tint = iconColor.value)
        }
        AnimatedVisibility(selected) {
            Row() {
                Spacer(modifier = Modifier.width(6.dp))
                Text(title,fontSize = fontSize.value.sp, color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.95f))
            }
        }
    }
}

enum class kBottomBatTab{
    HOME,
    SEARCH,
    LIBRARY
}