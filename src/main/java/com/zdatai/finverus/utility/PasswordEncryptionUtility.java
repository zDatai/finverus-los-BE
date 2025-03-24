package com.zdatai.finverus.utility;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Utility for encrypt plain text
 *
 */
@Component
public class PasswordEncryptionUtility {
	
	public String encryptPassword(String password) {
		return Hashing.sha256()
				  .hashString(password, StandardCharsets.UTF_8)
				  .toString();
	}
}
