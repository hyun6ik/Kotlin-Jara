package hyun6ik.issue.domain.issue.entity.enums

enum class IssueType {
    BUG, TASK;

    companion object {
       operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}
