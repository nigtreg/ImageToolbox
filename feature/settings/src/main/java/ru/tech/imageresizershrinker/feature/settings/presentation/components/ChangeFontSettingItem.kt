/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2024 T8RIN (Malik Mukhametzyanov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * You should have received a copy of the Apache License
 * along with this program.  If not, see <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package ru.tech.imageresizershrinker.feature.settings.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.tech.imageresizershrinker.core.resources.R
import ru.tech.imageresizershrinker.core.resources.icons.FontFamily
import ru.tech.imageresizershrinker.core.resources.icons.MiniEdit
import ru.tech.imageresizershrinker.core.settings.presentation.model.UiFontFamily
import ru.tech.imageresizershrinker.core.settings.presentation.provider.LocalSettingsState
import ru.tech.imageresizershrinker.core.ui.widget.modifier.ContainerShapeDefaults
import ru.tech.imageresizershrinker.core.ui.widget.preferences.PreferenceItem
import ru.tech.imageresizershrinker.feature.settings.presentation.components.additional.PickFontFamilySheet

@Composable
fun ChangeFontSettingItem(
    onValueChange: (UiFontFamily) -> Unit,
    onAddFont: (Uri) -> Unit,
    onRemoveFont: (UiFontFamily.Custom) -> Unit,
    shape: Shape = ContainerShapeDefaults.centerShape,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
) {
    val settingsState = LocalSettingsState.current
    var showFontSheet by rememberSaveable { mutableStateOf(false) }

    PreferenceItem(
        shape = shape,
        onClick = { showFontSheet = true },
        title = stringResource(R.string.font),
        subtitle = settingsState.font.name ?: stringResource(R.string.system),
        startIcon = Icons.Rounded.FontFamily,
        endIcon = Icons.Rounded.MiniEdit,
        modifier = modifier
    )
    PickFontFamilySheet(
        visible = showFontSheet,
        onDismiss = {
            showFontSheet = false
        },
        onFontSelected = onValueChange,
        onAddFont = onAddFont,
        onRemoveFont = onRemoveFont
    )
}