/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jetsnack.ui.home

import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetsnack.R
import com.example.jetsnack.ui.SnackViewModel
import com.example.jetsnack.ui.components.JetsnackScaffold
import com.example.jetsnack.ui.theme.JetsnackTheme
@Composable
fun Profile(
    viewModel: SnackViewModel,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val user = viewModel.getLoggedInUser()
    JetsnackScaffold(
        bottomBar = {
            JetsnackBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.PROFILE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            IconButton(onClick = { viewModel.logOut() }, modifier = Modifier.align(Alignment.End)) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout")
            }
            // Profile Information Text
            Text(
                text = stringResource(id = R.string.profile_information),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                color = JetsnackTheme.colors.brand,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            // Name
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.name) + ":",
                    style = MaterialTheme.typography.h6,
                    color = JetsnackTheme.colors.brand,
                    modifier = Modifier.padding(8.dp)
                )
                if (user != null) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Email
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.email) + ":",
                    style = MaterialTheme.typography.h6,
                    color = JetsnackTheme.colors.brand,
                    modifier = Modifier.padding(8.dp)
                )
                if (user != null) {
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Phone number
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.phone_number) + ":",
                    style = MaterialTheme.typography.h6,
                    color = JetsnackTheme.colors.brand,
                    modifier = Modifier.padding(8.dp)
                )
                if (user != null) {
                    Text(
                        text = user.phone,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Adress
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(id = R.string.address) + ":",
                    style = MaterialTheme.typography.h6,
                    color = JetsnackTheme.colors.brand,
                    modifier = Modifier.padding(8.dp)
                )
                if (user != null) {
                    Text(
                        text = user.address,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Favorite snacks Text
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                // Favorite snacks Text
                Text(
                    text = stringResource(id = R.string.favorite_snacks) + ":",
                    style = MaterialTheme.typography.h6,
                    color = JetsnackTheme.colors.brand,
                    modifier = Modifier.padding(8.dp)
                )

                // Column to display favorite snacks as a list
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    val favoriteSnacks = user?.favoriteSnacks
                    // Iterate through the list of favorite snacks and display each one as a Text composable
                    favoriteSnacks?.forEach { snack ->
                        Text(
                            text = snack,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }

        }
    }
}


