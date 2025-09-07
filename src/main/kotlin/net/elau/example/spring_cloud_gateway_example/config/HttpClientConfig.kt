package net.elau.example.spring_cloud_gateway_example.config

import io.netty.channel.ChannelOption
import net.elau.example.spring_cloud_gateway_example.config.properties.HttpClientProperties
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import reactor.netty.resources.LoopResources

@Configuration
class HttpClientConfig(private val props: HttpClientProperties) {

    private val logger = LoggerFactory.getLogger(HttpClientConfig::class.java)

    @Bean
    fun loopResources(): LoopResources {
        return LoopResources.create("custom-http-client-loops")
    }

    @Bean
    fun httpClient(loopResources: LoopResources): HttpClient =
        HttpClient.create(
            ConnectionProvider.builder("custom")
                .maxConnections(props.maxConnections)
                .maxIdleTime(props.maxIdleTime)
                .maxLifeTime(props.maxLifeTime)
                .pendingAcquireTimeout(props.pendingAcquireTimeout)
                .build()
        ).resolver { builder ->
            builder.cacheMinTimeToLive(props.cacheTtlMin)
            builder.cacheMaxTimeToLive(props.cacheTtlMax)
        }.runOn(loopResources)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, props.connectTimeoutMilis)
            .responseTimeout(props.responseTimeout)
            .doOnConnected { conn ->
                logger.debug("Connected to backend.local -> {}", conn.channel().remoteAddress())
            }
}