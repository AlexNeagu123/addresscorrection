package com.pa.utility;

import com.pa.entity.Address;
import com.pa.entity.FieldToken;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The <tt>AddressNormalizer</tt> class contains useful methods that based on an {@link Address} object received by the API,
 * deletes all the invalid characters and extra spaces and makes all the text to be lowercase
 * <p>
 * All the found tokens are returned as a list of {@link FieldToken} objects
 *
 * @author Alex Neagu
 * @author Cristian Fiodorov
 */
public class AddressNormalizer {
    private static final List<Pair<Integer, Integer>> badCharactersRange = Arrays.asList(Pair.of(0, 64), Pair.of(91, 95), Pair.of(123, 190));
    private static final int WORD_LIMIT = 5;

    /**
     * Given a list that contains {@link FieldToken} objects formed by only one word,
     * this method computed a new list generated by combining consecutive {@link FieldToken} objects from the initial list (up to 5).
     *
     * @param fieldTokens The initial list of {@link FieldToken} objects
     * @return The new list of compound tokens
     */
    public static List<FieldToken> getCompoundTokens(List<FieldToken> fieldTokens) {
        List<FieldToken> compoundTokens = new ArrayList<>();
        for (int i = 0; i < fieldTokens.size(); ++i) {
            StringBuilder currentToken = new StringBuilder();
            Integer currentFieldId = fieldTokens.get(i).getFieldId();
            for (int j = i; j < Math.min(i + WORD_LIMIT, fieldTokens.size()); ++j) {
                if (!Objects.equals(currentFieldId, fieldTokens.get(j).getFieldId())) {
                    break;
                }
                if (j > i) {
                    currentToken.append(" ");
                }
                currentToken.append(fieldTokens.get(j).getToken());
                compoundTokens.add(new FieldToken(currentToken.toString(), currentFieldId));
            }
        }
        return compoundTokens;
    }

    /**
     * @param address The {@link Address} object received by the API
     * @return A list of {@link FieldToken} object that represents all the words extracted after the normalization
     * process (along with their corresponding fieldId)
     */
    public static List<FieldToken> normalizeAddress(Address address) {
        transformNullToEmptyString(address);
        makeAddressLowercase(address);

        List<FieldToken> allFieldTokens = transformAddressToFieldTokens(address);
        for (FieldToken fieldToken : allFieldTokens) {
            String strToken = fieldToken.getToken();
            fieldToken.setToken(removeBadCharacters(strToken));
        }

        System.out.println("Normalized field tokens: " + allFieldTokens);
        return allFieldTokens;
    }

    private static void transformNullToEmptyString(Address address) {
        if (address.getCountry() == null) {
            address.setCountry("");
        }
        if (address.getState() == null) {
            address.setState("");
        }
        if (address.getCity() == null) {
            address.setCity("");
        }
    }

    private static List<FieldToken> transformAddressToFieldTokens(Address address) {
        List<FieldToken> allFieldTokens = new ArrayList<>();
        allFieldTokens.addAll(getFieldTokens(address.getCountry(), 0));
        allFieldTokens.addAll(getFieldTokens(address.getState(), 1));
        allFieldTokens.addAll(getFieldTokens(address.getCity(), 2));
        return allFieldTokens;
    }

    private static List<FieldToken> getFieldTokens(String field, Integer fieldId) {
        List<FieldToken> fieldTokens = new ArrayList<>();
        if (Objects.equals(field, "")) {
            return fieldTokens;
        }
        List<String> strTokens = Arrays.stream(field.split("\s+")).distinct().toList();
        for (String strToken : strTokens) {
            fieldTokens.add(new FieldToken(strToken, fieldId));
        }
        return fieldTokens;
    }

    private static void makeAddressLowercase(Address address) {
        address.setCountry(address.getCountry().toLowerCase());
        address.setState(address.getState().toLowerCase());
        address.setCity(address.getCity().toLowerCase());
    }

    private static String removeBadCharacters(String addressStr) {
        for (var badCharacterRange : badCharactersRange) {
            for (int badCharacterCode = badCharacterRange.getLeft(); badCharacterCode <= badCharacterRange.getRight(); ++badCharacterCode) {
                if (badCharacterCode == 32 || badCharacterCode == 45) {
                    // if space or '-' was met
                    continue;
                }
                char badCharacter = (char) badCharacterCode;
                addressStr = addressStr.replace(Character.toString(badCharacter), "");
            }
        }
        return addressStr;
    }
}
