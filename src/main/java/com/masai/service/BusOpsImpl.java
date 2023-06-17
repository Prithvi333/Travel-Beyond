package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Bus;
import com.masai.entity.Travels;
import com.masai.exception.BusNotFoundException;
import com.masai.exception.EmptyBusListException;
import com.masai.exception.TravelsNotFoundException;
import com.masai.repository.BusDao;
import com.masai.repository.TravelDao;
import org.springframework.stereotype.Service;

@Service
public class BusOpsImpl implements BusOps {

	@Autowired
	BusDao bd;
	@Autowired
	TravelDao td;

	@Override
	public Bus addBus(int travelId, Bus bus) {

		Optional<Travels> travels = td.findById(travelId);
		if (!travels.isEmpty()) {
			travels.get().setStatus(true);
			travels.get().getBuses().add(bus);
			bus.setTravel(travels.get());
			return bd.save(bus);
		}
		throw new TravelsNotFoundException("Tranvels not found to add bus");
	}

	@Override
	public Bus removeBus(int busId) {

		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			bus.get().setStatus(false);
			return bd.save(bus.get());
		}

		throw new BusNotFoundException("Bus not found with the given id to remove");
	}

	@Override
	public Bus searchBus(int busId) {
		Optional<Bus> bus = bd.findById(busId);
		if (!bus.isEmpty()) {
			return bus.get();
		}
		throw new BusNotFoundException("Bus not found ");
	}

	@Override
	public List<Bus> viewBusByTravelsId(int travelId) {
		Optional<Travels> travels = td.findById(travelId);
		if (!travels.isEmpty()) {
			Travels travel = travels.get();

			List<Bus> buses = travel.getBuses();
			if (!buses.isEmpty())
				return buses;
			throw new EmptyBusListException("No bus is added by this traveler yet");
		}
		throw new TravelsNotFoundException("Tranvels not found to add bus");
	}

	@Override
	public List<Bus> viewAllBuses() {
		List<Bus> buses = bd.findAll();
		if (!buses.isEmpty())
			return buses;
		throw new EmptyBusListException("Bus list is empty");
	}

}
