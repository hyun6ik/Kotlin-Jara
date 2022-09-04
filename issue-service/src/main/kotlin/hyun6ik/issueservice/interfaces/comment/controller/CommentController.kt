package hyun6ik.issueservice.interfaces.comment.controller

import hyun6ik.issueservice.application.CommentFacade
import hyun6ik.issueservice.domain.comment.service.CommentService
import hyun6ik.issueservice.global.argumentResolver.AuthUser
import hyun6ik.issueservice.interfaces.comment.dto.request.CommentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentFacade: CommentFacade,
    private val commentService: CommentService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) = ResponseEntity.ok(commentFacade.create(issueId, authUser.userId, authUser.username, request))

    @PutMapping("/{id}")
    fun update(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest,
    ) = ResponseEntity.ok(commentService.update(id, authUser.userId, request))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @PathVariable id: Long,
    ) {
        commentFacade.delete(issueId, id, authUser.userId)
    }
}