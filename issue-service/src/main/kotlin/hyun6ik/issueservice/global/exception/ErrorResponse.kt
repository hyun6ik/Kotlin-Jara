package hyun6ik.issueservice.global.exception

data class ErrorResponse(
    val code: Int,
    val message: String,
) {
}