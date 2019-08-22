package com.ihorcompany.fd.serviceImplement;

import com.ihorcompany.fd.exception.StorageException;
import com.ihorcompany.fd.exception.StorageFileNotFoundException;
import com.ihorcompany.fd.service.StorageService;
import com.ihorcompany.fd.serviceImplement.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileUploadStorageService implements StorageService {

    private final Path ROOT_LOCATION;

    @Autowired
    public FileUploadStorageService(StorageProperties storageProperties) {
        ROOT_LOCATION = Path.of(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(ROOT_LOCATION);
        }catch (IOException e){
            throw new StorageException("Could not initialise storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()){
                throw new StorageException("Failed to store file with relative path"+filename);
            }
            if (filename.contains("..")){
                throw new StorageException("Failed to store file with relative path"+filename);
            }
            try(InputStream stream = file.getInputStream()) {
                Files.copy(stream, this.ROOT_LOCATION.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            throw new StorageException("Failed to store file"+filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.ROOT_LOCATION, 1)
                    .filter(path -> !path.equals(ROOT_LOCATION))
                    .map(ROOT_LOCATION::relativize);
        }catch (IOException e){
            throw new StorageException("There are no stored files!", e);
        }
    }

    @Override
    public Path load(String filename) {
        return ROOT_LOCATION.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try{
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read "+filename);
            }
        }catch (MalformedURLException e){
            throw new StorageFileNotFoundException("Could not read "+filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(ROOT_LOCATION.toFile());
    }
}
