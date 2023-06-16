package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Hotel;
import com.masai.entity.Packages;
import com.masai.exception.EmptyPackageListException;
import com.masai.exception.HotelNotFoundException;
import com.masai.exception.PackageNotFoundException;
import com.masai.repository.HotelDao;
import com.masai.repository.PackageDao;

public class PackageOpsImpl implements PackageOps {

	@Autowired
	PackageDao pd;
	@Autowired
	HotelDao hd;

	@Override
	public Packages addPackage(int hotelId, Packages pack) {

		Optional<Hotel> hotel = hd.findById(hotelId);

		if (!hotel.isEmpty()) {
			pack.setStatus(true);
			pack.setHotel(hotel.get());
			return pd.save(pack);

		}
		throw new HotelNotFoundException("Hotel not found with the given id");
	}

	@Override
	public Packages deletePackage(int packageId) {

		Optional<Packages> packages = pd.findById(packageId);
		if (!packages.isEmpty()) {
			packages.get().setStatus(false);
			return pd.save(packages.get());
		}
		throw new PackageNotFoundException("No package is found to remove");
	}

	@Override
	public Packages viewPackage(int packageId) {
		Optional<Packages> packages = pd.findById(packageId);
		if (!packages.isEmpty()) {
			return packages.get();
		}
		throw new PackageNotFoundException("No package is found to view");
	}

	@Override
	public List<Packages> viewAllPackages() {
		List<Packages> packages = pd.findAll();
		if (!packages.isEmpty())
			return packages;
		throw new EmptyPackageListException("Package list is empty");
	}

}
