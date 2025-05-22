
package com.webApp.CompApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webApp.CompApp.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Метод для поиска пользователя по полю login
    User findByLogin(String login);
    List<User> findByStation_Id(Long stationId); // Находит всех пользователей по station.id
    
    
}
