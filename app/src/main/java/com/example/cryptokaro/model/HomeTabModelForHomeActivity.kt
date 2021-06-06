package com.example.cryptokaro.model

class HomeTabModelForHomeActivity () {

    private var id : String = ""
    private var img : String = ""
    private var sno : Int = 0
    private var text : String = ""
    private var name : String = ""

    constructor(id : String, img : String, sno : Int, text : String, name : String) : this() {
        this.id = id
        this.img = img
        this.sno = sno
        this.text = text
        this.name = name
    }

    fun getId() : String {
        return id
    }

    fun getImg() : String {
        return img
    }

    fun getSno() : Int {
        return sno
    }

    fun getText() : String {
        return text
    }

    fun getName() : String {
        return name
    }

}