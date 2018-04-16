package rocks.gebsattel.hochzeit.services.security

import org.jasypt.util.password.StrongPasswordEncryptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor strongEncryptor

    @Autowired
    void setStrongEncryptor(StrongPasswordEncryptor strongEncryptor) {
        this.strongEncryptor = strongEncryptor
    }

    @Override
    String encryptString(String input) {
        strongEncryptor.encryptPassword(input)
    }

    boolean checkPassword(String plainPassword, String encryptedPassword){
        strongEncryptor.checkPassword(plainPassword, encryptedPassword)
    }

}
