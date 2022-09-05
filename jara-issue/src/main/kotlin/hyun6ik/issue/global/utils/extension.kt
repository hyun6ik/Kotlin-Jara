package hyun6ik.issue.global.utils

import hyun6ik.issue.domain.comment.entity.Comment
import hyun6ik.issue.interfaces.comment.dto.response.CommentResponse

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    body = body,
    username = username,
)
