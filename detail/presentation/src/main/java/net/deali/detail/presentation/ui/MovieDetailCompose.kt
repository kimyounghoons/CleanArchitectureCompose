package net.deali.detail.presentation.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.deali.detail.domain.entity.DetailEntity

@Composable
fun MovieDetailCompose(
    item: DetailEntity,
    scroll: ScrollState,
    headerHeight: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = item.imageUrl, contentDescription = "imageUrl"
            )
            Text(text = item.overView)
        }
        ProductionCompanyListCompose(item.productionCompanies)
        Spacer(Modifier.height(1000.dp))
    }
}

@Composable
fun ProductionCompanyListCompose(productionCompanies: List<DetailEntity.ProductionCompany>) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "제작사", fontSize = 16.sp, fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            productionCompanies.forEach {
                key(it) {
                    ProductionCompanyCompose(it)
                }
            }
        }
    }
}

@Composable
fun ProductionCompanyCompose(productionCompany: DetailEntity.ProductionCompany) {
    Column {
        AsyncImage(
            model = productionCompany.imageUrl, contentDescription = "productionCompany ImageUrl"
        )
        Text(text = productionCompany.name)
    }
}