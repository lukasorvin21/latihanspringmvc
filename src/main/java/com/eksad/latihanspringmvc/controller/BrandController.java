package com.eksad.latihanspringmvc.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eksad.latihanspringmvc.model.Brand;
import com.eksad.latihanspringmvc.model.Product;
import com.eksad.latihanspringmvc.repository.BrandRepositoryDao;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	BrandRepositoryDao brandRepositoryDao;

	@RequestMapping("")
	public String index(Model model) {

		List<Brand> list = brandRepositoryDao.findAll();

		model.addAttribute("listBrand", list);

		return "listBrand";
	}
	//add
	@RequestMapping("/add")
	public String addBrand(Model model){
		Brand brand = new Brand();

		model.addAttribute("brand", brand);

		return "addBrand";
	}
	//save
	@RequestMapping(value =  "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute Brand brand) {
		brandRepositoryDao.save(brand);

		return "redirect:/brand";
	}
	//update/edit
//	 @RequestMapping(value = "edit",method = RequestMethod.POST)
//	    public String editBrand(@ModelAttribute("id") Brand brand){
//	        brandRepositoryDao.update(brand);
//	        return "redirect:/";
//	    }
//	 
//	 
//	 @GetMapping("edit/{id}")
//	    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//	        Student student = studentRepository.findById(id)
//	            .orElseThrow(() - > new IllegalArgumentException("Invalid student Id:" + id));
//	        model.addAttribute("student", student);
//	        return "update-student";
//	    }

	//edit
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Brand update (@RequestBody Brand brand, @PathVariable Long id) {			
		Brand brandSelected = brandRepositoryDao.findById(id).orElse(null) ;
		if (brandSelected !=null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProduct_type(brand.getProduct_type());
			brandRepositoryDao.save(brandSelected);
			return brandRepositoryDao.save(brandSelected);
		} else {
			return null;
		}	    
	}
	//delete
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable ("id") Long id) {
		Brand brand = brandRepositoryDao.getOne(id);
		if(brand==null)
		{
			throw new EntityNotFoundException("Not Found");
		}
		brandRepositoryDao.delete(brand);
		return "redirect:/brand";
	}	
	//edit
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBrand(@PathVariable Long id, Model model) {
		Brand brand = brandRepositoryDao.getOne(id);
		model.addAttribute("brand", brand);
		return "editBrand";
	}
}
