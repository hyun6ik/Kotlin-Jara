package hyun6ik.issue.domain.issue.entity

import hyun6ik.issue.domain.base.BaseEntity
import hyun6ik.issue.domain.comment.entity.Comment
import hyun6ik.issue.domain.comment.entity.Comments
import hyun6ik.issue.domain.issue.entity.enums.IssuePriority
import hyun6ik.issue.domain.issue.entity.enums.IssueStatus
import hyun6ik.issue.domain.issue.entity.enums.IssueType
import javax.persistence.*

@Entity
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val userId: Long,

    @Embedded
    var comments : Comments = Comments(mutableListOf()),

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,

    ) : BaseEntity() {

    fun update(
        summary: String,
        description: String,
        type: IssueType,
        priority: IssuePriority,
        status: IssueStatus,
    ) {
        this.summary = summary
        this.description = description
        this.type = type
        this.priority = priority
        this.status = status
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }
}
