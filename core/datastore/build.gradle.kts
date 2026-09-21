/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/kmp-project-template/blob/main/LICENSE
 */
plugins {
    alias(libs.plugins.kmp.library.convention)
}

androidComponents {
    finalizeDsl { ext ->
        ext.withHostTest {
            isReturnDefaultValues = true
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.common)
            implementation(projects.coreBase.common)
            implementation(projects.coreBase.datastore)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.serialization)
            implementation(libs.multiplatform.settings.coroutines)
        }
    }
}

// ── Fork-owned dependency seam (white-label, mirrors `feature-deps.gradle.kts`) ────────────────
// A fork adds its OWN dependencies for this module in `core/datastore/module-deps.gradle.kts` — never in
// this file. That is what lets THIS build file be `owner: template` and FULL-COPY on a template
// sync: the fork's deps live in a file the sync never touches, so a template plugin/version bump
// can no longer drop them and no 3-way merge is needed.
project.file("module-deps.gradle.kts").takeIf { it.exists() }?.let { apply(from = it) }
