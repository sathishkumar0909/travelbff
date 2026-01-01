package com.sk.travel.repository;

import com.sk.travel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface UserRepository extends JpaRepository<User, Long>{
//    User findByPhonenumber(String phonenumber);
    User findByEmail(String email);


}
