package com.example.demo.web;

import org.apache.commons.lang3.StringUtils;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.form.AddUsuarioForm;
import com.example.demo.form.AutentificaForm;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.DepartmentRepository;

@Controller
@RequestMapping("/admin/usuario")
@SessionAttributes(names="keyword")
public class UsuarioController {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@ModelAttribute("keyword")
    public String setRequestForm(String keyword){
        return keyword;
    }
	
    @RequestMapping("")
    public String index() {
        return "usuario/buscar";
    }

    @RequestMapping(value="/buscar", method=RequestMethod.GET)
    public String index2() {
        return "usuario/buscar";
    }

    @RequestMapping(value="/buscar", method=RequestMethod.POST)
    public ModelAndView search(String keyword) {
    	
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/buscar");
        
        if (StringUtils.isNotEmpty(keyword)) {
          List<MemberEntity> list = memberRepository.findUsers(keyword);
          
//          if (CollectionUtils.isEmpty(list)) {
//            String message = msg.getMessage("actor.list.empty", null, Locale.JAPAN);
//            mv.addObject("emptyMessage", message);
//          }
          
          mv.addObject("list", list);
          mv.addObject("keyword", keyword);
          setRequestForm(keyword);
        }
        return mv;
    }

    @ModelAttribute
    AddUsuarioForm setupForm() {
        return new AddUsuarioForm();
    }
    
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addIndex(Model model) {
    	
    	model.addAttribute("form", new AddUsuarioForm());
    	ModelAndView mv = new ModelAndView();
    	
    	List<DepartmentEntity> list = departmentRepository.findByEnable(1);
    	mv.addObject("departmentList", list);
        mv.setViewName("usuario/add");
    	
        return mv;
   }

    @RequestMapping(value="/add", params = "save", method=RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("form") @Valid AddUsuarioForm form, BindingResult result) {
    	
        ModelAndView mv = new ModelAndView();

        MemberEntity member = new MemberEntity();
        BeanUtils.copyProperties(form, member);

        if (result.hasErrors()) {
        
        	List<DepartmentEntity> list = departmentRepository.findByEnable(1);
        	mv.addObject("departmentList", list);
        	mv.setViewName("usuario/add");

        } else{
        
        	DepartmentEntity entity = departmentRepository.findByName(form.getDepartmentname());
        	member.setDepartment(entity);
        	
	        memberRepository.save(member);
	 
	        mv.setViewName("usuario/buscar");
	        List<MemberEntity> list = memberRepository.findUsers(member.getLoginId());
	        mv.addObject("list", list);
	        mv.addObject("keyword", member.getLoginId());
	        
        }
        
        return mv;
   }

    @RequestMapping(value="/add", params = "back", method=RequestMethod.POST)
    public ModelAndView back(@ModelAttribute("keyword")String session_keyword) {
    	
        ModelAndView mv = new ModelAndView();

        if (StringUtils.isNotEmpty(session_keyword)) {
            List<MemberEntity> list = memberRepository.findUsers(session_keyword);
            mv.addObject("list", list);
            mv.addObject("keyword",session_keyword);
        }
       
    	mv.setViewName("usuario/buscar");

        return mv;
   }

	@RequestMapping(value="/{loginId}", method=RequestMethod.GET)
    public ModelAndView edit(@PathVariable("loginId") String loginId) {
    	
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("usuario/edit");
    	MemberEntity entity = memberRepository.findByLoginId(loginId);
    	mv.addObject("usuario", entity);
    	
		return mv;
    }
    
	@RequestMapping(value="delete/{loginId}", method=RequestMethod.POST)
    public ModelAndView delete(@PathVariable("loginId") String loginId, String keyword) {
    	
    	ModelAndView mv = new ModelAndView();

    	MemberEntity entity = memberRepository.findByLoginId(loginId);
    	entity.setEnabled(0);
    	memberRepository.save(entity);
    	
        if (StringUtils.isNotEmpty(keyword)) {
            List<MemberEntity> list = memberRepository.findUsers(keyword);            
            mv.addObject("list", list);
            mv.addObject("keyword", keyword);
        }
  	
    	
    	mv.setViewName("usuario/buscar");
   	
		return mv;
    }
    

}