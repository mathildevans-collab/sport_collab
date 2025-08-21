package com.sport.utilisateur_api.utils;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ResourcesUtils {
	public InputStream getKeysFile(String fileName) {
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}
}
