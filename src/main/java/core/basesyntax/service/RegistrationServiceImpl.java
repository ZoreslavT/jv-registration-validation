package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 16;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null) {
            throw new RuntimeException("Users login can’t be null");
        }
        if (user.getPassword() == null) {
            throw new RuntimeException("Password can’t be null");
        }
        if (user.getPassword().length() < 6) {
            throw new RuntimeException("Password have to contain at least 6 symbols");
        }
        if (user.getAge() == null) {
            throw new RuntimeException("Users age can’t be null");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RuntimeException("Users age can’t be less than 18 years old");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RuntimeException("User with this login already exist");
        }
        return storageDao.add(user);
    }
}
