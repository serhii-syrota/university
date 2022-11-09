package com.example.project_2.data.mapsRoute

import com.google.gson.annotations.SerializedName

data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        var geocoderStatus: String?,
        @SerializedName("place_id")
        var placeId: String?,
        @SerializedName("types")
        var types: List<String?>?
)