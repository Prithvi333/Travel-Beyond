package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Travels;
import com.masai.entity.TravelsDto;
import com.masai.exception.EmptyTravelListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.TravelsNotFoundException;
import com.masai.repository.TravelDao;

@Service
public class TravelsOpsImpl implements TravelsOps {

	@Autowired
	TravelDao td;

//	Adding travel that is done by admin only
	@Override
	public Travels addTravels(Travels travel) {
		travel.setStatus(true);
		return td.save(travel);

	}
// Updating travel using data transfer object
	@Override
	public Travels updateTravels(int travelsId, TravelsDto travelsdto) {

		Optional<Travels> travels = td.findById(travelsId);
		if (!travels.isEmpty()) {

			Travels travel = travels.get();
			if (!travel.isStatus()) {
				throw new EntityAlreadyAlteredException("Travel is not available to update");
			}
			travel.setAgentName(travelsdto.getAgentName());
			travel.setAddress(travelsdto.getAddress());
			travel.setContact(travelsdto.getContact());
			return td.save(travel);
		}
		throw new TravelsNotFoundException("Travels not found to update");

	}

//	Deleting travel i.e it not available anymore
	@Override
	public Travels removeTravels(int id) {

		Optional<Travels> travels = td.findById(id);
		if (!travels.isEmpty()) {
			if (!travels.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Travel is not available to remove");
			}
			travels.get().setStatus(false);
			return td.save(travels.get());
		}
		throw new TravelsNotFoundException("Travels not found to remove");

	}

//	Finding travel based on its id
	@Override
	public Travels searchTravels(int id) {

		Optional<Travels> travels = td.findById(id);
		if (!travels.isEmpty()) {
			if (!travels.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Travel is not available now");
			}
			return travels.get();
		}
		throw new TravelsNotFoundException("Travels not found to view");
	}
	
//	Getting all the travels available to serve

	@Override
	public List<Travels> viewTravels() {

		List<Travels> travelsList = td.findAll();

		if (!travelsList.isEmpty()) {
			return travelsList.stream().filter(a -> a.isStatus()).toList();
		}
		throw new EmptyTravelListException("Travels list is empty");
	}

}
