package com.example.security.repository;

import com.example.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id = ?1")
    public Member findById(String id);

}
