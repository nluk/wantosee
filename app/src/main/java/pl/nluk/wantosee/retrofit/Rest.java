package pl.nluk.wantosee.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static CountriesInterface countries;
    private static OkHttpClient okHttpClient;
    private static Gson gson;

    private final static String COUNTRIES_ENDPOINT = "https://restcountries.eu/rest/v2/";
    private final static String GOOGLE_PLACES_ENDPOINT = "";

    private Rest(){}

    public static CountriesInterface getCountriesRest() { return countries; }

    public static void init(){
        gson = new GsonBuilder().create();
        okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(COUNTRIES_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        countries = retrofit.create(CountriesInterface.class);



    }


}
