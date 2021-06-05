package net.bg.satish_assignment.data.models

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)