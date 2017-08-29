package com.example.demo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServiceImpl implements UserDetailsService
{
	@Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(username))
        {
        	throw new UsernameNotFoundException("Username is empty");
        }

        MemberEntity memberEntity = memberRepository.findByLoginId(username);
        if (memberEntity == null)
        {
        	throw new UsernameNotFoundException("User not found: " + username);
        }

        return memberEntity;
    }

//    @Transactional
//    public void registerAdmin(int id, String password) {
//        MemberEntity member = new MemberEntity(id, password);
//        member.setAdmin(true);
//        memberRepository.save(member);
//    }
//
//    @Transactional
//    public void registerUser(int id, String password) {
//    	MemberEntity member = new MemberEntity(id, password);
//    	memberRepository.save(member);
//    }
    
    
}