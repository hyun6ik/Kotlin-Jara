package hyun6ik.issueservice.interfaces.issue.controller

import hyun6ik.issueservice.domain.issue.entity.enums.IssueStatus
import hyun6ik.issueservice.domain.issue.service.IssueService
import hyun6ik.issueservice.global.argumentResolver.AuthUser
import hyun6ik.issueservice.interfaces.issue.dto.request.IssueRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ) = ResponseEntity.ok(issueService.create(authUser.userId, request))

    @GetMapping
    fun getAll(
        authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = ResponseEntity.ok(issueService.getAll(status))

    @GetMapping("/{id}")
    fun get(
        authUser: AuthUser,
        @PathVariable id: Long,
    ) = ResponseEntity.ok(issueService.get(id))
}