package com.alextos.plankingtimer.presentation.screens.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.domain.model.main.TimerQueue
import com.alextos.plankingtimer.presentation.common.DeleteButton
import com.alextos.plankingtimer.presentation.common.Label
import com.alextos.plankingtimer.presentation.common.ScreenTitle
import com.alextos.plankingtimer.presentation.theme.DarkSurface2
import com.alextos.plankingtimer.presentation.theme.LightSurface2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onTimerSelected: (TimerQueue) -> Unit,
    createNewTimer: () -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.state.value

    val listState = rememberLazyListState()
// The FAB is initially expanded. Once the first visible item is past the first item we
// collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
    val expandedFab = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = createNewTimer,
                expanded = expandedFab.value,
                icon = { Icon(Icons.Filled.Add, stringResource(id = R.string.new_timer)) },
                text = { Text(text = stringResource(id = R.string.new_timer)) },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ScreenTitle(title = stringResource(id = R.string.my_timers))

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = if (isSystemInDarkTheme()) DarkSurface2 else LightSurface2,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                itemsIndexed(state.timers) { index, timer ->
                    TimerListItem(
                        timer = timer,
                        modifier = Modifier
                            .clickable {
                                onTimerSelected(timer)
                            }
                    ) {
                        viewModel.deleteTimer(timer)
                    }
                    if (index < state.timers.lastIndex) {
                        Divider(color = Color.LightGray)
                    }
                }
            }
        }
    }


}

@Composable
fun TimerListItem(
    timer: TimerQueue,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Label(
                painter = painterResource(id = R.drawable.ic_baseline_title_24),
                text = timer.title
            )
            Spacer(modifier = Modifier.height(8.dp))
            Label(
                painter = painterResource(id = R.drawable.ic_baseline_timer_24),
                text = stringResource(id = R.string.seconds, timer.totalSeconds())
            )
        }

        DeleteButton {
            onDelete()
        }
    }
}