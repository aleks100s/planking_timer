package com.alextos.plankingtimer.presentation.screens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alextos.plankingtimer.R
import com.alextos.plankingtimer.presentation.common.DeleteButton
import com.alextos.plankingtimer.presentation.common.Label
import com.alextos.plankingtimer.presentation.theme.DarkSurface2
import com.alextos.plankingtimer.presentation.theme.LightSurface2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTimerScreen(onTimerCreated: () -> Unit) {
    val viewModel: CreateTimerViewModel = viewModel()
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
                onClick = {
                    viewModel.saveTimer(onTimerCreated)
                },
                expanded = expandedFab.value,
                icon = { Icon(Icons.Filled.Done, stringResource(id = R.string.save)) },
                text = { Text(text = stringResource(id = R.string.save)) },
                modifier = Modifier.alpha(if (state.isValid()) 1f else 0f)
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = stringResource(id = R.string.new_timer),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                item {
                    TimerTitleSection(
                        title = state.title,
                        onTitleChanged = viewModel::timerTitleChanged
                    )
                }

                itemsIndexed(state.parts) { index, part ->
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .background(
                                color = if (isSystemInDarkTheme()) DarkSurface2 else LightSurface2,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = part.title,
                            onValueChange = { title ->
                                viewModel.timerPartTitleChanged(index, title)
                            },
                            placeholder = {
                                Text(stringResource(id = R.string.title))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(part.timeString().asString())

                            Row {
                                DecreaseButton(enabled = part.secondsCount > 15) {
                                    viewModel.decreaseTimerPart(index = index)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                IncreaseButton(enabled = part.secondsCount < 900) {
                                    viewModel.increaseTimerPart(index = index)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                DeleteButton {
                                    viewModel.deletePart(index = index)
                                }
                            }
                        }
                    }
                }

                item {
                    AddSubTimerSection {
                        viewModel.addNewPart()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerTitleSection(title: String, onTitleChanged: (String) -> Unit) {
    OutlinedTextField(
        value = title,
        onValueChange = onTitleChanged,
        label = {
            Text(stringResource(id = R.string.title, listOf(CreateTimerViewModel.STEP)))
        },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme()) DarkSurface2 else LightSurface2,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    )

    Spacer(Modifier.height(32.dp))
}

@Composable
fun DecreaseButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(painter = painterResource(
            id = R.drawable.ic_baseline_remove_24),
            contentDescription = stringResource(id = R.string.decrease)
        )
    }
}

@Composable
fun IncreaseButton(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(painter = painterResource(
            id = R.drawable.ic_baseline_add_24),
            contentDescription = stringResource(id = R.string.increase)
        )
    }
}

@Composable
fun AddSubTimerSection(onClick: () -> Unit) {
    Spacer(modifier = (Modifier.height(32.dp)))

    Label(
        painter = painterResource(id = R.drawable.ic_baseline_add_24),
        text = stringResource(id = R.string.add_subtimer),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme()) DarkSurface2 else LightSurface2,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .clickable {
                onClick()
            }
    )
}