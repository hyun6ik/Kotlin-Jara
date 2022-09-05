package hyun6ik.issue.domain.issue.entity.enums

enum class IssuePriority {

    LOW, MEDIUM, HIGH;

    companion object {
        operator fun invoke(priority: String) = valueOf(priority.uppercase())
    }
}
