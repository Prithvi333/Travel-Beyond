package com.masai.service;

import java.util.List;

import com.masai.entity.Route;
import com.masai.entity.RouteDto;
import com.masai.repository.RouteDao;

public interface RouteOps {

	Route addRoute(int busId, Route route);

	Route updateRoute(RouteDto routedto, Integer rid);

	Route removeRoute(int routeId);

	Route searchRoute(int routeId);

	List<Route> viewRouteList();
}
