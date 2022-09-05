package hyun6ik.issue.interfaces.comment.dto.response

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val body: String,
    val username: String? = null,
)
