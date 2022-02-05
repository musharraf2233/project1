package com.repopack;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Accounts;

public interface AccountDAO {

	 
    void saveEmployee(Accounts accounts);
     
     
    List<Accounts> findAllEmployees();
    
    Accounts findAccountByName(String username);
    
    List<Accounts> findAccountByPass(String password);
    
    Accounts findusernameAndpassword(String username,String password);
 
}

