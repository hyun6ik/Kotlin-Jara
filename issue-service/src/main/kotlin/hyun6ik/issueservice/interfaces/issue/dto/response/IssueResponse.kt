package hyun6ik.issueservice.interfaces.issue.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import hyun6ik.issueservice.domain.comment.entity.Comment
import hyun6ik.issueservice.domain.issue.entity.Issue
import hyun6ik.issueservice.domain.issue.entity.enums.IssuePriority
import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.domain.issue.entity.enums.IssueType
import hyun6ik.issueservice.global.utils.toResponse
import hyun6ik.issueservice.interfaces.comment.dto.response.CommentResponse
import java.time.LocalDateTime

data class IssueResponse(
    val id: Long,
    val comments: List<CommentResponse> = emptyList(),
    val userId: Long,
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
) {
    companion object {
        fun of(issue: Issue) =
            with(issue) {
                IssueResponse(
                    id = id!!,
                    comments = comments.toResponseList(),
                    userId = userId,
                    summary = summary,
                    description = description,
                    type = type,
                    priority = priority,
                    status = status,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }
    }
}