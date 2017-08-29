package com.example.demo.customValidation;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

public class LoginIdValidator implements ConstraintValidator<LoginIdUnique,String> {

	private LoginIdUnique my;
	
	@Autowired
	MemberRepository memberRepository;

	
	@Override
	public void initialize(LoginIdUnique annotation) {
		this.my = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		MemberEntity entity = memberRepository.findByLoginId(value);
		
		if(entity != null){
			return false;
		} else{
			return true;
		}
	}

}
