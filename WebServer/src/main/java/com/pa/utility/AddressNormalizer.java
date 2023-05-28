package com.pa.utility;

import com.pa.entity.Address;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddressNormalizer {
    private static final List<Pair<Integer, Integer>> badCharactersRange = Arrays.asList(Pair.of(0, 64), Pair.of(91, 95), Pair.of(123, 190));
    private static final int WORD_LIMIT = 3;

    public static List<String> getCompoundTokens(List<String> singleTokens) {
        List<String> compoundTokens = new ArrayList<>();
        for (int i = 0; i < singleTokens.size(); ++i) {
            StringBuilder currentToken = new StringBuilder();
            for (int j = i; j < Math.min(i + WORD_LIMIT, singleTokens.size()); ++j) {
                if (j > i) {
                    currentToken.append(" ");
                }
                currentToken.append(singleTokens.get(j));
                compoundTokens.add(currentToken.toString());
            }
        }
        return compoundTokens;
    }

    public static List<String> normalizeAddress(Address address) {
        transformNullToEmptyString(address);
        makeAddressLowercase(address);

        String addressStr = transformAddressToString(address);
        addressStr = removeBadCharacters(addressStr);
        addressStr = removeAdditionalSpaces(addressStr);
        return Arrays.stream(addressStr.split(" ")).distinct().toList();
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

    private static String transformAddressToString(Address address) {
        return address.getCountry() + " " + address.getState() + " " + address.getCity();
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

    private static String removeAdditionalSpaces(String addressStr) {
        return addressStr.replaceAll("\\s+", " ");
    }
}
