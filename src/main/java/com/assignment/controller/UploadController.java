package com.assignment.controller;

import com.assignment.service.AuthorService;
import com.assignment.service.PackageService;
import com.assignment.service.VersionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController {
    private final VersionService versionService;
    private final PackageService packageService;
    private final AuthorService authorService;

    public UploadController(VersionService versionService, PackageService packageService, AuthorService authorService) {
        this.versionService = versionService;
        this.packageService = packageService;
        this.authorService = authorService;
    }
}
