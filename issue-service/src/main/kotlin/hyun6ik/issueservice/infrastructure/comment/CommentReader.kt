package hyun6ik.issueservice.infrastructure.comment

import org.springframework.stereotype.Component

@Component
class CommentReader(
    private val commentRepository: CommentRepository,
) {
}