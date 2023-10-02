package com.ecommerce.impl;

import java.util.List;
import java.util.Optional;

import com.ecommerce.Constants.FileType;
import com.ecommerce.Utils.FileExporter;
import com.ecommerce.Utils.TXTFileExporter;
import com.ecommerce.Utils.XMLFileExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.repo.UserRepo;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@Transactional
@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;


	private final SimpMessagingTemplate template;
    public UserServiceImpl(SimpMessagingTemplate template) {
		this.template = template;
	}

	@Override
	public User addUser(User user) {
		List<User> users = userRepo.findAll();
		if (users.size() == 0) {
			user.setAdmin(true);
		}
		
		for (User userExist : users) {
			if (user.getUsername().equals(userExist.getUsername())) {
				//password crypt
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String password = bCryptPasswordEncoder.encode(userExist.getPassword());
				userExist.setUsername(userExist.getUsername());
				userExist.setPassword(password);
				this.template.convertAndSend("/topic/socket/api","new user created");
				return userRepo.save(userExist);
			}
		}
	
		return userRepo.save(user);
	}


	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User editUser(User user, long id) {
		User existUser = userRepo.findById(id).orElse(null);
		existUser.setUsername(user.getUsername());
		existUser.setPassword(user.getPassword());
		existUser.setAdmin(user.isAdmin());
		existUser.setEmail(user.getEmail());
		existUser.setNameOnCard(user.getNameOnCard());
		existUser.setCardNumber(user.getCardNumber());
		existUser.setCvv(user.getCvv());
		existUser.setAddress(user.getAddress());
		return userRepo.save(existUser);
	}

	@Override
	public User findUserById(long id) {
      return userRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteUser(long id) {
		userRepo.deleteById(id);
	} 
	
	@Override
	 public User findByUsername(String username) {
		   Optional<User> users = userRepo.findByUsername(username);
		    if (users.isPresent()) {
		     User user = users.get();
		   return user;
		  }
		   return null;
		 }

	@Override
	public String exportUsersDetails(long id, String fileType) {
		User user = this.findUserById(id);
		FileExporter fileExporter;
		if(fileType.equals(FileType.XML)){
			fileExporter = new XMLFileExporter();
			return fileExporter.exportData(user);
		}else if(fileType.equals(FileType.TXT)){
			fileExporter = new TXTFileExporter();
			return fileExporter.exportData(user);
		}
		return null;
	}


}
