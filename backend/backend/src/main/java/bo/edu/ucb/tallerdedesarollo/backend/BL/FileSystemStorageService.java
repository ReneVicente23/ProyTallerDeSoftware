package bo.edu.ucb.tallerdedesarollo.backend.BL;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;


    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));
            return resource;
            
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file " + filename);
        }
    }

    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }
}
