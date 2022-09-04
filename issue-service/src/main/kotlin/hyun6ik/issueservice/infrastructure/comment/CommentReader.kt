package hyun6ik.issueservice.infrastructure.comment

import hyun6ik.issueservice.domain.comment.entity.Comment
import hyun6ik.issueservice.global.exception.NotFoundException
import org.springframework.stereotype.Component

@Component
class CommentReader(
    private val commentRepository: CommentRepository,
) {
    fun getCommentBy(id: Long, userId: Long) : Comment {
        return commentRepository.findByIdAndUserId(id, userId) ?: throw NotFoundException("해당 코멘트를 찾을 수 없습니다.")
    }
}