package cz.mendelu.pef.petstore.extension

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
	val pattern = Pattern.compile("^(?:[\\w\\!\\#\\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`\\{\\|\\}\\~]+\\.)*[\\w\\!\\#\\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`\\{\\|\\}\\~]+@(?:(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9\\-](?!\\.)){0,61}[a-zA-Z0-9]?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9\\-](?!\$)){0,61}[a-zA-Z0-9]?)|(?:\\[(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\]))\$")
	if (this == null) return false
	return pattern.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
	return this.length > 7
}