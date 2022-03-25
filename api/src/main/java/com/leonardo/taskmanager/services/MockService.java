package com.leonardo.taskmanager.services;

import java.util.Arrays;

import com.leonardo.taskmanager.model.User;
import com.leonardo.taskmanager.repositories.AttachmentRepository;
import com.leonardo.taskmanager.repositories.BacklogNotificationRepository;
import com.leonardo.taskmanager.repositories.BacklogRepository;
import com.leonardo.taskmanager.repositories.ClassificationRepository;
import com.leonardo.taskmanager.repositories.ClientRepository;
import com.leonardo.taskmanager.repositories.DetailedHourRepository;
import com.leonardo.taskmanager.repositories.ProjectRepository;
import com.leonardo.taskmanager.repositories.TaskRepository;
import com.leonardo.taskmanager.repositories.TeamRepository;
import com.leonardo.taskmanager.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MockService {

    private final AttachmentRepository attachmentRepository;
    private final BacklogRepository backlogRepository;
    private final BacklogNotificationRepository backlogNotificationRepository;
    private final ClassificationRepository classificationRepository;
    private final ClientRepository clientRepository;
    private final DetailedHourRepository detailedHourRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public MockService(
        AttachmentRepository attachmentRepository, 
        BacklogRepository backlogRepository, 
        BacklogNotificationRepository backlogNotificationRepository, 
        ClassificationRepository classificationRepository, 
        ClientRepository clientRepository,
        DetailedHourRepository detailedHourRepository,
        ProjectRepository projectRepository,
        TaskRepository taskRepository,
        TeamRepository teamRepository,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ){
        this.attachmentRepository = attachmentRepository;
        this.backlogRepository = backlogRepository;
        this.backlogNotificationRepository = backlogNotificationRepository;
        this.classificationRepository = classificationRepository;
        this.clientRepository = clientRepository;
        this.detailedHourRepository = detailedHourRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void mock(){

        User user1 = User.builder()
            .name("Leonardo")
            .lastName("Lacerda")
            .email("leon.lacerda2015@gmail.com")
            .telephoneNumber("11953248804")
            .username("leon1298")
            .password(passwordEncoder.encode("leon1298"))
            .build();

        User user2 = User.builder()
            .name("Joanna")
            .lastName("Dark")
            .email("dark.joanna@gmail.com")
            .telephoneNumber("11953648795")
            .username("joanna")
            .password(passwordEncoder.encode("dark"))
            .build();


      
        userRepository.saveAll(Arrays.asList(user1, user2));


    }


}
