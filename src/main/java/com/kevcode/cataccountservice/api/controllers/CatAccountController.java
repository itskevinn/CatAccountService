package com.kevcode.cataccountservice.api.controllers;

import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransaction;
import com.kevcode.cataccountservice.application.CatAccount.service.ICatAccountService;
import com.kevcode.cataccountservice.application.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CatAccount")
public class CatAccountController {
    @Autowired
    private ICatAccountService _catAccountService;

    @GetMapping("/Balance/{accountId}")
    public Response<Long> getBalance(@PathVariable Long accountId) {
        return _catAccountService.getBalance(accountId);
    }

    @PostMapping("/Deposit")
    public Response<Long> deposit(@RequestBody NationalTransaction transaction) {
        return _catAccountService.deposit(transaction);
    }

    @PostMapping("/Withdraw")
    public Response<Long> withdraw(@RequestBody NationalTransaction transaction) {
        return _catAccountService.withdraw(transaction);
    }
}
