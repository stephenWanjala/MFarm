package com.wantech.mfarm.mFarm_home.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.mfarm.core.domain.model.Evaluation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MilkCollectionStatus(modifier: Modifier = Modifier) {
    Table(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TableRow(
            table = listOf(
                Evaluation(
                    farmer = "James",
                    time = LocalDateTime.now(),
                    quantity = 50.67,
                    status = "Fresh",
                    grossBilling = 12000.89
                ),
                Evaluation(
                    farmer = "James",
                    time = LocalDateTime.now().plusMinutes(15),
                    quantity = 53.67,
                    status = "Fresh",
                    grossBilling = 12000.89
                ),
            )
        )
    }
}


@Composable
fun Table(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(4.dp)
            .border(
                shape = RectangleShape,
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            ),
        content = content
    )
}

@Composable
fun TableRow(modifier: Modifier = Modifier, table: List<Evaluation>) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Divider()
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "#")
            Text(text = "Farmer")
            Text(text = "Time")
            Text(text = "Quantity")
            Text(text = "Status")
            Text(text = "Gross Billing")

        }
        Divider()
        table.forEachIndexed { index, milkEvaluation ->
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${index + 1}")
                Text(text = milkEvaluation.farmer)
                Text(text = milkEvaluation.time.formatTime())
                Text(text = milkEvaluation.quantity.toString())
                Text(text = milkEvaluation.status)
                Text(text = milkEvaluation.grossBilling.toString())
            }
            Divider()
        }
    }
}




@Preview
@Composable
fun RowPrev() {
    MilkCollectionStatus()
}


fun LocalDateTime.formatTime(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return this.format(formatter)
}

