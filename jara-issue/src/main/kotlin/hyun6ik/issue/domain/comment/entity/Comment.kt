package hyun6ik.issue.domain.comment.entity

import hyun6ik.issue.domain.base.BaseEntity
import hyun6ik.issue.domain.issue.entity.Issue
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

    fun update(body: String) {
        this.body = body
    }


}
