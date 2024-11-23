package com.farmsystem.ras.repository;

import com.farmsystem.ras.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// DB 연결하기 위한 용도로 만들어진 것 입니다. 필요한 DB 조작이 필요할 때 연락주세요.
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //username을 매개변수로 user 객체를 반환합니다.
    User findByUsername(String username);

    //DB에 존재하는 username이면 true를 반환합니다.
    boolean existsByUsername(String username);
}
