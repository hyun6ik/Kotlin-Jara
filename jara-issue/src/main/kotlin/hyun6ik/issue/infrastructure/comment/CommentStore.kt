package hyun6ik.issue.infrastructure.comment

import hyun6ik.issue.domain.comment.entity.Comment
import org.springframework.stereotype.Component

@Component
class CommentStore(
    private val commentRepository: CommentRepository,
) {
    fun store(comment: Comment) = commentRepository.save(comment)

}
