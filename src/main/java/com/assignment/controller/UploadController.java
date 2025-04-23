package com.assignment.controller;

import com.assignment.entity.Author;
import com.assignment.entity.Package;
import com.assignment.entity.Version;
import com.assignment.service.AuthorService;
import com.assignment.util.FileUploadUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    private final AuthorService authorService;

    public UploadController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("{author}/{pck}/{version}")
    public Author uploadPck(@PathVariable Integer author,
                            @PathVariable String pck,
                            @PathVariable MultipartFile version) {

        if (version.isEmpty() || version.getOriginalFilename() == null) {
            throw new RuntimeException("Uploaded file is empty or invalid");
        }
        String versionName = StringUtils.cleanPath(version.getOriginalFilename());

        Author ath = authorService.findAuthorById(author);
        if (ath == null) {
            ath = new Author();
        }

        Package tempPck = new Package();
        tempPck.setName(pck);

        Version vrs = new Version();
        vrs.setName(versionName);

        ath.addPck(tempPck);
        tempPck.addVersion(vrs);

        tempPck.setAuthorId(ath);
        vrs.setPackageId(tempPck);

        Author savedAuthor = authorService.saveAuthor(ath);

        String versionNam = vrs.getName().replace(".zip", "");
        try {
            String uploadDir = "company/" + ath.getId() + "/" + tempPck.getName() + "/" + versionNam;
            var a = FileUploadUtil.saveFile(uploadDir, version);
            if (!a) throw new RuntimeException("Not a valid json data");

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
        return savedAuthor;
    }
}
