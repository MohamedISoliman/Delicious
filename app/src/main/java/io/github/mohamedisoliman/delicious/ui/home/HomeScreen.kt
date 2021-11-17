package io.github.mohamedisoliman.delicious.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.restaurants.HomeViewState
import io.github.mohamedisoliman.delicious.ui.common.PlaceHolderView
import io.github.mohamedisoliman.delicious.R


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val viewState = viewModel.state().observeAsState()
    val state = viewState.value ?: HomeViewState.Idle
    HomeContent(state)
}

@Composable
private fun HomeContent(
    state: HomeViewState = HomeViewState.Idle,
    onItemClicked: (String) -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadingView(modifier = Modifier.align(Alignment.Center), isLoading = state.isLoading)
        ListView(list = state.restaurants, onItemClicked = onItemClicked)
        EmptyView(list = state.restaurants)
        ErrorView(throwable = state.throwable)
    }
}

@Composable
fun EmptyView(modifier: Modifier = Modifier, list: List<Restaurant>?) {
    if (list?.isEmpty() == true) {
        PlaceHolderView(modifier = modifier, icon = Icons.Outlined.Info,
            message = stringResource(R.string.emptyViewMessage))
    }
}

@Composable
fun ErrorView(modifier: Modifier = Modifier, throwable: Throwable?) {
    throwable?.let {
        PlaceHolderView(
            modifier = modifier, icon = Icons.Outlined.Warning,
            message = throwable.message ?: stringResource(R.string.defaultErrorMessage),
            textColor = MaterialTheme.colors.error
        )
    }
}

@Composable
fun LoadingView(modifier: Modifier = Modifier, isLoading: Boolean?) {
    if (isLoading == true) {
        CircularProgressIndicator(
            modifier = modifier
                .size(48.dp)
        )
    }
}

@Composable
fun ListView(
    modifier: Modifier = Modifier,
    list: List<Restaurant>?,
    onItemClicked: (String) -> Unit,
) {
    if (list?.isNotEmpty() == true) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            list.forEachIndexed { index, restaurant ->
                item {
                    PostView(item = restaurant) { id ->
                        if (id != null) {
                            onItemClicked(id)
                        }
                    }
                }
                if (index < list.lastIndex)
                    item {
                        Divider(thickness = 1.dp)
                    }
            }

        }
    }
}

@Composable
fun PostView(
    modifier: Modifier = Modifier,
    item: Restaurant,
    onClick: (String?) -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.name ?: "", style = MaterialTheme.typography.h5)
        Text(text = item.status ?: "", style = MaterialTheme.typography.body2)
    }
}
