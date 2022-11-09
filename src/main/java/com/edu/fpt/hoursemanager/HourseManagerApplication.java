package com.edu.fpt.hoursemanager;

import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.account.service.RoleService;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.form.CreateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.service.BuildingManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
@Slf4j
public class HourseManagerApplication implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BuildingManageService buildingManageService;

	public static void main(String[] args) {
		SpringApplication.run(HourseManagerApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("----------------- Start system");
		log.info("----------------- Inser Role");
		List<Role> roleList =  List.of(new Role("KH", "Khách Hàng"),new Role("CN", "Chủ Nhà"));
		List<Role> rolesAfterSave = roleService.saveRole(roleList);
		List<Building> buildingList = List.of(new Building("Mỹ Đình ", "Bến xe Mỹ Đình", null, "description", "utilities", "1. Không vứt rác bừa bãi. "));
		rolesAfterSave.stream().forEach(System.out::println);
		List<Account> accounts = List.of(new Account("admin@gmail.com","12345", ProviderAccount.DATABASE,rolesAfterSave, buildingList));
		List<Account> accountAfterSave = accountService.saveAccount(accounts);
		accountAfterSave.stream().forEach(Account::toString);
//		CreateBuildingForm createBuildingForm = new CreateBuildingForm("Sân bóng", "Hà Nội", "Mỹ Đình", "Cầu Giấy","120 Duy Tân","một nhà trọ hạnh phúc","","máy giặt","1. về trước 11h","","1");
//		boolean createBuildingStatus = buildingManageService.addBuilding(createBuildingForm);
//		log.info(String.valueOf(createBuildingStatus));
	}
}
