package com.scoops.brainfreeze.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Intentionally vulnerable file upload for A08 Software and Data Integrity Failures.
 * - No file type validation
 * - No size limits enforced here
 * - Files stored in a predictable location
 */
@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("success", false);
            response.put("message", "File is empty");
            return response;
        }

        // Intentionally weak: no content-type or extension whitelist
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalName = file.getOriginalFilename();
        String storedName = UUID.randomUUID() + "_" + originalName;
        Path path = Paths.get(UPLOAD_DIR + storedName);
        Files.write(path, file.getBytes());

        response.put("success", true);
        response.put("message", "File uploaded successfully");
        response.put("originalName", originalName);
        response.put("storedName", storedName);
        response.put("size", file.getSize());
        // In a real vulnerable scenario this path might be web-accessible
        response.put("path", path.toString());

        return response;
    }
}
