package com.elkrnshawy.restaurant_qr_user.models

data class Paginate(
    var total: Int,
    var count: Int,
    var per_page: Int,
    var next_page_url: String,
    var prev_page_url: String,
    var current_page: Int,
    var total_pages: Int
)
{

    constructor():this(0,0,0,"","",1,0){

    }

    fun increasePage() {
        this.current_page++
    }
}