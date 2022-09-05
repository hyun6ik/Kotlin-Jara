package hyun6ik.issue.infrastructure.issue

import hyun6ik.issue.domain.issue.entity.Issue
import hyun6ik.issue.domain.issue.entity.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {

    fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>?
}
