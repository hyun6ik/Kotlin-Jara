package hyun6ik.issue.interfaces.issue.controller

import hyun6ik.issue.domain.issue.entity.enums.IssueStatus
import hyun6ik.issue.domain.issue.service.IssueService
import hyun6ik.issue.global.annotation.Auth
import hyun6ik.issue.global.model.AuthUser
import hyun6ik.issue.interfaces.issue.dto.request.IssueRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        @Auth authUser: AuthUser,
        @RequestBody request: IssueRequest,
    ) = ResponseEntity.ok(issueService.create(authUser.userId, request))

    @GetMapping
    fun getAll(
        @Auth authUser: AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = ResponseEntity.ok(issueService.getAll(status))

    @GetMapping("/{id}")
    fun get(
        @Auth authUser: AuthUser,
        @PathVariable id: Long,
    ) = ResponseEntity.ok(issueService.get(id))

    @PutMapping("{id}")
    fun update(
        @Auth authUser: AuthUser,
        @PathVariable id: Long,
        @RequestBody request: IssueRequest,
    ) = ResponseEntity.ok(issueService.update(authUser.userId, id, request))

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @Auth authUser: AuthUser,
        @PathVariable id: Long,
    ) {
        issueService.delete(id)
    }
}
