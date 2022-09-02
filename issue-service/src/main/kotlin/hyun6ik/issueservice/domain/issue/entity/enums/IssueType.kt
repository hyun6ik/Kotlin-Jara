package hyun6ik.issueservice.domain.issue.entity.enums

enum class IssueType {
    BUG, TASK;

    companion object {
       operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}