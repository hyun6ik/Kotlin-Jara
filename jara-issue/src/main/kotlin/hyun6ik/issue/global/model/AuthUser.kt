package hyun6ik.issue.global.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthUser(
    @JsonProperty("id")
    val userId: Long,
    val username: String,
    val profileUrl: String? = null,
    val email: String,
)
