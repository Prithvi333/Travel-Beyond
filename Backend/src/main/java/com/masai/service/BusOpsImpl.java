package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Bus;
import com.masai.entity.Destination;
import com.masai.entity.Travels;
import com.masai.exception.BusNotFoundException;
import com.masai.exception.DestinationNotFoundException;
import com.masai.exception.EmptyBusListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.TravelsNotFoundException;
import com.masai.repository.BusDao;
import com.masai.repository.DestinationDao;
import com.masai.repository.TravelDao;

@Service
public class BusOpsImpl implements BusOps {

	@Autowired
	BusDao bd;
	@Autowired
	TravelDao td;
	@Autowired
	DestinationDao dd;

	@Override
	public Bus addBus(int travelId, Bus bus) {

		Optional<Travels> travels = td.findById(travelId);
		if (!travels.isEmpty()) {
			bus.setStatus(true);
			travels.get().setStatus(true);
			travels.get().getBuses().add(bus);
			bus.setTravel(travels.get());
			return bd.save(bus);
		}
		throw new TravelsNotFoundException("Travels not found to add bus");
	}

	public Bus addDestination(int destId, int bId) {

		Optional<Destination> destination = dd.findById(destId);
		Optional<Bus> bus = bd.findById(bId);

		if (destination.isEmpty() || !destination.get().isStatus())
			throw new DestinationNotFoundException("Destination is not found");
		if (bus.isEmpty() || !bus.get().isStatus())
			throw new BusNotFoundException("Bus not found");
		bus.get().getDestinationList().add(destination.get());
		destination.get().getBus().add(bus.get());
		return bd.save(bus.get());
	}

	@Override
	public Bus removeBus(int busId) {

		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			if (!bus.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Bus is already removed");
			}
			bus.get().setStatus(false);
			return bd.save(bus.get());
		}

		throw new BusNotFoundException("Bus not found with the given id to remove");
	}

	@Override
	public Bus searchBus(int busId) {
		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			if (!bus.get().isStatus()) {
				throw new EntityAlreadyAlteredException("This bus is not exist now");
			}
			return bus.get();
		}
		throw new BusNotFoundException("Bus not found");
	}

	@Override
	public List<Bus> viewBusByTravelsId(int travelId) {
		Optional<Travels> travels = td.findById(travelId);
		if (!travels.isEmpty()) {
			Travels travel = travels.get();
			if (!travel.isStatus()) {
				throw new EntityAlreadyAlteredException("This travels is not available now");
			}
			List<Bus> buses = travel.getBuses();
			if (!buses.isEmpty())
				return buses.stream().filter(a -> a.isStatus()).toList();
			throw new EmptyBusListException("No bus is added by this traveler yet");
		}
		throw new TravelsNotFoundException("Travels not found to add bus");
	}

	@Override
	public List<Bus> viewAllBuses() {
		List<Bus> buses = bd.findAll();
		if (!buses.isEmpty())
			return buses.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyBusListException("Bus list is empty");
	}

}
