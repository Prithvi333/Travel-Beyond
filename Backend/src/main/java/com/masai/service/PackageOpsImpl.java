package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Destination;
import com.masai.entity.Hotel;
import com.masai.entity.Packages;
import com.masai.exception.EmptyPackageListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.HotelNotFoundException;
import com.masai.exception.PackageNotFoundException;
import com.masai.repository.DestinationDao;
import com.masai.repository.HotelDao;
import com.masai.repository.PackageDao;

@Service
public class PackageOpsImpl implements PackageOps {

	@Autowired
	PackageDao pd;
	@Autowired
	HotelDao hd;
	@Autowired
	DestinationDao dd;

//	Using this method admin can add the packages available based on hotel id and destination id
	@Override
	public Packages addPackage(int hotelId, int destinationId, Packages pack) {

		Optional<Hotel> hotel = hd.findById(hotelId);
		Optional<Destination> destination = dd.findById(destinationId);

		if (!hotel.isEmpty()) {
			if (!hotel.get().isStats()) {
				throw new EntityAlreadyAlteredException("Unable to add package in deleted hotel");
			}
		}
		if (!hotel.isEmpty()) {
			if (!destination.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to add package in deleted destination");
			}
			pack.setStatus(true);
			pack.setDestination(destination.get());
			destination.get().getPackagesList().add(pack);
			pack.setHotel(hotel.get());
			return pd.save(pack);
		}
		throw new HotelNotFoundException("Hotel not found with the given id");

	}
// Admin can also delete the package using this method if any mismatch is happened
	@Override
	public Packages deletePackage(int packageId) {

		Optional<Packages> packages = pd.findById(packageId);
		if (!packages.isEmpty()) {
			if (!packages.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to remove already deleted package");
			}
			packages.get().setStatus(false);
			return pd.save(packages.get());
		}
		throw new PackageNotFoundException("No package is found to remove");
	}
//  From there customer can view the package based on that he can take the id from there and then also able to book it
	@Override
	public Packages viewPackage(int packageId) {
		Optional<Packages> packages = pd.findById(packageId);
		if (!packages.isEmpty()) {
			if (!packages.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Package is not exist now");
			}
			return packages.get();
		}
		throw new PackageNotFoundException("No package is found to view");
	}
// Using the functionality customer can be able to see all the package at the same time 
	@Override
	public List<Packages> viewAllPackages() {
		List<Packages> packages = pd.findAll();
		if (!packages.isEmpty())
			return packages.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyPackageListException("Package list is empty");
	}

}
