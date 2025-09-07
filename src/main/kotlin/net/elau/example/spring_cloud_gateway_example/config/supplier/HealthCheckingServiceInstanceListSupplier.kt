package net.elau.example.spring_cloud_gateway_example.config.supplier

import org.springframework.cloud.client.DefaultServiceInstance
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.net.HttpURLConnection
import java.net.URL

@Component
class HealthCheckingServiceInstanceListSupplier : ServiceInstanceListSupplier {

    override fun getServiceId(): String = "BACKEND-SERVICE"

    override fun get(): Flux<List<ServiceInstance>> {
        val healthyInstances = listOfNotNull(
        if (isUp("backend1", 80)) DefaultServiceInstance("backend1", "BACKEND-SERVICE", "backend1", 80, false) else null,
        if (isUp("backend2", 80)) DefaultServiceInstance("backend2", "BACKEND-SERVICE", "backend2", 80, false) else null,
        if (isUp("backend3", 80)) DefaultServiceInstance("backend3", "BACKEND-SERVICE", "backend3", 80, false) else null)
        return Flux.just(healthyInstances)
    }

    private fun isUp(host: String, port: Int): Boolean {
        return try {
            val url = URL("http://$host:$port/actuator/health")
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 500
            conn.readTimeout = 500
            conn.requestMethod = "GET"
            conn.connect()
            conn.responseCode == 200
        } catch (ex: Exception) {
            false
        }
    }
}