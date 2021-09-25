package com.complexica.weather.controllers;

import com.complexica.weather.dto.RouteMapDataDTO;
import com.complexica.weather.dto.RouteMapToStoreDTO;
import com.complexica.weather.dto.routeMap.RouteMapDTO;
import com.complexica.weather.dto.routeMap.RouteMapUpdateResponseDTO;
import com.complexica.weather.persistent.common.ResponseDTO;
import com.complexica.weather.services.RouteService;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("route/")
public class RouteMapController {

    @Autowired
    private RouteService routeService;
    @ApiOperation("Return a JSON object that gives the weather averages.")
    @PostMapping(path = "/viewMap", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<RouteMapDataDTO>> viewMap(@NonNull @RequestBody List<RouteMapDTO> routeMapDTO) throws Exception {
        RouteMapDataDTO routeMapDataDTO = routeService.viewRouteMapInfo(routeMapDTO);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<RouteMapDataDTO>().setData(routeMapDataDTO));
    }


    @ApiOperation("Return a JSON object that gives the weather stored averages.")
    @PostMapping(path = "/storeRoute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<String>> store(@NonNull @RequestBody RouteMapToStoreDTO routeMapToStoreDTO) {
        routeService.storeRouteMap(routeMapToStoreDTO);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<String>().setData("ok"));
    }

    @ApiOperation("Return a JSON object that gives the weather stored averages.")
    @PutMapping(path = "/updateRoute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<String>> updateRoute(@NonNull @RequestBody RouteMapUpdateResponseDTO routeMapUpdateResponseDTO){
        routeService.updateRouteMap(routeMapUpdateResponseDTO);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<String>().setData("ok"));
    }

    @ApiOperation("Return a JSON object that gives the weather stored averages.")
    @DeleteMapping(path = "/deleteRoute/{routeName:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<String>> deleteRoute(@NonNull @PathVariable("routeName") String routeName){
        routeService.deleteRoutMap(routeName);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<String>().setData("ok"));
    }

    @ApiOperation("Return a JSON object that gives the weather stored averages.")
    @GetMapping(path = "/viewAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<RouteMapToStoreDTO> >> viewAll(){
        List<RouteMapToStoreDTO> routeMapToStoreDTOS = routeService.viewSavedRouteMaps();
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<List<RouteMapToStoreDTO> >().setData(routeMapToStoreDTOS));
    }


    @ApiOperation("Return a JSON object that gives the weather stored averages.")
    @GetMapping(path = "/viewByRouteName/{routeName:.*}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<RouteMapUpdateResponseDTO>> findRoute(@NonNull @PathVariable("routeName") String routeName) {
        RouteMapUpdateResponseDTO routeByName =  routeService.findByRouteName(routeName);
        return ResponseEntity
                .ok()
                .body(new ResponseDTO<RouteMapUpdateResponseDTO>().setData(routeByName));
    }
}
