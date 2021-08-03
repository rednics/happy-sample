package com.happyhouse.server.api.repository;

import com.happyhouse.server.api.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserRepository {

    /*
        findByLastnameAndFirstname
        findByLastnameOrFirstname
        findByFirstname,findByFirstnameIs,findByFirstnameEquals
        findByStartDateBetween
        findByAgeLessThan
        findByAgeLessThanEqual
        findByAgeGreaterThan
        findByAgeGreaterThanEqual
        findByStartDateAfter
        findByStartDateBefore
        findByAgeIsNull
        findByAge(Is)NotNull
        findByFirstnameLike
        findByFirstnameNotLike
        findByFirstnameStartingWith
        findByFirstnameEndingWith
        findByFirstnameContaining
        findByAgeOrderByLastnameDesc
        findByLastnameNot
        findByAgeIn(Collection<Age> ages)
        findByAgeNotIn(Collection<Age> age)
        findByActiveTrue()
        findByActiveFalse()
        findByFirstnameIgnoreCase
    */

    List<User> findAll();
    User findById(Integer id);
    User findByEmail(Map<String, Object> sqlParam);
    void create(User user);
    void update(User user);
    void delete(Integer id);
}