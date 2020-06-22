package com.vahitkeskin.countryandlanguage.service;

import com.vahitkeskin.countryandlanguage.model.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryServices {

    //https://raw.githubusercontent.com/vire/country.json/master/src/country-languages.json

    @GET("vire/country.json/master/src/country-languages.json")
    Call<List<CountryModel>> getMyData();

}
