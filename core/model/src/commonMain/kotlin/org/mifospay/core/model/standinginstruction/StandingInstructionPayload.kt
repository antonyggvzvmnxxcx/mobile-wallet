/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.model.standinginstruction

import kotlinx.serialization.Serializable
import org.mifospay.core.common.Parcelable
import org.mifospay.core.common.Parcelize

@Serializable
@Parcelize
data class StandingInstructionPayload(
    val fromOfficeId: Long = 0,
    val fromClientId: Long = 0,
    val fromAccountType: Long = 0,
    val fromAccountId: Long = 0,

    val toOfficeId: Long = 0,
    val toClientId: Long = 0,
    val toAccountType: Long = 0,
    val toAccountId: Long = 0,

    val name: String = "",
    val amount: String = "",
    val transferType: Long = 0,
    val instructionType: Long = 0,
    val priority: Long = 0,
    val status: Long = 0,
    val recurrenceType: Long = 0,
    val recurrenceFrequency: Long = 0,
    val recurrenceInterval: String = "",

    val locale: String = "",
    val validFrom: String = "",
    val validTill: String = "",
    val dateFormat: String = "",
    val monthDayFormat: String = "",
    val recurrenceOnMonthDay: String = "",
) : Parcelable

fun StandingInstructionPayload.toSIUploadPayload(): SIUpdatePayload {
    return SIUpdatePayload(
        priority = priority,
        status = status,
        locale = locale,
        validFrom = validFrom,
        validTill = validTill,
        dateFormat = dateFormat,
    )
}
