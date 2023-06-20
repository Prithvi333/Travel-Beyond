package com.masai.service;

import java.util.List;

import com.masai.entity.Packages;

public interface PackageOps {

	Packages addPackage(int hotelId, int destinationId, Packages pack);

	Packages deletePackage(int packageId);

	Packages viewPackage(int packageId);

	List<Packages> viewAllPackages();
}
