package com.complexica.weather.persistent;

import com.complexica.weather.persistent.common.AuditedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "route")
public class Route extends AuditedEntity {


    @Column(name = "name")
    private String routeMapName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WeatherValues> itinerary = new ArrayList<>();



    public List<WeatherValues> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<WeatherValues> itinerary) {
        this.itinerary = itinerary;
    }

    public String getRouteMapName() {
        return routeMapName;
    }

    public void setRouteMapName(String routeMapName) {
        this.routeMapName = routeMapName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Route route = (Route) o;
        return Objects.equals(routeMapName, route.routeMapName) &&
                Objects.equals(itinerary, route.itinerary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), routeMapName, itinerary);
    }
}
