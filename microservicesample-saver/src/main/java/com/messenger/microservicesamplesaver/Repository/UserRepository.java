package com.messenger.microservicesamplesaver.Repository;

import com.messenger.microservicesamplesaver.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
