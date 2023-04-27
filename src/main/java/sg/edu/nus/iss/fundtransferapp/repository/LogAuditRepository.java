package sg.edu.nus.iss.fundtransferapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogAuditRepository {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void logTransaction(String transactionId, String jsonString) {
        redisTemplate.opsForValue().set(transactionId, jsonString);
    }

}
