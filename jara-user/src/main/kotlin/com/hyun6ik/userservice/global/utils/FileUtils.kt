package com.hyun6ik.userservice.global.utils

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.core.io.ClassPathResource
import org.springframework.http.codec.multipart.FilePart
import java.io.File

object FileUtils {

    suspend fun createFile(id: Long, filePart: FilePart): String? {
        val orgFilename = filePart.filename()
        var filename: String? = null
        if (orgFilename.isNotEmpty()) {
            val ext = getExt(orgFilename)
            filename = "${id}.${ext}"

            val file = File(ClassPathResource("/images/").file, filename)
            filePart.transferTo(file).awaitSingleOrNull()
        }
        return filename
    }

    fun getFile(filename: String): File = File(ClassPathResource("/images/").file, filename)

    fun getExt(filename: String) = filename.substring(filename.lastIndexOf(".") + 1)
}
