/*
 * Copyright 2025 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
plugins {
    alias(libs.plugins.kmp.library.convention)
    // Fork addition: AssetRepositoryImpl.kt's fallback (composeResources/files/countries.json)
    // needs the `compose` extension for org.jetbrains.compose.resources.ExperimentalResourceApi.
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

androidComponents {
    finalizeDsl { ext ->
        ext.withHostTest {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.database)
            implementation(projects.coreBase.database)
            implementation(projects.coreBase.datastore)
            implementation(projects.coreBase.store)
            // api: re-export the relocated sync/monitor infra (NetworkMonitor, Synchronizer,
            // SyncManager, TimeZoneMonitor) so existing core/data consumers (features, sync,
            // cmp-android) keep the transitive visibility they had when it lived in core/data.
            api(projects.coreBase.data)
            // api: core/data is auto-wired into every feature module via cmp.feature.convention
            // (commonMainImplementation project(":core:data")) — re-exporting these means feature
            // modules that read domain types (org.mifospay.core.model.*), UserPreferencesRepository
            // (org.mifospay.core.datastore.*), or network DTOs (org.mifospay.core.network.model.*)
            // get them for free instead of every feature module repeating its own
            // implementation(projects.core.{model,datastore,network}) — features must never depend
            // on core/network directly; core/data is the sole consumer/re-exporter of it.
            implementation(projects.core.firebase)

            implementation(projects.coreBase.common)
            implementation(projects.coreBase.network)
            api(projects.core.store)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            api(libs.cmp.network.monitor)

        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.tracing.ktx)
            implementation(libs.koin.android)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.turbine)
            implementation(libs.koin.test)
        }
    }
}

// ── Fork-owned dependency seam (white-label, mirrors `feature-deps.gradle.kts`) ────────────────
// A fork adds its OWN dependencies for this module in `core/data/module-deps.gradle.kts` — never in
// this file. That is what lets THIS build file be `owner: template` and FULL-COPY on a template
// sync: the fork's deps live in a file the sync never touches, so a template plugin/version bump
// can no longer drop them and no 3-way merge is needed.
//
// String `"commonMainImplementation"(...)` notation is used in the seam, not the type-safe
// `libs.`/`projects.` accessors: those are NOT generated for `apply(from = ...)` script plugins.
//
// Guarded like feature-deps: a fork that adopted the template BEFORE this seam existed may not have
// the file yet, and an unconditional apply would fail the whole configuration.
project.file("module-deps.gradle.kts").takeIf { it.exists() }?.let { apply(from = it) }
