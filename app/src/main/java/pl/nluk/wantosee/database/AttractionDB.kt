package pl.nluk.wantosee.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel
import pl.nluk.wantosee.models.Country

@Table(database = AppDatabase::class,name = CountryDB.NAME)
class AttractionDB : BaseModel {
    companion object {
        const val NAME = "COUNTRIES"
        fun getCountries() : List<Country> {
            return SQLite.select()
                .from(AttractionDB::class.java)
                .queryList()
                .map { it.toDomain() }
        }


        fun insertCountry(country: Country) {
            AttractionDB(country).save()
        }

        fun getCountryById(id: Long): Country?{
            return SQLite.select()
                .from(AttractionDB::class.java)
                .where(CountryDB_Table.id.`is`(id))
                .querySingle()?.toDomain()

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
        run {
            id = country.id
            name = country.name
        }
    }
    fun toDomain(): Country =
        Country(
            id,
            name
        )


}


