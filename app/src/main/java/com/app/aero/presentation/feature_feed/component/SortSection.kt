package com.app.aero.presentation.feature_feed.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val arrSortCategories = listOf(
    SortChipData("A-Z", 1, SortBy.ASCE , SortCategories.AZ),
    SortChipData("Price", 2, SortBy.ASCE, SortCategories.PRICE),
    SortChipData("Change %", 3, SortBy.ASCE, SortCategories.CHANGE),
)
data class SortChipData(
    val name: String,
    val id: Int,
    val sort: SortBy,
    val sortId: SortCategories
)

enum class SortCategories{
    AZ,PRICE,CHANGE
}
enum class SortBy{
    ASCE,
    DESC,
}

@Composable
fun SortSection(
    selectedSort : SortChipData?,
    arrListOfSort : List<SortChipData>,
    onSelectedSort: (SortChipData) -> Unit,
    onClearChipSelection: () -> Unit = {}
) {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

        Text(
            "SORT BY:",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp) , verticalAlignment = Alignment.CenterVertically) {
            arrListOfSort.forEach {
                SortChip(it, it.id == selectedSort?.id,{
                    onSelectedSort(it)
                },onClearChipSelection)
            }
        }
    }
}