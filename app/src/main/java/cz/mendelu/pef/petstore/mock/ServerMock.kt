package cz.mendelu.pef.petstore.mock

import cz.mendelu.pef.petstore.model.Category
import cz.mendelu.pef.petstore.model.Pet
import kotlin.random.Random

object ServerMock {

    enum class Categories {
        Dog,
        Cat,
        Monkey,
        MiniPig,
        Hamster,
        Hedgehog,
        Fish
    }

    private fun mockCategory(category: Categories) = Category(
        id = Random.nextLong(),
        name = category.name
    )

    private val dog = Pet(
        id = 1L,
        name = "Benny",
        tags = emptyList(),
        category = mockCategory(Categories.Dog),
        photoUrls = listOf("https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=0.752xw:1.00xh;0.175xw,0&resize=1200:*"),
        status = "",
        weightKg = 12.0,
    )

    private val cat = Pet(
        id = 2L,
        name = "Afrodita",
        tags = emptyList(),
        category = mockCategory(Categories.Cat),
        photoUrls = listOf("https://image.petmd.com/files/styles/978x550/public/2023-06/flehmen-response-cat.jpg"),
        status = "",
        weightKg = 4.0,
    )

    private val monkey = Pet(
        id = 3L,
        name = "Laura",
        tags = emptyList(),
        category = mockCategory(Categories.Monkey),
        photoUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Crab-eating_macaque_%28Macaca_fascicularis%29_Phang_Nga.jpg/640px-Crab-eating_macaque_%28Macaca_fascicularis%29_Phang_Nga.jpg"),
        status = "",
        weightKg = 7.0,
    )

    private val miniPig = Pet(
        id = 4L,
        name = "Paris",
        tags = emptyList(),
        category = mockCategory(Categories.MiniPig),
        photoUrls = listOf("https://www.zoohit.cz/magazin/wp-content/uploads/2022/04/Male-prasatko-na-louce.jpeg"),
        status = "",
        weightKg = 6.0,
    )

    private val hamster = Pet(
        id = 5L,
        name = "Theo",
        tags = emptyList(),
        category = mockCategory(Categories.Hamster),
        photoUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/PhodopusSungorus_1.jpg/640px-PhodopusSungorus_1.jpg"),
        status = "",
        weightKg = 0.5,
    )

    private val hedgehog = Pet(
        id = 6L,
        name = "Carlitto",
        tags = emptyList(),
        category = mockCategory(Categories.Hedgehog),
        photoUrls = listOf("https://images.ctfassets.net/rt5zmd3ipxai/4Z1RIc1p8375Fb1JPvVhcg/f39b175c5b030bd6e2df81fa1b9fb0de/ServiceCards-Hedgehog.png?fit=fill&fm=webp&h=578&w=1070&q=72,%20https://images.ctfassets.net/rt5zmd3ipxai/4Z1RIc1p8375Fb1JPvVhcg/f39b175c5b030bd6e2df81fa1b9fb0de/ServiceCards-Hedgehog.png?fit=fill&fm=webp&h=1156&w=2140&q=72"),
        status = "",
        weightKg = 1.3,
    )

    private val fish = Pet(
        id = 7L,
        name = "Leon",
        tags = emptyList(),
        category = mockCategory(Categories.Fish),
        photoUrls = listOf("https://home.adelphi.edu/~ve21375/betta%20fish%20home%201.jpg"),
        status = "",
        weightKg = 0.01,
    )

    val all = listOf(
        dog,
        cat,
        monkey,
        miniPig,
        hamster,
        hedgehog,
        fish
    )
}