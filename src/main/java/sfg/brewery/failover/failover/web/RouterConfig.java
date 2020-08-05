package sfg.brewery.failover.failover.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    //when request goes to get of /inventory-service, it goes through the gateway that forward it to the inventory-service.
    //if this service is down, the gateway routes it to this failover service (we set it in teh gateway service).
    //since the call was a get to /inventory-service, this method routes it to the method listInventory() in InventoryHandler.class
    @Bean
    public RouterFunction inventoryRoute(InventoryHandler inventoryHandler){
        return route(GET("/inventory-service")
                .and(accept(MediaType.APPLICATION_JSON)), inventoryHandler::listInventory);
    }
}
