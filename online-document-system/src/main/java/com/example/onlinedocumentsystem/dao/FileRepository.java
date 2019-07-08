package com.example.onlinedocumentsystem.dao;

import com.example.onlinedocumentsystem.domain.FileMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FileRepository extends JpaRepository<FileMessage,Long> {
    public List<FileMessage> findByUploadPerson(String uploadPerson);
    public List<FileMessage> findById(long id);
}
