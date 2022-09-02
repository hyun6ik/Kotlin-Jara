package hyun6ik.issueservice.interfaces.issue.controller

import hyun6ik.issueservice.domain.issue.service.IssueService
import hyun6ik.issueservice.global.argumentResolver.AuthUser
import hyun6ik.issueservice.interfaces.issue.dto.request.IssueRequest
import hyun6ik.issueservice.interfaces.issue.dto.response.IssueResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ): ResponseEntity<IssueResponse> {
        return ResponseEntity.ok(issueService.create(authUser.userId, request))
    }
}