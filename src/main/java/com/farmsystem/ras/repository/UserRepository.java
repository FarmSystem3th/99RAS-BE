package com.farmsystem.ras.repository;

import com.farmsystem.ras.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// DB 연결하기 위한 용도로 만들어진 것 입니다. 필요한 DB 조작이 필요할 때 연락주세요.
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
