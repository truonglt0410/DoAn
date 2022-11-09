package com.edu.fpt.hoursemanager.common.enums;

import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProviderAccount {
    DATABASE("database"),
    GOOGLE("google");

    private final String type;

    public static ProviderAccount lookUp(String type) throws HouseManagerExceptions{
        return Arrays.stream(ProviderAccount.values())
                .filter(enumProvider -> enumProvider.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new HouseManagerExceptions("Not found provider account" + type));
    }


}
