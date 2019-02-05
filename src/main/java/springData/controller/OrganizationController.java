package springData.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springData.domain.Organization;
import springData.repository.OrganizationRepository;

@Controller
@RequestMapping("/Organization")

public class OrganizationController {

   @Autowired
   OrganizationRepository organizationRepo;

   @RequestMapping(value = "/")
   public String organizationHome() {
      return "";
   }

   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public String organizationAdd() {
      return "";
   }

   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public String organizationDelete() {
      return "";
   }
}