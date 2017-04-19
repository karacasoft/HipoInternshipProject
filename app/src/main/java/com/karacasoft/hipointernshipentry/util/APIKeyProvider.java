package com.karacasoft.hipointernshipentry.util;

/**
 * Created by mahmutkaraca on 4/19/17.
 */

public class APIKeyProvider {
    private static final String hashKey = "WdJ9a3TbaZS4oTpywHZTexoDIC0jNzx1";
    public static final short[] apiKeyEncrypted = {
            136, 155, 172, 111, 195, 153, 132, 197, 147, 142, 132, 154, 164, 181, 161, 172, 219, 121,
            191, 181, 156, 218, 212, 121, 171, 167, 96, 158, 132, 172, 219, 97};
    public static String getAPIKey() {
        String apiKey = "";
        int index = 0;
        for (short s : apiKeyEncrypted) {
            apiKey += (char)(s - hashKey.charAt(index));
            index++;
            if(index >= hashKey.length()) {
                index = 0;
            }
        }
        return apiKey;
    }
}
