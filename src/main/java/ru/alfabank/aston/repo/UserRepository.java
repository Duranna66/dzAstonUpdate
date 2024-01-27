package ru.alfabank.aston.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alfabank.aston.entites.UserApp;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
     List<UserApp> findAll();

    UserApp save(UserApp userApp);

    @Override
    void delete(UserApp entity);
}
