package com.wm.item.inventory.management.functionalweb.routers;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.wm.item.inventory.management.datasetsMgt.constants.ItemMgtConstants;
import com.wm.item.inventory.management.functionalweb.handlers.ItemMgtHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ItemMgtRouterConfiguration {

  @Bean
  public RouterFunction<ServerResponse> itemsRoute(ItemMgtHandler itemMgtHandler) {

    return RouterFunctions
        .route(
            GET(ItemMgtConstants.itemGetAllEndPointsFunctional_v1)
                .and(accept(MediaType.APPLICATION_JSON)),itemMgtHandler::getAllItems);

  }


}
