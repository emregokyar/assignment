package com.assignment.controller;

import com.assignment.entity.Package;
import com.assignment.entity.Version;
import com.assignment.service.AuthorService;
import com.assignment.service.PackageService;
import com.assignment.service.VersionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class DownloadController {
    private final VersionService versionService;
    private final PackageService packageService;
    private final AuthorService authorService;

    public DownloadController(VersionService versionService, PackageService packageService, AuthorService authorService) {
        this.versionService = versionService;
        this.packageService = packageService;
        this.authorService = authorService;
    }

    @GetMapping("/{pck}/{version}")
    public Version getVersion(@PathVariable String pck, @PathVariable String version) {
        Package tempPck = packageService.findByPackageName(pck);
        for (var p : tempPck.getVersions()) {
            if (Objects.equals(p.getName(), version)) return versionService.getVersionByName(version);
        }
        return null;
    }

    @GetMapping("/{pck}")
    public Package getPck(@PathVariable String pck) {
        return packageService.findByPackageName(pck);
    }
}
