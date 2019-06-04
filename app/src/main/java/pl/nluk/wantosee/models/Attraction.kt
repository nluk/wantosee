package pl.nluk.wantosee.models

class Attraction {
    constructor(id: Long, googleId: String, countryId: Long, name: String, imageReference: String) {
        this.id = id
        this.googleId = googleId
        this.countryId = countryId
        this.name = name
        this.imageReference = imageReference
    }

    constructor()

    var id : Long = 0L
    var googleId: String = ""
    var countryId : Long = 0L
    var name : String = ""
    var imageReference: String = ""
}