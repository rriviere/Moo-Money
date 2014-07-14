package com.riviere.moomoney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * For all access denied error go through this controller and show the access denied view.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
public class AccessDeniedController {
 
	 /**
	  * Show the access denied view 
      * @return
	 */
	@RequestMapping(value = "/accessDenied.htm")
     public String accessDenied() {
           return "accessDenied"; 
      }	
	 
	 /**
	  * Show the error view
	  *  
	  * @return
	 */
	@RequestMapping(value = "/error.htm")
     public String error() {
           return "error"; 
      }		 
    
}