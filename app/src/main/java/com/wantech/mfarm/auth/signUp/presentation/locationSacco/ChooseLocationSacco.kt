package com.wantech.mfarm.auth.signUp.presentation.locationSacco

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.mfarm.auth.signUp.SignUpPageParts
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.core.presentation.components.SaccoSpinner
import com.wantech.mfarm.ui.theme.MFarmTheme

@Composable
fun ChooseLocationSacco(modifier: Modifier = Modifier,pagePart: SignUpPageParts=SignUpPageParts.LocationAndSacco) {
    val saccos = listOf(
        Sacco("mAZE MILK", "0700000000", "sacco1@one.com", "Nairobi"),
        Sacco("KitLida Milk", "0700000000", "2@saco.com", "Kisumu"),
        Sacco(name = "Lato Dairy", phone = "0700000000", email = "latto@mail.com", location = "Nairobi"),
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth(),
//                .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.elevatedCardColors()
        ) {
            Text(
                text = "Select Sacco",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier =Modifier.height(8.dp))

            SaccoSpinner(list =saccos , isEnabled = {true} ,
            onSelectionChanged = {sacco ->

            })

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SaccoLocPreview() {
    MFarmTheme {
        ChooseLocationSacco()
    }
}