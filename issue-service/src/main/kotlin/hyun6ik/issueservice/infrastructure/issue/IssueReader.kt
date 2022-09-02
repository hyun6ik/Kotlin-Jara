package hyun6ik.issueservice.infrastructure.issue

import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.interfaces.issue.dto.response.IssueResponse
import org.springframework.stereotype.Component

@Component
class IssueReader(
    private val issueRepository: IssueRepository,
) {
    fun getAllBy(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map(IssueResponse::of)
}