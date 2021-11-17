package io.github.mohamedisoliman.delicious.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mohamedisoliman.delicious.R
import io.github.mohamedisoliman.delicious.domain.entities.Restaurant
import io.github.mohamedisoliman.delicious.domain.restaurants.HomeViewState
import io.github.mohamedisoliman.delicious.ui.common.PlaceHolderView


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val viewState = viewModel.state().observeAsState()
    val state = viewState.value ?: HomeViewState.Idle
    HomeContent(
        state = state,
        onSearchChange = {
            viewModel.search(it)
        }
    )
}

@Composable
private fun HomeContent(
    state: HomeViewState = HomeViewState.Idle,
    onItemClicked: (String) -> Unit = { },
    onSearchChange: (String) -> Unit,
) {
    Column {

        SearchTopBar(
            onSearchChange = onSearchChange,
            searchText = state.searchText ?: ""
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            LoadingView(modifier = Modifier.align(Alignment.Center), isLoading = state.isLoading)
            ListView(
                modifier = Modifier.align(Alignment.TopCenter),
                list = state.restaurants,
                onItemClicked = onItemClicked
            )
            EmptyView(list = state.restaurants)
            ErrorView(throwable = state.throwable)
        }

    }

}


@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchChange: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        value = searchText,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            onSearchChange(it)
        },
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = ""
                )
            }
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { onSearchClicked() })
    )
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
