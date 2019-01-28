package de.codecentric.hikaku.matcher

data class PreCheckListSizeMatchResult(
        private val relatesTo: String,
        private val specificationListSize: Int,
        private val implementationListSize: Int
) : MatchResultGroup {

    override val matchResults: List<MatchResult<Int>> = listOf(
            MatchResult(
                    relatesTo,
                    specificationListSize,
                    implementationListSize
            )
    )
}