package net.elau.example.spring_cloud_gateway_example.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
@ConfigurationProperties(prefix = "custom-gateway-config.httpclient")
class HttpClientProperties {
    var maxConnections: Int = 50
    var maxIdleTime: Duration = Duration.ofSeconds(2)
    var maxLifeTime: Duration = Duration.ofSeconds(5)
    var pendingAcquireTimeout: Duration = Duration.ofMillis(200)
    var responseTimeout: Duration = Duration.ofSeconds(2)
    var connectTimeoutMilis: Int = 500
    var cacheTtlMin: Duration = Duration.ofSeconds(5)
    var cacheTtlMax: Duration = Duration.ofSeconds(10)
}