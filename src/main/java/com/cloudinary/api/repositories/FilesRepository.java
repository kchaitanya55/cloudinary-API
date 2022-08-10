package com.cloudinary.api.repositories;

import com.cloudinary.api.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FilesRepository extends JpaRepository<FileDetails,Long> {
}
