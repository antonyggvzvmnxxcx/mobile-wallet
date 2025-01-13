/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.kyc.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import org.mifospay.core.ui.composableWithPushTransitions
import org.mifospay.feature.kyc.KYCLevel3Screen

const val KYC_LEVEL_3_ROUTE = "kyc_level_3_route"

fun NavController.navigateToKYCLevel3(navOptions: NavOptions? = null) {
    this.navigate(KYC_LEVEL_3_ROUTE, navOptions)
}

fun NavGraphBuilder.kycLevel3Screen(
    navigateBack: () -> Unit,
) {
    composableWithPushTransitions(route = KYC_LEVEL_3_ROUTE) {
        KYCLevel3Screen(
            navigateBack = navigateBack,
        )
    }
}
