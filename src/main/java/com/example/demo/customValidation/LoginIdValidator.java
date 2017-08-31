package com.example.demo.customValidation;

import javax.validation.*;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;

public class LoginIdValidator implements ConstraintValidator<LoginIdUnique,Object> {

	@Autowired
	MemberRepository memberRepository;

	private String hidId;
	private String loginId;
	
	@Override
	public void initialize(LoginIdUnique annotation) {
        this.hidId = annotation.fieldHidId();
        this.loginId = annotation.fieldLoginId();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		int hidId = (int)beanWrapper.getPropertyValue(this.hidId);
		String loginId = (String)beanWrapper.getPropertyValue(this.loginId);

		MemberEntity entity = memberRepository.findByLoginIdAndEnabled(loginId,1);
		

		if(entity == null){
			return true;
		} else{
			
			if(hidId > 0 && hidId == entity.getId()){
				return true;
			}
			
			return false;
		}
	}

}
