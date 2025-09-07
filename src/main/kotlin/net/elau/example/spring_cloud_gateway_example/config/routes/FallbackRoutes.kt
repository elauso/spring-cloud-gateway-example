package net.elau.example.spring_cloud_gateway_example.config.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class FallbackRoutes {

    @Bean
    fun fallbackRouter() = router {
        GET("/_fallback/api") {
            ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .bodyValue(mapOf("message" to "Service temporarily unavailable. Please try again."))
        }
    }
}