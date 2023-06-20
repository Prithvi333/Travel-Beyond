package com.masai.service;

import java.util.List;

import com.masai.entity.Bus;

public interface BusOps {

	Bus addBus(int travelId, Bus bus);

	Bus addDestination(int destId, int bId);

	Bus removeBus(int busId);

	Bus searchBus(int busId);

	List<Bus> viewBusByTravelsId(int travelId);

	List<Bus> viewAllBuses();
}
