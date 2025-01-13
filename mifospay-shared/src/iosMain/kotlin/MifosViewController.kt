/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
import androidx.compose.ui.window.ComposeUIViewController
import org.mifospay.shared.MifosPaySharedApp
import org.mifospay.shared.di.initKoin

@Suppress("ktlint:standard:function-naming")
fun MifosViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    },
) {
    MifosPaySharedApp()
}
