package com.complexica.weather.repository;

import com.complexica.weather.persistent.Route;

import java.util.Optional;

public interface RouteMapDAO extends AbstractDAO<Route> {
    Optional<Route> findByRouteMapName(String routeMapName);


}
