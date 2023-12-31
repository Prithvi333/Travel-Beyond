package com.masai.service;

import java.util.List;

import com.masai.entity.Destination;

public interface DestinationOps {
//	Destination addDestination(int busId, Destination destination);

	Destination addDestination(Destination destination);

	Destination updateDestination(Destination destination,Integer desId);
	Destination removeDestination(int destinationId);

	Destination searchDestination(int destinationId);

	List<Destination> viewDestinationByBusId(int busId);

	List<Destination> viewAllDestination();
}
