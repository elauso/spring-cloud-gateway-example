package net.elau.example.spring_cloud_gateway_example.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
@ConfigurationProperties(prefix = "gateway.httpclient")
class HttpClientProperties {
    var maxConnections: Int = 50
    var maxIdleTime: Duration = Duration.ofSeconds(2)
    var maxLifeTime: Duration = Duration.ofSeconds(5)
    var cacheTtlMin: Duration = Duration.ofSeconds(5)
    var cacheTtlMax: Duration = Duration.ofSeconds(10)
}