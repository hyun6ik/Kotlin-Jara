package hyun6ik.issue.interfaces.comment.controller

import hyun6ik.issue.application.CommentFacade
import hyun6ik.issue.domain.comment.service.CommentService
import hyun6ik.issue.global.annotation.Auth
import hyun6ik.issue.global.model.AuthUser
import hyun6ik.issue.interfaces.comment.dto.request.CommentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentFacade: CommentFacade,
    private val commentService: CommentService,
) {

    @PostMapping
    fun create(
        @Auth authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) = ResponseEntity.ok(commentFacade.create(issueId, authUser.userId, authUser.username, request))

    @PutMapping("/{id}")
    fun update(
        @Auth authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest,
    ) = ResponseEntity.ok(commentService.update(id, authUser.userId, request))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @Auth authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
    ) {
        commentFacade.delete(issueId, id, authUser.userId)
    }
}
