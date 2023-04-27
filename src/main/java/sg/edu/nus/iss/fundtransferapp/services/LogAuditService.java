package sg.edu.nus.iss.fundtransferapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.fundtransferapp.models.Transfer;
import sg.edu.nus.iss.fundtransferapp.repository.LogAuditRepository;

@Service
public class LogAuditService {
    
    @Autowired
    LogAuditRepository logAuditRepository;

    public void logTransaction(Transfer transfer) {
        logAuditRepository.logTransaction(transfer.getId(), transfer.toJsonString());
    }

}
