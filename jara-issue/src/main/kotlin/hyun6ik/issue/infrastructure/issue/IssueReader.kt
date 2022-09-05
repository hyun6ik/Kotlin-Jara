package hyun6ik.issue.infrastructure.issue

import hyun6ik.issue.domain.issue.entity.enums.IssueStatus
import hyun6ik.issue.global.exception.NotFoundException
import hyun6ik.issue.interfaces.issue.dto.response.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class IssueReader(
    private val issueRepository: IssueRepository,
) {
    fun getAllBy(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map(IssueResponse::of)

    fun getIssueBy(id: Long) = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

}
