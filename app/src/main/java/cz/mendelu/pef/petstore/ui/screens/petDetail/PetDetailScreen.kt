package cz.mendelu.pef.petstore.ui.screens.petDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import cz.mendelu.pef.petstore.model.Pet
import cz.mendelu.pef.petstore.ui.elements.BaseScreen
import cz.mendelu.pef.petstore.ui.theme.basicTextColor

const val TestTagPetDetailName = "TestTagPetDetailName"
const val TestTagPetDetailWeightText = "TestTagPetDetailWeightText"
const val TestTagPetDetailRadioButtonKilograms = "TestTagPetDetailRadioButtonKilograms"
const val TestTagPetDetailRadioButtonPounds = "TestTagPetDetailRadioButtonPounds"

@Composable
fun PetDetailScreen(
    viewModel: PetDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.petDetailUiState.collectAsState()

    BaseScreen(
        topBarText = "Pet detail",
        drawFullScreenContent = false,
        showLoading = false,
        placeholderScreenContent = null
    ) {
        val pet = uiState.data
        if (pet != null) {
            PetDetailScreenContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                pet = pet
            )
        } else {
            Text(
                text = "Pet not found!",
                color = basicTextColor()
            )
        }
    }
}

@Composable
private fun PetDetailScreenContent(
    pet: Pet,
    modifier: Modifier = Modifier,
) {
    val coverUrl = pet.photoUrls?.firstOrNull()

    var weightUnit: WeightUnitsEnum by remember(Unit) {
        mutableStateOf(WeightUnitsEnum.KILOGRAMS)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        coverUrl?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds,
                model = coverUrl,
                contentDescription = "Loaded image"
            )
        }
        Text(
            modifier = Modifier.testTag(TestTagPetDetailName),
            text = pet.name.orEmpty(),
            color = basicTextColor()
        )
        Text(
            modifier = Modifier.testTag(TestTagPetDetailWeightText),
            text = "Weight: ${weightUnit.formatWeight(weightKgs = pet.weightKg ?: 0.0)}",
            color = basicTextColor()
        )
        Divider(modifier = Modifier.fillMaxWidth())
        WeightUnitsEnum.values().forEach { unit ->
            WeightUnitRadioButton(
                unit = unit,
                selectedUnit = weightUnit,
                onClick = { weightUnit = unit },
                testTag = when (unit) {
                    WeightUnitsEnum.KILOGRAMS -> TestTagPetDetailRadioButtonKilograms
                    WeightUnitsEnum.POUNDS -> TestTagPetDetailRadioButtonPounds
                }
            )
        }
    }
}

@Composable
private fun WeightUnitRadioButton(
    unit: WeightUnitsEnum,
    selectedUnit: WeightUnitsEnum,
    testTag: String,
    onClick: () -> Unit
) {
    val isItemSelected: (WeightUnitsEnum) -> Boolean = { selectedUnit == it }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .selectable(
                selected = isItemSelected(unit),
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(8.dp)
            .testTag(testTag),
    ) {
        RadioButton(
            selected = isItemSelected(unit),
            onClick = null
        )
        Text(
            modifier = Modifier.padding(start = 24.dp),
            text = unit.displayedOption,
            color = basicTextColor()
        )
    }
}

