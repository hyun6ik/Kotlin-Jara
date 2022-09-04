package hyun6ik.issueservice.infrastructure.comment

import hyun6ik.issueservice.domain.comment.entity.Comment
import org.springframework.stereotype.Component

@Component
class CommentStore(
    private val commentRepository: CommentRepository,
) {
    fun store(comment: Comment) = commentRepository.save(comment)

}