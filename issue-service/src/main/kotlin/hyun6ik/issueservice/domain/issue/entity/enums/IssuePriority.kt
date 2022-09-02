package hyun6ik.issueservice.domain.issue.entity.enums

enum class IssuePriority {

    LOW, MEDIUM, HIGH;

    companion object {
        operator fun invoke(priority: String) = valueOf(priority.uppercase())
    }
}