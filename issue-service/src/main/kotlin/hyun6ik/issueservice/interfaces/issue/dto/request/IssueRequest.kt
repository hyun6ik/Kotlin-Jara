package hyun6ik.issueservice.interfaces.issue.dto.request

import hyun6ik.issueservice.domain.issue.entity.Issue
import hyun6ik.issueservice.domain.issue.entity.enums.IssuePriority
import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.domain.issue.entity.enums.IssueType

data class IssueRequest(
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
) {
    fun toEntity(userId: Long): Issue {
        return Issue(
            summary = summary,
            description = description,
            userId = userId,
            type = type,
            priority = priority,
            status = status,
        )
    }

}
