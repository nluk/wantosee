package pl.nluk.wantosee.connectivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static CountriesInterface countries;


    private final static String COUNTRIES_ENDPOINT = "https://restcountries.eu/rest/v2/";
    private final static String GOOGLE_PLACES_ENDPOINT = "https://maps.googleapis.com/maps/api/place/";

    private Rest() {
    }

    public static CountriesInterface getCountriesRest() {
        return countries;
    }

    public static void init(CommonConnectivity connectivity) {


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(COUNTRIES_ENDPOINT)
                .client(connectivity.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(connectivity.getGson()))
                .build();

        countries = retrofit.create(CountriesInterface.class);


    }


}
