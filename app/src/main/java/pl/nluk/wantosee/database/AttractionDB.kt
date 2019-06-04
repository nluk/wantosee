package pl.nluk.wantosee.database

import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.BaseModel
import pl.nluk.wantosee.models.Attraction

@Table(database = AppDatabase::class, name = AttractionDB.NAME)
class AttractionDB : BaseModel {
    companion object {
        const val NAME = "ATTRACTIONS"
        fun getAttractions(): List<Attraction> {
            return SQLite.select()
                .from(AttractionDB::class.java)
                .queryList()
                .map { it.toDomain() }
        }


        fun insertAttraction(attraction: Attraction) {
            AttractionDB(attraction).save()
        }

        fun getAttractionById(id: Long): Attraction? {
            return SQLite.select()
                .from(AttractionDB::class.java)
                .where(AttractionDB_Table.id.`is`(id))
                .querySingle()?.toDomain()
        }

        fun getAttractionsByCountry(countryId: Long): List<Attraction> {
            return SQLite.select()
                .from(AttractionDB::class.java)
                .where(AttractionDB_Table.countryId_id.`is`(countryId))
                .queryList()
                .map { it.toDomain() }
        }
    }


    @PrimaryKey(autoincrement = true)
    var id: Long = 0L

    @Unique(onUniqueConflict = ConflictAction.IGNORE)
    @Column(name = "google_id")
    var googleId: String = ""

    @ForeignKey(tableClass = CountryDB::class)
    @ForeignKeyReference(columnName = "attraction_country_id", foreignKeyColumnName = "country_id")
    var countryId: Long = 0L

    @Column(name = "attr_name")
    var name: String = ""

    @Column(name = "image_refrerence")
    var imageReference: String = ""

    constructor() {
        // Mandatory for DBFlow
    }

    constructor(attraction: Attraction) {
        run {
            id = attraction.id
            googleId = attraction.googleId
            countryId = attraction.countryId
            name = attraction.name
            imageReference = attraction.imageReference
        }
    }

    fun toDomain(): Attraction =
        Attraction(
            id,
            googleId,
            countryId,
            name,
            imageReference
        )


}


