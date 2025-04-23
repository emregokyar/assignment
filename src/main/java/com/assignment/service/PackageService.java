package com.assignment.service;

import com.assignment.entity.Package;
import com.assignment.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public void savePck(Package pck) {
        packageRepository.save(pck);
    }

    public void deletePck(Package pck) {
        packageRepository.delete(pck);
    }

    public Package findByPackageName(String name) {
        return packageRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Can not find a package with this name"));
    }
}
