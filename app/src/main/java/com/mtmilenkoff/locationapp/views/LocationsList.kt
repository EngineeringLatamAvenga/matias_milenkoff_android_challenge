package com.mtmilenkoff.locationapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mtmilenkoff.domain.models.Coordinates
import com.mtmilenkoff.domain.models.Location
import com.mtmilenkoff.locationapp.R
import com.mtmilenkoff.locationapp.ui.theme.LocationAppTheme
import com.mtmilenkoff.locationapp.utils.getFullName

@Composable
internal fun LocationsList(
    modifier: Modifier = Modifier,
    locations: List<Location>,
    onLocationClick: (Location) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(locations) { location ->
            Spacer(Modifier.height(4.dp))
            LocationItem(
                location = location,
                addFavorite = { onLocationClick(location) }
            )
        }
    }
}

@Composable
private fun LocationItem(
    modifier: Modifier = Modifier,
    location: Location,
    addFavorite: () -> Unit = {}
) {
    Row(
        modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.surface)
        .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = location.getFullName(), maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.width(6.dp))
        IconButton(onClick = addFavorite) {
            Icon(
                painter = painterResource(R.drawable.ic_favorite),
                tint = Color.Red,
                contentDescription = "Favorite button",
            )
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
            )
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
            onLocationClick = {}
        )
    }
}