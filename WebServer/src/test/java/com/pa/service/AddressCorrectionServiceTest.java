package com.pa.service;

import com.google.common.collect.Multimap;
import com.pa.entity.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AddressCorrectionServiceTest {
    @Autowired
    private AddressCorrectionService addressCorrectionService;

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
    void givenZambiaLusakaLusakaAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Zambia", "Lusaka Province", "Lusaka");
        Address wrongAddress = new Address(correctAddress.getState(), correctAddress.getCountry(), correctAddress.getCity());
        Assertions.assertEquals(correctAddress, addressCorrectionService.correctAddress(wrongAddress));
    }

    @Test
    void givenBurkinaFasoOuagadougouOuagadougouAddress_whenSwappingCountryAndStateField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Burkina Faso", "Centre", "Ouagadougou");
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
//======================================================================================================================
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
        Address correctAddress = new Address("United States", "New York", "Manhattan");
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
    void givenUzbekistanWithAlternateNameTashkentTashkentAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Uzbekistan", "Jizzakh Region", "Toshkent");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "O'zbekiston Respublikasi");
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
    void givenKazakhstanWithAlternateNameAstanaAstanaAddress_whenSwappingCountryAndCityField_thenCorrectTheAddress() {
        Address correctAddress = new Address("Republic of Kazakhstan", "Astana", "Astana");
        Address wrongAddress = new Address(correctAddress.getCity(), correctAddress.getState(), "Қазақстан Республикасы");
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
}