package com.apartment.repositories;

import com.apartment.entity.Member;
import com.apartment.entity.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

}
