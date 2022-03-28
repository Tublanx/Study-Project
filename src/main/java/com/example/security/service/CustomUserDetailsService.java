package com.example.security.service;

import com.example.security.entity.Member;
import com.example.security.repository.MemberRepository;
import com.example.security.userDetail.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id);

        if(member == null) {
            throw new UsernameNotFoundException("User not Found");
        }

        return new CustomUserDetails(member);
    }
}
