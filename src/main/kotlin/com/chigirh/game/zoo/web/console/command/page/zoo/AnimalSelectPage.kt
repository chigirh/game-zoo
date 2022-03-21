package com.chigirh.game.zoo.web.console.command.page.zoo

import com.chigirh.game.zoo.common.system.SystemProfile
import com.chigirh.game.zoo.common.system.UserProfile
import com.chigirh.game.zoo.common.system.UserSettings
import com.chigirh.game.zoo.domain.model.animal.Animal
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalUseCase
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalUseCase.Mode
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase
import com.chigirh.game.zoo.domain.usecase.cage.PutAnimalUseCase
import com.chigirh.game.zoo.web.console.command.*
import com.chigirh.game.zoo.web.console.command.logic.LogicBase
import org.springframework.stereotype.Component
import com.chigirh.game.zoo.web.console.command.page.PageBase
import com.chigirh.game.zoo.domain.usecase.animal.FetchAnimalUseCase.InputImpl as FetchAnimalInput
import com.chigirh.game.zoo.domain.usecase.cage.FetchCageUseCase.InputImpl as FetchCageInput
import com.chigirh.game.zoo.domain.usecase.cage.PutAnimalUseCase.InputImpl as PutAnimalInput


@Component("p_zoo_0003")
class AnimalSelectPage(
    override val systemProfile: SystemProfile,
    override val userProfile: UserProfile,
    override val userSettings: UserSettings,
    override val commandManager: CommandManager,
    private val fetchCageUseCase: FetchCageUseCase,
    private val fetchAnimalUseCase: FetchAnimalUseCase,
    private val putAnimalUseCase: PutAnimalUseCase,
    private val factory: ChoiceFactory,
) : PageBase(systemProfile, userProfile, userSettings, commandManager) {
    override fun description(input: String): String {
        val cage = fetchCageUseCase(FetchCageInput(input.toInt())).models.first()
        return """
            どの動物を檻に入れますか？
            選択した檻:${cage.name},檻に入れられる動物数:${cage.getStorableSize()},檻に入れられる重量:${cage.getStorableWeight()}"
        """.trimMargin()
    }


    override fun getChoiceBox(input: String): List<Choice> {
        val cage = fetchCageUseCase(FetchCageInput(input.toInt())).models.first()

        val logic = object : LogicBase() {
            override fun logic(input: String) {
                val putAnimal = PutAnimalInput(
                    cageId = cage.id,
                    animalId = input.toInt()
                )
                putAnimalUseCase(putAnimal)
            }

            override fun getId() = CommandId.L_ZOO_ANIMAL_PUT
        }

        val fetchAnimal = FetchAnimalInput(mode = Mode.NOT_IN_CAGE)
        val animals = fetchAnimalUseCase(fetchAnimal).models
        return animals.mapIndexed { idx, animal -> this.createChoice(idx, animal, logic) }.toList()
    }

    private fun createChoice(idx: Int, animal: Animal, logic: Command) = factory(
        index = idx,
        value = "${animal.id}",
        label = "id:${animal.id},name:${animal.name},体重:${animal.weight}kg",
        command = logic,
    )

    override fun getId() = CommandId.P_ZOO_ANIMAL_SELECT
}