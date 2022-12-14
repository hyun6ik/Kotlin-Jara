package hyun6ik.issue.domain.comment.service

import hyun6ik.issue.domain.comment.entity.Comment
import hyun6ik.issue.domain.issue.entity.Issue
import hyun6ik.issue.global.utils.toResponse
import hyun6ik.issue.infrastructure.comment.CommentReader
import hyun6ik.issue.infrastructure.comment.CommentStore
import hyun6ik.issue.interfaces.comment.dto.request.CommentRequest
import hyun6ik.issue.interfaces.comment.dto.response.CommentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentReader: CommentReader,
    private val commentStore: CommentStore,
) {

    @Transactional
    fun create(issue: Issue, initComment: Comment) : CommentResponse{
        val comment = commentStore.store(initComment)
        issue.addComment(comment)
        return comment.toResponse()
    }

    @Transactional
    fun update(id: Long, userId: Long, request: CommentRequest): CommentResponse {
        return commentReader.getCommentBy(id, userId).apply {
            update(request.body)
        }.toResponse()
    }

    fun getCommentBy(id: Long, userId: Long) = commentReader.getCommentBy(id, userId)

}
