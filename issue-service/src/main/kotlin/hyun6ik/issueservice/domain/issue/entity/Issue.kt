package hyun6ik.issueservice.domain.issue.entity

import hyun6ik.issueservice.domain.base.BaseEntity
import hyun6ik.issueservice.domain.issue.entity.enums.IssuePriority
import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.domain.issue.entity.enums.IssueType
import javax.persistence.*

@Entity
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val userId: Long,

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
}