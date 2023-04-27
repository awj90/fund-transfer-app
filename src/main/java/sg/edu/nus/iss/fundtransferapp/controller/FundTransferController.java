package sg.edu.nus.iss.fundtransferapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.fundtransferapp.models.Account;
import sg.edu.nus.iss.fundtransferapp.models.Transfer;
import sg.edu.nus.iss.fundtransferapp.services.AppService;

@Controller
public class FundTransferController {
    
    @Autowired
    AppService appService;

    @GetMapping(path="/", produces="text/html")
    public String renderViewZero(Model model, HttpSession session) {
        List<Account> accounts = appService.getAllAccounts();
        session.setAttribute("accounts", accounts);
        model.addAttribute("accounts", accounts);
        model.addAttribute("transfer", new Transfer());
        return "view0";
    }

    @PostMapping(path="/transfer", produces="text/html")
    public String postTransferHandler(@ModelAttribute @Valid Transfer transfer, BindingResult bindingResult, HttpSession session, Model model) {

        String fromAccountId = transfer.getFromAccountId();
        String toAccountId = transfer.getToAccountId();
        List<Account> accounts = (List<Account>) session.getAttribute("accounts");
        model.addAttribute("accounts", accounts);

        // C0
        if (!appService.isExisting(fromAccountId)) {
            ObjectError err = new ObjectError("globalError", "Source account id %s is invalid.".formatted(fromAccountId));
            bindingResult.addError(err);
        }
        if (!appService.isExisting(toAccountId)) {
            ObjectError err = new ObjectError("globalError", "Destination account id %s is invalid.".formatted(toAccountId));
            bindingResult.addError(err);
        }

        // C1 is syntactic validation handled by @Size annotation in models

        // C2
        if (fromAccountId.equals(toAccountId)) {
            ObjectError err = new ObjectError("globalError", "Source account and destination account cannot be same.");
            bindingResult.addError(err);
        }

        // C3, C4 are syntactic validation handled by @Min annotation in models

        // C5
        if (!appService.isBalanceSufficient(fromAccountId, transfer.getAmount())) {
            ObjectError err = new ObjectError("globalError", "Source account id %s has insufficient funds.".formatted(toAccountId));
            bindingResult.addError(err);
        }

        if (bindingResult.hasErrors()) {
            return "view0";
        }

        return "";
    }
}
