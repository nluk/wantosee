package pl.nluk.wantosee.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel
import pl.nluk.wantosee.models.Country

@Table(database = AppDatabase::class,name = CountryDB.NAME)
class CountryDB : BaseModel {
    companion object {
        const val NAME = "COUNTRIES"
        fun getCountries() : List<Country> {
            return SQLite.select()
                .from(CountryDB::class.java)
                .queryList()
                .map { it.toDomain() }
        }


        fun insertCountry(country: Country) {
            CountryDB(country).save()
        }
    }

    @PrimaryKey(autoincrement = true)
    @Column
    var id: Long = 0L

    @Column
    var name: String = ""

    constructor() {
        // Mandatory for DBFlow
    }

    constructor(country: Country) {
        country.let {
            id = it.id
            name = it.name
        }
    }
    fun toDomain(): Country =
        Country(
            id,
            name
        )


}


