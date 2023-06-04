package com.pa.service;

import com.pa.entity.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AddressCorrectionServiceTest {
    @Autowired
    private AddressCorrectionService addressCorrectionService;

    // ======================================================================================================================
    // 10 tests with cross-fields without any alternate names
    @Test
    void givenRomaniaIasiIasiAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Iasi", "Iasi");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaChisinauChisinauAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Chisinau");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUSAFloridaMiamiAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("United States", "Florida", "Miami");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), correctAddress.getCountry());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRussiaMoskvaMoscowAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Russian Federation", "Moskva", "Moscow");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getCountry(), correctAddress.getState());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUkraineKievKievAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Ukraine", "Misto Kyiv", "Kyiv");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUKLiverpoolLiverpoolAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("United Kingdom of Great Britain and Northern Ireland", "England", "Liverpool");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaNisporeniGrozestiAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Nisporeni", "Grozesti");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenItalyPortofinoPortofinoAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Italian Republic", "Liguria", "Portofino");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenNigeriaLagosLagosAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federal Republic of Nigeria", "Lagos State", "Lagos");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenNorwayOsloMarkaAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Kingdom of Norway", "Oslo County", "Marka");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 15 tests with alternate names on one field + cross-fields
    @Test
    void givenRussiaWithAlternateNameSanktPeterburgSerovoAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Russian Federation", "Sankt-Peterburg", "Serovo");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Российская Федерациz");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRomaniaWithAlternateNameBucurestiBucurestiAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Bucuresti", "Bucuresti");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "România");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaWithAlternateNameChisinauChisinauAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Chisinau");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Republica Moldova");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenChinaWithAlternateNameBeijingBeijingAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("People's Republic of China", "Beijing Shi", "Beijing");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "中华人民共和国");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUSAWithAlternateNameNewYorkManhattanAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("United States", "New York", "Essex County");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "United States of America");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenIrelandWithAlternateNameDublinDublinAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Ireland", "Leinster", "Dublin");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Éire");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenBelarusWithAlternateNameMinskMinskAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Belarus", "Minsk Oblast", "Minski Rayon");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Беларусь");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSwedenWithAlternateNameStockholmStockholmAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Kingdom of Sweden", "Stockholm County", "Stockholm");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Sverige");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMongoliaWithAlternateNameUlaanbaatarUlaanbaatarAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Mongolia", "Ulaanbaatar Hot", "Ulan Bator");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Монгол улс");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenBrazilWithAlternateNameBrasiliaBrasiliaAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federative Republic of Brazil", "Federal District", "Brasilia");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Brasil");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMexicoWithAlternateNameMexicoCityMexicoCityAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Mexico", "Ciudad de Mexico", "Mexico City");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "México");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSwitzerlandWithAlternateNameBernBernAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Switzerland", "Canton de Berne", "Bern");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Schweiz");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenGermanyWithAlternateNameBerlinBerlinAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federal Republic of Germany", "Land Berlin", "Berlin");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Deutschland");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRomaniaJudetulBacauTraian_whenSwappingCityAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Bacau", "Traian");
        Address wrongAddress = new Address(correctAddress.getCountry(), correctAddress.getCity(), "Judetul Bacau");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaMunicipiulChisinauWithAlternateNameAddressSectorulCiocana_whenSwappingCityAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Ciocana");
        Address wrongAddress = new Address(correctAddress.getCountry(), correctAddress.getCity(), "Gorod Kishinev");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 15 tests with alternate names on all three fields + cross-fields
    @Test
    void givenRussiaWithAlternateNameSanktPeterburgWithAlternateNameSerovoAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Russian Federation", "Sankt-Peterburg", "Serovo");
        Address wrongAddress = new Address("Питер", correctAddress.getCity(), "Российская Федерация");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRomaniaWithAlternateNameBucurestiWithAlternateNameBucurestiAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Bucuresti", "Bucuresti");
        Address wrongAddress = new Address("Municipiul Bucuresti", correctAddress.getCity(), "România");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaWithAlternateNameChisinauChisinauWithAlternateNameAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Chisinau");
        Address wrongAddress = new Address("Gorod Kishinev", correctAddress.getCity(), "Republica Moldova");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenChinaWithAlternateNameBeijingWithAlternateNameBeijingAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("People's Republic of China", "Beijing Shi", "Beijing");
        Address wrongAddress = new Address("北京市", correctAddress.getCity(), "中华人民共和国");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUSAWithAlternateNameNewYorkWithAlternateNameManhattanAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("United States", "New York", "Essex County");
        Address wrongAddress = new Address("Njujork", correctAddress.getCity(), "United States of America");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenIrelandWithAlternateNameLeinsterDublinWithAlternateNameAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Ireland", "Leinster", "Dublin");
        Address wrongAddress = new Address(correctAddress.getState(), "DUB", "Éire");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenBelarusWithAlternateNameMinskWithAlternateNameMinskAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Belarus", "Minsk Oblast", "Minski Rayon");
        Address wrongAddress = new Address("Мінская Вобласць", correctAddress.getCity(), "Беларусь");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSwedenWithAlternateNameStockholmStockholmWithAlternateNameAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Kingdom of Sweden", "Stockholm County", "Stockholm");
        Address wrongAddress = new Address(correctAddress.getState(), "Estocolm", "Sverige");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMongoliaWithAlternateNameUlaanbaatarUlaanbaatarWithAlternateNameAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Mongolia", "Ulaanbaatar Hot", "Ulan Bator");
        Address wrongAddress = new Address(correctAddress.getState(), "Oulan Mpator", "Монгол улс");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenBrazilWithAlternateNameBrasiliaBrasiliaWithAlternateNameAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federative Republic of Brazil", "Federal District", "Brasilia");
        Address wrongAddress = new Address(correctAddress.getState(), "Бразилия", "Brasil");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMexicoWithAlternateNameMexicoWithAlternateNameMexicoCityAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Mexico", "Ciudad de Mexico", "Mexico City");
        Address wrongAddress = new Address("CMX", correctAddress.getCity(), "México");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSwitzerlandWithAlternateNameBernWithAlternateNameBernAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Switzerland", "Canton de Berne", "Bern");
        Address wrongAddress = new Address("BE", correctAddress.getCity(), "Schweiz");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenGermanyWithAlternateNameBerlinWithAlternateNameBerlinAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federal Republic of Germany", "Land Berlin", "Berlin");
        Address wrongAddress = new Address("Berlim", correctAddress.getCity(), "Deutschland");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRomaniaWithAlternateNameJudetulBacauWithAlternateNameTraianAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Bacau", "Traian");
        Address wrongAddress = new Address("Judetul Bacau", correctAddress.getCity(), "România");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaWithAlternateNameMunicipiulChisinauWithAlternateNameSectorulCiocanaAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Ciocana");
        Address wrongAddress = new Address("Gorod Kishinev", correctAddress.getCity(), "Republica Moldova");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 15 tests with alternate names on all three fields + cross-fields
    @Test
    void givenFranceAzurNiceAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of France", "Provence-Alpes-Cote d'Azur", "Nice");
        Address wrongAddress = new Address("Provence-Alpes-Cote d'Azur", "Nizza", "FR");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenUSADallasSugarGroveAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("United States", "Pennsylvania", "Township of Sugar Grove");
        Address wrongAddress = new Address("Commonwealth of Pennsylvania", "Sugar Grove Township", "U.S.A");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaMunicipiulChisinauSectorulCiocanaAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Ciocana");
        Address wrongAddress = new Address("Gorod Kishinev", "Pretura Ciocana", "Republica Moldova");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRussiatSanktPeterburgSerovoAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Russian Federation", "Sankt-Peterburg", "Serovo");
        Address wrongAddress = new Address("Серово", "Питер", "Российская Федерация");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenChinaBeijingBeijingAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("People's Republic of China", "Beijing Shi", "Beijing");
        Address wrongAddress = new Address("北京市", "北京市", "中华人民共和国");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenJapanTokyoTokyoAddressAllWithAlternateNames_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Japan", "Tokyo Prefecture", "Tokyo");
        Address wrongAddress = new Address("とうきょうと", "டோக்கியோ", "ޖަޕާނު");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaNisporeniGrozestiAddressAllWithAlternateNames_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Nisporeni", "Grozesti");
        Address wrongAddress = new Address("Arrondissement Nisporeni", "Maldova", "Грозешты");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenMoldovaMunicipiulChisinauRascaniAddressAllWithAlternateNames_whenSwappingStateAndCity_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Moldova", "Municipiul Chisinau", "Riscani");
        Address wrongAddress = new Address("A' Mholdobha", "Ryshkanovka", "Кишинёв");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenArgentinaCordobaCordobaAddressAllWithAlternateNames_whenSwappingStateAndCity_thenCorrectTheAddress() {
        Address correctAddress = new Address("Argentine Republic", "Cordoba Province", "Departamento de Marcos Juarez");
        Address wrongAddress = new Address("A Arxentina", "Marcos Juárez", "CD");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenRomaniaClujClujNapocaAddressAllWithAlternateNames_whenSwappingStateAndCity_thenCorrectTheAddress() {
        Address correctAddress = new Address("Romania", "Cluj", "Cluj-Napoca");
        Address wrongAddress = new Address("an Rómáin", "CLJ", "Klausenburg");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
        // 11
    void givenCanadaOntarioTorontoAddressAllWithAlternateNames_whenSwappingStateAndCity_thenCorrectTheAddress() {
        Address correctAddress = new Address("Canada", "Ontario", "Ottawa");
        Address wrongAddress = new Address("Ca-na-da", "Ottaba", "Canada West");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSwitzerlandBernBernAllWithAlternateNamesAddress_whenSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Switzerland", "Canton de Berne", "Bern");
        Address wrongAddress = new Address("BE", "Bundesstadt", "Schweiz");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenPortugalMadeiraSantanaAllWithAlternateNamesAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Portuguese Republic", "Madeira", "Santana");
        Address wrongAddress = new Address("Autonomous Region of Madeira", "Portugal", "Santana Municipality");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenPolandWojewodztwoLodzkieCzerniwckieAllWithAlternateNames_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Poland", "Województwo Łódzkie", "Czerniewice");
        Address wrongAddress = new Address("Черњевице", "Lodz", "Lengyelorszag");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenSloveniaScavniciJurijAllWithAlternateNames_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Slovenia", "Obcina Sveti Jurij ob Scavnici", "Sveti Jurij");
        Address wrongAddress = new Address("Obcina Sveti Jurij", "Socijalisticka Republika Slovenija", "Vizlendva");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 10 tests with one field deletion (without cross-fields or alternate names)
    @Test
    void givenUSAMiamiAddressWithDeletedStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("United States", "Florida", "Miami");
        Address wrongAddress = new Address(null, correctAddress.getState(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }


    // ======================================================================================================================
    // 15 tests with one field deletion (mainly country) + cross-field
    @Test
    void givenSwitzerlandCantonDeBerneBernAddress_whenDeletingCountryFieldAndSwappingAllFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Switzerland", "Canton de Berne", "Bern");
        Address wrongAddress = new Address(correctAddress.getCity(), null, correctAddress.getState());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 10 tests with one field deletion (mainly country) + alternate names + cross-fields on all fields
    @Test
    void givenBrazilFederalDistrictBrasiliaWithAlternateNames_whenDeletedCountryFieldAndSwappingRemainingFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federative Republic of Brazil", "Federal District", "Brasilia");
        Address wrongAddress = new Address("Distrig Kevreadel", null, "Бразилия");
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    // ======================================================================================================================
    // 10 tests with moving one field + alternate names + cross-fields on all fields (compound fields may exist)
    @Test
    void givenGermanyWithAlternateBerlinWithAlternateBerlinAddress_whenMovingCountryFieldToStateAndSwappingRemainingFields_thenCorrectTheAddress() {
        Address correctAddress = new Address("Federal Republic of Germany", "Land Berlin", "Berlin");
        Address wrongAddress = new Address("Berlim Deutschland", correctAddress.getCity(), null);
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }
}