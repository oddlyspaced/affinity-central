package oddlyspaced.surge

/**
 * Configuration class for server
 * todo: make this class load from a config file or command line params
 */
class AffinityConfiguration {
    companion object {
        // netty server port
        const val APPLICATION_PORT = 5030
        // should dummy data be generated
        const val GENERATE_DUMMY_DATA = true
        // should data be restored from providers.json on first start
        const val RESTORE_DATA_ON_BOOT = false
        // should data be stored to providers.json
        const val SAVE_DATA_TO_STORAGE = true
        // interval between write to providers.json file
        const val SAVE_DATA_INTERVAL = 1000 * 60 * 60L // ms time
    }
}