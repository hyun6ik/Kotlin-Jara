package hyun6ik.issueservice.infrastructure.issue

import hyun6ik.issueservice.domain.issue.entity.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long> {
}