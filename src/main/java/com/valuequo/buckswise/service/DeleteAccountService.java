package com.valuequo.buckswise.service;

import com.valuequo.buckswise.repository.DeleteAccountRepository;
import com.valuequo.buckswise.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.valuequo.buckswise.domain.User;

@Service
@Transactional
public class DeleteAccountService {

    private final Logger log = LoggerFactory.getLogger(DeleteAccountService.class);

    @Autowired
    private DeleteAccountRepository deleteAccountRepository;

    @Autowired
    private UserRepository userRepository; 

    public void deleteAccount(Long id) {
        deleteAccountRepository.deleteUserDetails(id);
    }

	public Void updateid(Long id) {
       Long newId = (long) 0;
       List<User> user = (List<User>) userRepository.findById(id);
       for (User var : user) {
            Long newId2 = newId;
            var.setId(newId2);
           userRepository.save(var);
       }
       return null;
	}
}
