package hyun6ik.issueservice.domain.issue.enums

enum class IssueStatus {

    TODO, IN_PROGRESS, RESOLVED;

    companion object {
        operator fun invoke(status: String) = valueOf(status.uppercase())
    }
}