package de.codecentric.hikaku

import de.codecentric.hikaku.reporter.CommandLineReporter
import de.codecentric.hikaku.reporter.Reporter

data class HikakuConfig(
        val ignorePaths: Set<String> = emptySet(),
        val ignoreHttpMethodHead: Boolean = false,
        val ignoreHttpMethodOptions: Boolean = false,
        val reporter: Reporter = CommandLineReporter()
)