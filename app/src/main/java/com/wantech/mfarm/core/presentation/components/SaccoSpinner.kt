package com.wantech.mfarm.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wantech.mfarm.core.domain.model.Sacco
import com.wantech.mfarm.ui.theme.MFarmTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaccoSpinner(
    modifier: Modifier = Modifier,
    list: List<Sacco> =  listOf(
        Sacco(
            name = "Kisumu Sacco",
            phone = "0712345678",
            email = "kisumusacco@mail.com",
            location = "Kisumu",
        ),
        Sacco(
            name = "Nairobi Sacco",
            phone = "0712345678",
            email = "sacoo@nairobi.co.ke",
            location = "Nairobi",
        )
    ),
    preselected: Sacco =Sacco(
        name = "Sacco",
        email = "examplemail.com",
        phone = "09876456",
        location = "Location"
    ) ,
    onSelectionChanged: (sacco: Sacco) -> Unit,
    isEnabled: () -> Boolean
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value



    Card(

        modifier = modifier
            .clickable {
                expanded = !expanded
            }
            .padding(16.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
        onClick = { expanded = !expanded },
        enabled = isEnabled()

    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = "${selected.name} ~ ${selected.location}",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                list.forEach { listEntry ->


                    DropdownMenuItem(
                        onClick = {
                            selected = listEntry
                            expanded = false
                            onSelectionChanged(selected)
                        },
                        text = {
                            Text(
                                text = listEntry.name,
                                modifier = Modifier
                                    //.wrapContentWidth()  //optional instead of fillMaxWidth
//                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            )
                        },
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpinnerSample_Preview() {
    MFarmTheme {
        val spinnerData =
            listOf(
                Sacco(
                    name = "Kisumu Sacco",
                    phone = "0712345678",
                    email = "kisumusacco@mail.com",
                    location = "Kisumu",
                ),
                Sacco(
                    name = "Nairobi Sacco",
                    phone = "0712345678",
                    email = "sacoo@nairobi.co.ke",
                    location = "Nairobi",
                )
            )

        SaccoSpinner(
            modifier = Modifier.fillMaxWidth(),
            spinnerData,
            preselected = spinnerData.first(),
            onSelectionChanged = { },
            isEnabled = {
                true
            }
        )
    }
}

