package com.pa.utility;

import com.pa.entity.Address;
import com.pa.entity.FieldToken;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddressNormalizer {
    private static final List<Pair<Integer, Integer>> badCharactersRange = Arrays.asList(Pair.of(0, 64), Pair.of(91, 95), Pair.of(123, 190));
    private static final int WORD_LIMIT = 3;

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

    public static List<FieldToken> normalizeAddress(Address address) {
        transformNullToEmptyString(address);
        makeAddressLowercase(address);

        List<FieldToken> allFieldTokens = transformAddressToString(address);
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

    private static List<FieldToken> transformAddressToString(Address address) {
        List<FieldToken> allFieldTokens = new ArrayList<>();
        allFieldTokens.addAll(getFieldTokens(address.getCountry(), 0));
        allFieldTokens.addAll(getFieldTokens(address.getState(), 1));
        allFieldTokens.addAll(getFieldTokens(address.getCity(), 2));
        return allFieldTokens;
    }

    private static List<FieldToken> getFieldTokens(String field, Integer fieldId) {
        List<FieldToken> fieldTokens = new ArrayList<>();
        if(Objects.equals(field, "")) {
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
                if (badCharacterCode == 32) {
                    // if space was met
                    continue;
                }
                char badCharacter = (char) badCharacterCode;
                addressStr = addressStr.replace(Character.toString(badCharacter), "");
            }
        }
        return addressStr;
    }
}
