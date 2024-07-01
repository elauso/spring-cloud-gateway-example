package net.elau.example.spring_cloud_gateway_example.filter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@Component
class LoggingFilter : GlobalFilter {
    companion object {
        val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {

        val uris: Set<URI> = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, emptySet())
        val originalUri = if (uris.isEmpty()) "Unknown" else uris.iterator().next().toString()

        val route: Route? = exchange.getAttribute(GATEWAY_ROUTE_ATTR)
        val routeUri: URI? = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR)

        log.info("Incoming request $originalUri is routed to id ${route?.id}, uri: $routeUri")
        return chain.filter(exchange)
    }
}
