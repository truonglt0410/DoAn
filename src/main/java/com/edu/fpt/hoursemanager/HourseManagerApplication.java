package com.edu.fpt.hoursemanager;

import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.account.service.RoleService;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.service.ContactService;
import com.edu.fpt.hoursemanager.management.directory.service.DirectionManageServices;
import com.edu.fpt.hoursemanager.management.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class HourseManagerApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private DirectionManageServices directionManageServices;

	@Autowired
	private FileService fileService;

	@Autowired
	private ContactService contactService;

	public static void main(String[] args) {
		SpringApplication.run(HourseManagerApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("----------------- Create Directory");
		fileService.init();
		log.info("----------------- Start system");
		log.info("----------------- Inser Role");
		List<Role> roleList =  List.of(new Role("CN", "Chủ Nhà"), new Role("KH", "Khách Hàng"), new Role("QL", "Quản Lý"), new Role("NV", "Nhân Viên"));
		List<Role> rolesAfterSave = roleService.saveRole(roleList);
		List<Building> buildingList = List.of(new Building("name","address", "image", "description", "rulesImage"));
		rolesAfterSave.stream().forEach(System.out::println);
		Contact contact = new Contact("fullName", "phone", new Date(), true, "address");
		List<Account> accounts = List.of(new Account("trungthien2399@gmail.com","12345", ProviderAccount.DATABASE,rolesAfterSave, buildingList, contact));
		List<Account> accountAfterSave = accountService.saveAccount(accounts);
		accountAfterSave.stream().forEach(Account::toString);
		boolean check = directionManageServices.addAllDirectory();
	}

}
