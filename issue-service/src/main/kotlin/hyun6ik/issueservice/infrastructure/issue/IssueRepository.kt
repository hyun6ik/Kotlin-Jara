package hyun6ik.issueservice.infrastructure.issue

import hyun6ik.issueservice.domain.issue.entity.Issue
import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {

    fun findAllByStatusOrderByCreatedAtDesc(status: IssueStatus): List<Issue>?
}