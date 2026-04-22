package com.example.controller;

import com.example.entity.Customer;
import com.example.repository.AccountRepository;
import com.example.repository.CustomerRepository;
import com.example.repository.DepositorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class BankingController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositorRepository depositorRepository;

    // ✅ ANA SAYFA - Tüm müşterileri listele
    @GetMapping
    public String home(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    // ✅ MÜŞTERİ DETAYLARI - Tek müşteri, hesapları ve yatırımları görüntüle
    @GetMapping("/customer/{id}")
    public String customerDetails(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("accounts", accountRepository.findByCustomerId(id));
            model.addAttribute("depositors", depositorRepository.findByCustomerId(id));
        }
        return "customer-details";
    }

    // ✅ MÜŞTERİ EKLE
    @PostMapping("/customer/add")
    public String addCustomer(@RequestParam String name,
                              @RequestParam String address,
                              @RequestParam String city) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setCity(city);
        customerRepository.save(customer);
        return "redirect:/";
    }

    // ✅ MÜŞTERİ GÜNCELLE - Güncelleme formunu göster
    @GetMapping("/customer/{id}/edit")
    public String editCustomerForm(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "edit-customer";
        }
        return "redirect:/";
    }

    // ✅ MÜŞTERİ GÜNCELLE - Veriyi kaydet
    @PostMapping("/customer/{id}/update")
    public String updateCustomer(@PathVariable Integer id,
                                 @RequestParam String name,
                                 @RequestParam String address,
                                 @RequestParam String city) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(name);
            customer.setAddress(address);
            customer.setCity(city);
            customerRepository.save(customer);
        }
        return "redirect:/customer/" + id;
    }

    // ✅ MÜŞTERİ SİL
    @GetMapping("/customer/{id}/delete")
    public String deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/";
    }
}