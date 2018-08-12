package com.demir.core.email.control;

public class EmailDomainHelper {

    private static final String DOMAINS[] = {"comeon.com", "cherry.se"};

    public static boolean isDomainValid(final String email) {
        final int index = email.lastIndexOf("@");
        if (index > 1 && index < email.length()) {
            final String domain = email.substring((index + 1)).trim().toLowerCase();
            for (String path : DOMAINS) {
                if (domain.equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }

}
