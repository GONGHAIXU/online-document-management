package com.example.onlinedocumentsystem.service;

import com.example.onlinedocumentsystem.dao.FileRepository;
import com.example.onlinedocumentsystem.dao.UserRepository;
import com.example.onlinedocumentsystem.domain.FileMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    public List<FileMessage> getFileMessageByUploadPerson(String uploadPerson){
        return fileRepository.findByUploadPerson(uploadPerson);
    }
    public FileMessage getFileMessageById(long id){
        return fileRepository.findById(id).get(0);
    }
    public void changeName(FileMessage fileMessage){
        String newName = fileMessage.getFileName();
        fileMessage = getFileMessageById(fileMessage.getId());
        fileMessage.setFileName(newName);
        fileRepository.save(fileMessage);
    }
}
