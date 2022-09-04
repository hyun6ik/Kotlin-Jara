package hyun6ik.issueservice.infrastructure.comment

import hyun6ik.issueservice.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}