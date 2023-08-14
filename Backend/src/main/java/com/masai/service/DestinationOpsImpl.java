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
import org.springframework.stereotype.Service;

@Service
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
			return dd.save(destination);
		}
		throw new BusNotFoundException("Bus not found with the given id");
	}

//	@Override
//	public Destination addDestination(Destination destination) {
//
////		Optional<Destination> destinationOptional = dd.findById(destination.getDesId());
////		if (destinationOptional.isEmpty()) {
//		destination.setStatus(true);
//		return dd.save(destination);

//		}
//		throw new DestinationNotFoundException("Destination already exist");
//	}

	@Override
	public Destination updateDestination(Destination destination, Integer desId) {

		Optional<Destination> destinationOptional = dd.findById(desId);
		if (destinationOptional.isPresent()) {
			Destination des = destinationOptional.get();
			if (des.isStatus()) {
				dd.save(destination);
				return destination;
			}
		}
		throw new DestinationNotFoundException("Destination does not exist");
	}

	@Override
	public Destination removeDestination(int destinationId) {
		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.isEmpty()) {
			Destination dest = destination.get();
			if (!dest.isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to remove alerady deleted destination");
			}
			dest.setStatus(false);
			return dd.save(dest);
		}
		throw new DestinationNotFoundException("Destination not found with the given id");
	}

	@Override
	public Destination searchDestination(int destinationId) {
		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.isEmpty()) {
			if (!destination.get().isStatus()) {
				throw new EntityAlreadyAlteredException("This destinaton is not available now");
			}
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
			if (!b.isStatus()) {
				throw new EntityAlreadyAlteredException("Bus is not exist now");
			}
			List<Destination> destinations = b.getDestinationList();
			if (!destinations.isEmpty())
				return destinations;
			throw new EmptyDestinationListException("Given bus not go any destination yet");
		}
		throw new BusNotFoundException("Bus not found with the given id");
	}

//	@Override
//	public List<Destination> viewAllDestination() {
//
//		List<Destination> destinations = dd.findAll();
//
//		if (!destinations.isEmpty())
//			return destinations;
//		throw new EmptyDestinationListException("Destination list is empty");
//	}

	public List<Destination> viewAllDestination() {

		List<Destination> destinations = dd.findAll();

		if (!destinations.isEmpty()) {
			destinations = destinations.stream().filter(Destination::isStatus).toList();
			return destinations.stream().filter(a -> a.isStatus()).toList();
		}
		throw new EmptyDestinationListException("Destination list is empty");
	}

}
