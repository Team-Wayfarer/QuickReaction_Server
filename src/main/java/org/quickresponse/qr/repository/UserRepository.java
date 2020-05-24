package org.quickresponse.qr.repository;

import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.dto.UserUpdateRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByNameAndContact(String name, String contact);

    @Modifying
    @Query("update User set contact = :con where id = :id")
    public void updateContact(@Param("id") long id, @Param("con") String contact);
}
