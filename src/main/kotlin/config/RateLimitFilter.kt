package es.unizar.webeng.hello.config

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.Refill
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

@Component
class RateLimitFilter(
    @Value("\${ratelimit.requests}") private val requests: Long,
    @Value("\${ratelimit.period}") private val period: Duration
) : OncePerRequestFilter() {

    private val cache = ConcurrentHashMap<String, Bucket>()

    private fun newBucket(): Bucket {
        val limit = Bandwidth.classic(
            requests,
            Refill.intervally(requests, period)
        )
        return Bucket.builder().addLimit(limit).build()
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val key = request.remoteAddr // IP como identificador

        val bucket = cache.computeIfAbsent(key) { newBucket() }

        val probe = bucket.tryConsumeAndReturnRemaining(1)
        if (probe.isConsumed) {
            response.setHeader("X-Rate-Limit-Remaining", probe.remainingTokens.toString())
            filterChain.doFilter(request, response)
        } else {
            response.status = 429
            response.writer.write("Too Many Requests")
        }
    }
}