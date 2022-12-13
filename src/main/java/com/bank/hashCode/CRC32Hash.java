package com.bank.hashCode;

import org.jetbrains.annotations.NotNull;

import java.util.zip.CRC32;

public class CRC32Hash {

    public static @NotNull String getHash(@NotNull String code) {
        CRC32 crc = new CRC32();

        crc.update(code.getBytes());

        return Long.toHexString(crc.getValue());
    }
}
