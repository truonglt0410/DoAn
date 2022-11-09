package com.edu.fpt.hoursemanager.management.account.repository;

import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.model.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("select a from Account a where a.email = :email and a.deleted = false")
    Account findByUsername(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.account.model.response.AccountResponse(a.id,a.email,a.password,a.authProvider,b.id,b.code,b.name) " +
            "from Account a inner join a.roles b")
    List<AccountResponse> getAllAccounts();

    @Query("select a from Account a where a.id = :id and a.deleted = false")
    Account findAccountById(@Param("id") Long id);
}
