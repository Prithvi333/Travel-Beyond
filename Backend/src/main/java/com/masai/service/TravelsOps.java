package com.masai.service;

import java.util.List;

import com.masai.entity.Travels;
import com.masai.entity.TravelsDto;

public interface TravelsOps {

	Travels addTravels(Travels travel);

	Travels updateTravels(int travelsId,TravelsDto traveldto);

	Travels removeTravels(int travelsId);

	Travels searchTravels(int travelId);

	List<Travels> viewTravels();
}
