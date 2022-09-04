package hyun6ik.issueservice.global.utils

import hyun6ik.issueservice.domain.comment.entity.Comment
import hyun6ik.issueservice.interfaces.comment.dto.response.CommentResponse

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    userId = userId,
    body = body,
    username = username,
)