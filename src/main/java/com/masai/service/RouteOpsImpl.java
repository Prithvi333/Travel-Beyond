package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Bus;
import com.masai.entity.Route;
import com.masai.exception.BusNotFoundException;
import com.masai.exception.EmptyRouteListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.RouteNotFoundException;
import com.masai.repository.BusDao;
import com.masai.repository.RouteDao;
import org.springframework.stereotype.Service;

@Service
public class RouteOpsImpl implements RouteOps {

	@Autowired
	BusDao bd;
	@Autowired
	RouteDao rd;

	@Override
	public Route addRoute(int busId, Route route) {

		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			Bus b = bus.get();
			if (!b.isStatus()) {
				throw new EntityAlreadyAlteredException("Bus is not exist now");
			}
			route.setStatus(true);
			b.getRoutes().add(route);
			return rd.save(route);
		}
		throw new BusNotFoundException("Bus not found with the given id ");
	}

	@Override
	public Route updateRoute(RouteDao routedto, Route route) {
		Optional<Route> rt = rd.findById(route.getRouteId());
		if (!rt.isEmpty()) {
			Route rot = rt.get();
			if (!rot.isStatus()) {
				throw new EntityAlreadyAlteredException("Route is not available to update now");
			}
			route.setRouteFrom(rot.getRouteFrom());
			route.setRouteTo(rot.getRouteTo());
			route.setArrivalTime(rot.getArrivalTime());
			route.setDepartureTime(rot.getDepartureTime());
			rd.save(route);
		}
		throw new RouteNotFoundException("Route not found to update ");
	}

	@Override
	public Route removeRoute(int routeId) {

		Optional<Route> route = rd.findById(routeId);
		if (!route.isEmpty()) {
			Route rut = route.get();
			if (!rut.isStatus()) {
				throw new EntityAlreadyAlteredException("Route is not available to remove now");
			}
			rut.setStatus(false);
			return rd.save(rut);
		}
		throw new RouteNotFoundException("Route not found to remove");
	}

	@Override
	public Route searchRoute(int routeId) {
		Optional<Route> route = rd.findById(routeId);
		if (!route.isEmpty()) {
			if (!route.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Route is not available now");
			}
			return route.get();
		}
		throw new RouteNotFoundException("Route not found to view");
	}

	@Override
	public List<Route> viewRouteList() {

		List<Route> routeList = rd.findAll();
		if (!routeList.isEmpty())
			return routeList.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyRouteListException("Empty route list");
	}

}
