package com.assignment.service;

import com.assignment.entity.Version;
import com.assignment.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    @Autowired
    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void saveVersion(Version version) {
        versionRepository.save(version);
    }

    public void deleteVersion(Version version) {
        versionRepository.delete(version);
    }

    public Version getVersionByName(String name) {
        return versionRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Can not find a version with this associated name"));
    }
}
