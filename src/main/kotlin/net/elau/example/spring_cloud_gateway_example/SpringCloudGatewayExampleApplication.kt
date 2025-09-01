package net.elau.example.spring_cloud_gateway_example

import net.elau.example.spring_cloud_gateway_example.config.properties.HttpClientProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(HttpClientProperties::class)
class SpringCloudGatewayExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudGatewayExampleApplication>(*args)
}
