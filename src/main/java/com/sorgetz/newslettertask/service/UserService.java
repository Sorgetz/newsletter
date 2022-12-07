package com.sorgetz.newslettertask.service;

import com.sorgetz.newslettertask.dto.req.UserReqDTO;
import com.sorgetz.newslettertask.dto.res.UserResDTO;
import com.sorgetz.newslettertask.model.Users;
import com.sorgetz.newslettertask.repository.UsersRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    public UserResDTO create(UserReqDTO dto){

        if(dto.getName() == null) throw new ServiceException("User name cannot be null");
        if(dto.getEmail() == null) throw new ServiceException("User email cannot be null");

        Users user = new Users();
        BeanUtils.copyProperties(dto, user);

        this.repository.save(user);

        return UserResDTO.toRes(user);
    }

    public Boolean isUserBirthday(Users user){
        if(user.getBirthdate() == null) return Boolean.FALSE;
        
        return user.getBirthdate().getMonth().equals(LocalDate.now().getMonth())
                && user.getBirthdate().getDayOfMonth() == LocalDate.now().getDayOfMonth();
    }

    public List<Users> getAllUsers(){
        return this.repository.findAll();
    }


}
