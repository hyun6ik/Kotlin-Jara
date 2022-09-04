package hyun6ik.issueservice.domain.comment.entity

import hyun6ik.issueservice.global.utils.toResponse
import hyun6ik.issueservice.interfaces.comment.dto.response.CommentResponse
import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Embeddable
class Comments(

    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),

) {
    fun add(comment: Comment) {
        comments.add(comment)
    }

    fun toResponseList(): List<CommentResponse> {
        return comments.sortedByDescending(Comment::id)
            .map(Comment::toResponse)
    }

    fun remove(comment: Comment) {
        comments.remove(comment)
    }
}