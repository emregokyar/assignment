package com.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUploadUtil {

    public static boolean saveFile(String uploadDir, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(multipartFile.getInputStream())) {
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.isDirectory()) {//If the file is dir, create dir
                    Path dirPath = uploadPath.resolve(entry.getName());
                    Files.createDirectories(dirPath);
                } else {
                    Path filePath = uploadPath.resolve(entry.getName());
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zipInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

        } catch (IOException e) {
            throw new IOException("Couldn't extract zip file", e);
        }

        return hasValidJsonFile(uploadPath);
    }

    private static boolean hasValidJsonFile(Path directory) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (Stream<Path> files = Files.walk(directory)) {

            return files.filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".json"))
                    .anyMatch(path -> {
                        try {
                            objectMapper.readTree(path.toFile());
                            return true;
                        } catch (IOException e) {
                            return false;
                        }
                    });
        }
    }
}
