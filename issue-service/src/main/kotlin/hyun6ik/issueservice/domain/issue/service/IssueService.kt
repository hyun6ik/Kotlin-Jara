package hyun6ik.issueservice.domain.issue.service

import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.infrastructure.issue.IssueReader
import hyun6ik.issueservice.infrastructure.issue.IssueStore
import hyun6ik.issueservice.interfaces.issue.dto.request.IssueRequest
import hyun6ik.issueservice.interfaces.issue.dto.response.IssueResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class IssueService(
    private val issueReader: IssueReader,
    private val issueStore: IssueStore,
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val initIssue = request.toEntity(userId)
        val issue = issueStore.store(initIssue)
        return IssueResponse.of(issue)
    }

    fun getAll(
        status: IssueStatus
    ) = issueReader.getAllBy(status)

    fun get(id: Long): IssueResponse {
        val issue = issueReader.getIssueBy(id)
        return IssueResponse.of(issue)
    }

    @Transactional
    fun update(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueReader.getIssueBy(id)
        return with(issue) {
            update(
                request.summary,
                request.description,
                request.type,
                request.priority,
                request.status
            )
            IssueResponse.of(this)
        }
    }

    @Transactional
    fun delete(id: Long) {
        issueStore.deleteIssueBy(id)
    }

}