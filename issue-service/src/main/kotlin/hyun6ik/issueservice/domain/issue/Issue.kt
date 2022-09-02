package hyun6ik.issueservice.domain.issue

import hyun6ik.issueservice.domain.base.BaseEntity
import hyun6ik.issueservice.domain.issue.enums.IssuePriority
import hyun6ik.issueservice.domain.issue.enums.IssueStatus
import hyun6ik.issueservice.domain.issue.enums.IssueType
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
}