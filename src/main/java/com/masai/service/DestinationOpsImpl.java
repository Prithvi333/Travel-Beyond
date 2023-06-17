package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Bus;
import com.masai.entity.Destination;
import com.masai.exception.BusNotFoundException;
import com.masai.exception.DestinationNotFoundException;
import com.masai.exception.EmptyDestinationListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.repository.BusDao;
import com.masai.repository.DestinationDao;

public class DestinationOpsImpl implements DestinationOps {

	@Autowired
	DestinationDao dd;
	@Autowired
	BusDao bd;

	@Override
	public Destination addDestination(int busId, Destination destination) {

		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			destination.setStatus(true);
			Bus b = bus.get();
			b.getDestinationList().add(destination);
			destination.getBus().add(b);
			return destination;
		}
		throw new BusNotFoundException("Bus not found with the given id");
	}

	@Override
	public Destination removeDestination(int destinationId) {
		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.isEmpty()) {
			if (!destination.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Destination is already removed");
			}
			Destination dest = destination.get();
			dest.setStatus(false);
			return dd.save(dest);
		}
		throw new DestinationNotFoundException("Destination not found with the given id");
	}

	@Override
	public Destination searchDestination(int destinationId) {
		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.isEmpty()) {
			Destination dest = destination.get();
			return dest;
		}
		throw new DestinationNotFoundException("Destination not found with the given id");
	}

	@Override
	public List<Destination> viewDestinationByBusId(int busId) {
		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			Bus b = bus.get();
			List<Destination> destinations = b.getDestinationList();
			if (!destinations.isEmpty())
				return destinations.stream().filter(a -> a.isStatus()).toList();
			throw new EmptyDestinationListException("Given bus not go any destination yet");
		}
		throw new BusNotFoundException("Bus not found with the given id");
	}

	@Override
	public List<Destination> viewAllDestination() {

		List<Destination> destinations = dd.findAll();
		if (!destinations.isEmpty())
			return destinations.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyDestinationListException("Destination list is empty");
	}

}
