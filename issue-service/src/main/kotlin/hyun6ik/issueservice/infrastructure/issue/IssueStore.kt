package hyun6ik.issueservice.infrastructure.issue

import hyun6ik.issueservice.domain.issue.entity.Issue
import org.springframework.stereotype.Component

@Component
class IssueStore(
    private val issueRepository: IssueRepository,
) {
    fun store(issue: Issue): Issue = issueRepository.save(issue)
}