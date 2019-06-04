package pl.nluk.wantosee.models

class AttractionDetailsResponse {
    var formattedAddress: String = ""
    var name: String = ""
    var rating: Double = 0.0
    var photoReferences: MutableList<String> = ArrayList()
    var reviews: MutableList<Review> = ArrayList()
}