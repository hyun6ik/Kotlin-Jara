package hyun6ik.issueservice.domain.comment.entity

import hyun6ik.issueservice.domain.base.BaseEntity
import hyun6ik.issueservice.domain.issue.entity.Issue
import hyun6ik.issueservice.interfaces.comment.dto.response.CommentResponse
import javax.persistence.*

@Entity
class Comment(

    @Column
    val userId: Long,

    @Column
    val username: String,

    @Column
    var body: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    val issue: Issue,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseEntity() {


}
