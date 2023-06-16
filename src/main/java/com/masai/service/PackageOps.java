package com.masai.service;

import java.util.List;

import com.masai.entity.Packages;

public interface PackageOps {

	Packages addPackage(int hotelId, Packages pack);

	Packages deletePackage(int packageId);

	Packages viewPackage(int packageId);

	List<Packages> viewAllPackages();
}
