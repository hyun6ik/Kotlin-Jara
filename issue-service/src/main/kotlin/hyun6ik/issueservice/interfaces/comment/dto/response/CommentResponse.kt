package hyun6ik.issueservice.interfaces.comment.dto.response

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val userId: Long,
    val body: String,
    val username: String? = null,
)