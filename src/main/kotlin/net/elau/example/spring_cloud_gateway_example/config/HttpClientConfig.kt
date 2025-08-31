package net.elau.example.spring_cloud_gateway_example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.LoopResources
import java.time.Duration

@Configuration
class HttpClientConfig {

    @Bean
    fun loopResources(): LoopResources {
        return LoopResources.create("my-http-client-loops")
    }

    @Bean
    fun httpClient(loopResources: LoopResources): HttpClient {
        return HttpClient.create()
            .resolver { builder ->
                builder.cacheMinTimeToLive(Duration.ofSeconds(10))
                builder.cacheMaxTimeToLive(Duration.ofSeconds(30))
                builder.cacheNegativeTimeToLive(Duration.ofSeconds(0))
            }
            .runOn(loopResources)
    }
}