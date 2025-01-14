package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.LocationAppTheme
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.R
import com.mtmilenkoff.locationapp.utils.getFullName

@Composable
internal fun LocationsList(
    modifier: Modifier = Modifier,
    locations: List<Location>,
    favoriteLocations: List<Location>,
    onLocationClick: (Location) -> Unit,
    onFavoriteClick: (Location) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(locations) { location ->
            Spacer(Modifier.height(4.dp))
            LocationItem(
                location = location,
                onLocationClick = { onLocationClick(location) },
                onFavoriteClick = { onFavoriteClick(location) },
                isFavorite = favoriteLocations.contains(location)
            )
        }
    }
}

@Composable
private fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    onLocationClick: (Location) -> Unit,
    onFavoriteClick: (Location) -> Unit,
    isFavorite: Boolean
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onLocationClick(location) },
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
            isFavorite = true
        )
    }
}

@Preview
@Composable
private fun LocationsListPreview() {
    LocationAppTheme {
        LocationsList(
            locations = listOf(
                Location(
                    id = 8172,
                    country = "Netherlands",
                    name = "Robin Fleming",
                    coord = Coordinates(lon = 8.9, lat = 10.11)
                ),
                Location(
                    id = 8172,
                    country = "Netherlands",
                    name = "Robin Fleming",
                    coord = Coordinates(lon = 8.9, lat = 10.11)
                )
            ),
            onLocationClick = {},
            onFavoriteClick = {},
            favoriteLocations = listOf()
        )
    }
}