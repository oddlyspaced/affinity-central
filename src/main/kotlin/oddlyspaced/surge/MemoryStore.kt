package oddlyspaced.surge

import io.github.serpro69.kfaker.Faker
import oddlyspaced.surge.provider.data.Location
import oddlyspaced.surge.provider.data.PhoneNumber
import oddlyspaced.surge.provider.data.Provider
import oddlyspaced.surge.provider.data.Service
import oddlyspaced.surge.provider.data.parameter.SearchParameter

val providers = arrayListOf<Provider>()

val sourcePoint = Location(26.882883,80.925594)
val services = arrayListOf<Service>()
val serviceRankMap = hashMapOf<String, Int>()

// todo: improve and make this piece of logic more efficient
fun generateData() {
    val fake = Faker()
    for (i in 0..25) {
        providers.add(
            Provider(
                (providers.size + 1),
                fake.name.name(),
                PhoneNumber(
                    fake.phoneNumber.countryCode.code(),
                    fake.phoneNumber.phoneNumber()
                ),
                Location(
                    sourcePoint.lat + ((0..100).random() / 1000.0),
                    sourcePoint.lon + ((0..100).random() / 1000.0),
                ),
                (1..(1..10).random()).mapTo(arrayListOf()) {
                    fake.job.field()
                }.flat()
            )
        )
    }
    generateServiceTags()
}

private fun generateServiceTags() {
    providers.forEach { provider ->
        provider.services.forEach { service ->
            serviceRankMap[service]?.let { count ->
                serviceRankMap[service] = count + 1
            } ?: run {
                serviceRankMap[service] = 1
            }
        }
    }
    serviceRankMap.toList().sortedBy {
        it.second
    }.reversed().let { rankedServiceList ->
        services.clear()
        rankedServiceList.mapTo(services) {
            Service(services.size.plus(1), it.first, it.second)
        }
    }
}

/**
 * handles search logic
 * copies over the provider list and cuts them short based on the parameters
 */
fun search(params: SearchParameter): ArrayList<Provider> {
    val results = arrayListOf<Provider>().apply { addAll(providers) }
    results.removeAll { provider ->
        doesProviderMatch(provider, params)
    }
    return results
}

/**
 * checks if a provided provider instance qualifies with the provided search params
 * todo: add logic
 */
private fun doesProviderMatch(provider: Provider, params: SearchParameter): Boolean {
    return true
}

fun ArrayList<String>.flat(): ArrayList<String> {
    val flattened = arrayListOf<String>()
    this.forEach {
        if (!flattened.contains(it)) {
            flattened.add(it)
        }
    }
    return flattened
}