package hyun6ik.issue.infrastructure.comment

import hyun6ik.issue.domain.comment.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByIdAndUserId(id: Long, userId: Long) : Comment?
}
