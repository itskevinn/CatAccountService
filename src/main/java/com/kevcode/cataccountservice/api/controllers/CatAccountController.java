package com.kevcode.cataccountservice.api.controllers;

import com.kevcode.cataccountservice.application.CatAccount.http.Dto.CatAccountDto;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.CatAccountRequest;
import com.kevcode.cataccountservice.application.CatAccount.http.Request.NationalTransactionRequest;
import com.kevcode.cataccountservice.application.CatAccount.service.ICatAccountService;
import com.kevcode.cataccountservice.application.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CatAccount")
public class CatAccountController {
    @Autowired
    private ICatAccountService _catAccountService;

    @GetMapping("/GetByPerson/{personId}")
    public Response<List<CatAccountDto>> getCatAccounts(@PathVariable("personId") Long personId) {
        return _catAccountService.findAllByPersonId(personId);
    }

    @PostMapping("/Create")
    public Response<CatAccountDto> save(@RequestBody CatAccountRequest request) {
        return _catAccountService.save(request);
    }


    @PostMapping("/Widthdraw")
    public Response<Long> withdraw(@RequestBody NationalTransactionRequest request) {

        return _catAccountService.withdraw(request);
    }

    @PostMapping("/Deposit")
    public Response<Long> deposit(NationalTransactionRequest request) {
        return _catAccountService.deposit(request);
    }

    @GetMapping("/GetBalance/{accountId}")
    public Response<Long> deposit(@PathVariable("accountId") Long accountId) {
        return _catAccountService.getBalance(accountId);
    }
}
