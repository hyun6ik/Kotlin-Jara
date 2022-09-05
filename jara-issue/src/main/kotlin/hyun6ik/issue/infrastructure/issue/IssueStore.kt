package hyun6ik.issue.infrastructure.issue

import hyun6ik.issue.domain.issue.entity.Issue
import org.springframework.stereotype.Component

@Component
class IssueStore(
    private val issueRepository: IssueRepository,
) {
    fun store(issue: Issue): Issue = issueRepository.save(issue)

    fun deleteIssueBy(id: Long) {
        issueRepository.deleteById(id)
    }
}
