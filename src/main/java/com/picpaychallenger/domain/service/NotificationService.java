package com.picpaychallenger.domain.service;

import com.picpaychallenger.domain.user.User;
import com.picpaychallenger.dto.NotificationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationRequestDTO notificationRequest = new NotificationRequestDTO(email, message);

       ResponseEntity<String> response = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest ,String.class);

       if(!(response.getStatusCode() == HttpStatus.OK)) {
           throw new Exception("Error while trying to send notification");
       }

    }
}
