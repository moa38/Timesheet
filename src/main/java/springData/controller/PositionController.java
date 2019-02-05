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

import springData.domain.Position;
import springData.repository.PositionRepository;

@Controller
@RequestMapping("/Position")

public class PositionController {

   @Autowired
   PositionRepository positionRepo;

   @RequestMapping(value = "/")
   public String positionHome() {
      return "";
   }

   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public String positionAdd() {
      return "";
   }

   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public String positionDelete() {
      return "";
   }
}