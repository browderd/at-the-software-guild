/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.controller;

import com.mycompany.vendingmachinewebapp.dao.VendingMachineDAO;
import com.mycompany.vendingmachinewebapp.dto.Change;
import com.mycompany.vendingmachinewebapp.dto.Item;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class MainController {

    private VendingMachineDAO dao;
    
    @Inject
    public MainController(VendingMachineDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHome() {
        return "home";
    }

    @RequestMapping(value="/menu", method=RequestMethod.GET)
    public String displayMenu() {
        return "menu";
    }
    
    @RequestMapping(value="/popMenu", method=RequestMethod.GET)
    @ResponseBody
    public List<Item> populateMenu() {
        return dao.returnAll();
    }
    
    @RequestMapping(value="/addBalance", method=RequestMethod.POST)
    public String storeBalance(HttpServletRequest request, Model model) {
        double amount = Double.parseDouble(request.getParameter("balanceStr"));
        dao.setBalance(amount);
        model.addAttribute("balance", amount);
        return "redirect:menu";
    }

    @RequestMapping(value="/updateBalance", method=RequestMethod.GET)
    public String updateBalanceForm (){
        return "updateBalance";
    }
    
    @RequestMapping(value = "balance", method = RequestMethod.GET)
    @ResponseBody
    public double showBal() {
        return dao.getBalance();
    }
    
    @RequestMapping(value="/updateBalance", method=RequestMethod.POST)
    public String updateBal(HttpServletRequest request, Model model) {
        double additional = Double.parseDouble(request.getParameter("additionalStr"));
        dao.setBalance(additional + dao.getBalance());
        model.addAttribute("balance", dao.getBalance());
        return "redirect:menu";
    }
    
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item buyItemPrompt(@PathVariable("id") int id) {
        return dao.getItemByID(id); 
    }
    
    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Change buyItem(@PathVariable("id") int id) {
        return dao.purchase(id); 
    }

}
