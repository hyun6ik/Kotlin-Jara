package hyun6ik.issue.application

import hyun6ik.issue.domain.comment.entity.Comment
import hyun6ik.issue.domain.comment.service.CommentService
import hyun6ik.issue.domain.issue.service.IssueService
import hyun6ik.issue.interfaces.comment.dto.request.CommentRequest
import hyun6ik.issue.interfaces.comment.dto.response.CommentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentFacade(
    private val commentService: CommentService,
    private val issueService: IssueService,
) {

    @Transactional
    fun create(
        issueId: Long,
        userId: Long,
        username: String,
        request: CommentRequest,
    ) : CommentResponse {
        val issue = issueService.getIssueBy(issueId)
        val initComment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )
        return commentService.create(issue, initComment)
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issueService.getIssueBy(issueId)
        commentService.getCommentBy(id, userId).let {
           comment -> issue.removeComment(comment)
        }
    }

}
