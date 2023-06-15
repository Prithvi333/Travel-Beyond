package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Travels;
import com.masai.entity.TravelsDto;
import com.masai.exception.EmptyTravelListException;
import com.masai.exception.TravelsNotFoundException;
import com.masai.repository.TravelDao;
@Service
public class TravelsOpsImpl implements TravelsOps {

	@Autowired
	TravelDao td;

	@Override
	public Travels addTravels(Travels travel) {
		travel.setStatus(true);
		return td.save(travel);

	}

	@Override
	public Travels updateTravels(int id, TravelsDto travelsdto) {

		Optional<Travels> travels = td.findById(id);
		if (!travels.isEmpty()) {

			Travels travel = travels.get();
			travel.setAgentName(travelsdto.getAgentName());
			travel.setAddress(travelsdto.getAgentName());
			travel.setContact(travelsdto.getContact());
			return td.save(travel);
		}
		throw new TravelsNotFoundException("Travels not found to update");

	}

	@Override
	public Travels removeTravels(int id) {

		Optional<Travels> travels = td.findById(id);
		if (!travels.isEmpty()) {

			travels.get().setStatus(false);
			return travels.get();
		}
		throw new TravelsNotFoundException("Travels not found to remove");

	}

	@Override
	public Travels searchTravels(int id) {

		Optional<Travels> travels = td.findById(id);
		if (!travels.isEmpty()) {
			return travels.get();
		}
		throw new TravelsNotFoundException("Travels not found to view");
	}

	@Override
	public List<Travels> viewTravels() {

		List<Travels> travelsList = td.findAll();

		if (!travelsList.isEmpty()) {
			return travelsList;
		}
		throw new EmptyTravelListException("Travels list is empty");
	}

}
