package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.compose.LocationAppTheme
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.R
import com.mtmilenkoff.locationapp.utils.getFullName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LocationsList(
    modifier: Modifier = Modifier,
    pagedLocations: Flow<PagingData<Location>>,
    favoriteLocations: List<Location>,
    selectedLocationId: Int?,
    onLocationClick: (Location) -> Unit,
    onFavoriteClick: (Location) -> Unit,
    filterTyping: (String) -> Unit,
    filterText: String,
    onFilterByFavoriteClick: () -> Unit,
) {
    val locations = pagedLocations.collectAsLazyPagingItems()
    Column(modifier.background(MaterialTheme.colorScheme.surface)) {
        FilterBar(
            filterText = filterText,
            filterTyping = filterTyping,
            onFilterByFavoriteClick = onFilterByFavoriteClick
        )
        Box(Modifier.fillMaxSize()) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(
                    count = locations.itemCount,
                    key = locations.itemKey { it.id }
                ) { index ->
                    val location = locations[index]
                    if (location != null) {
                        LocationItem(
                            location = location,
                            onLocationClick = { onLocationClick(location) },
                            onFavoriteClick = { onFavoriteClick(location) },
                            isFavorite = favoriteLocations.contains(location),
                            isSelected = location.id == selectedLocationId
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                }
                if (locations.loadState.append == LoadState.Loading) {
                    item {
                        Row(
                            modifier = modifier
                                .clip(MaterialTheme.shapes.medium)
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant
                                )
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterBar(
    modifier: Modifier = Modifier,
    filterText: String,
    filterTyping: (String) -> Unit,
    onFilterByFavoriteClick: () -> Unit
) {
    Row (
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = filterText,
            onValueChange = filterTyping,
            placeholder = { Text("Filter") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(
                    onClick = { filterTyping("") }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_cancel),
                        contentDescription = "Clear filter"
                    )
                }
            }
        )
        Button(
            onClick = onFilterByFavoriteClick,
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Text("Favorites")
        }
    }

}

@Composable
private fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    onLocationClick: (Location) -> Unit,
    onFavoriteClick: (Location) -> Unit,
    isFavorite: Boolean,
    isSelected: Boolean
) {
    Row(
        modifier = modifier
            .clickable { onLocationClick(location) }
            .clip(MaterialTheme.shapes.medium)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = location.getFullName(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.width(6.dp))
        IconButton(onClick = { onFavoriteClick(location) }) {
            if (isFavorite) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite),
                    tint = Color.Red,
                    contentDescription = "Favorite button",
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_border),
                    tint = Color.Red,
                    contentDescription = "Favorite button",
                )
            }
        }
    }
}

@Preview
@Composable
private fun LocationItemPreview() {
    LocationAppTheme {
        LocationItem(
            location = Location(
                id = 8172,
                country = "Netherlands",
                name = "Robin Fleming",
                coord = Coordinates(lon = 8.9, lat = 10.11)
            ),
            onLocationClick = {},
            onFavoriteClick = {},
            isFavorite = true,
            isSelected = true
        )
    }
}

@Preview
@Composable
private fun LocationsListPreview() {
    LocationAppTheme {
        LocationsList(
            pagedLocations = flow {},
            onLocationClick = {},
            onFavoriteClick = {},
            favoriteLocations = listOf(),
            selectedLocationId = null,
            filterTyping = {},
            filterText = "",
            onFilterByFavoriteClick = {}
        )
    }
}