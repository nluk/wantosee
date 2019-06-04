package pl.nluk.wantosee.connectivity.rest;

import pl.nluk.wantosee.connectivity.CommonConnectivity;
import pl.nluk.wantosee.connectivity.rest.interfaces.AttractionsInterface;
import pl.nluk.wantosee.connectivity.rest.interfaces.CountriesInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static CountriesInterface countries;
    private static AttractionsInterface attractions;


    private final static String COUNTRIES_ENDPOINT = "https://restcountries.eu/rest/v2/";
    private final static String GOOGLE_PLACES_ENDPOINT = "https://maps.googleapis.com/maps/api/place/";

    private Rest() {
    }

    public static CountriesInterface getCountriesRest() {
        return countries;
    }


    public static AttractionsInterface getAttractionsRest() {
        return attractions;
    }

    public static void init(CommonConnectivity connectivity) {


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(COUNTRIES_ENDPOINT)
                .client(connectivity.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(connectivity.getGson()))
                .build();

        countries = retrofit.create(CountriesInterface.class);


        retrofit = new Retrofit
                .Builder()
                .baseUrl(GOOGLE_PLACES_ENDPOINT)
                .client(connectivity.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(connectivity.getGson()))
                .build();

        attractions = retrofit.create(AttractionsInterface.class);


    }


}
