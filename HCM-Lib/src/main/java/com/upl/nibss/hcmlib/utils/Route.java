package com.upl.nibss.hcmlib.utils;

import lombok.Data;

import java.util.Objects;

@Data
public class Route {
    private String routePath;
    private String routeKey;

    public Route(){
    }

    public Route(String routePath, String routeKey){
        setRouteKey(routeKey);
        setRoutePath(routePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Route route = (Route) o;
        return Objects.equals(routePath, route.routePath) &&
                Objects.equals(routeKey, route.routeKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), routePath, routeKey);
    }
}
