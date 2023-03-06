package com.example.audioplayer

object Constants {

    const val FORGOT_PASSWORD: String = "FORGOT"
    const val USER_NAME: String = "name"

    var RADIONAMES = mutableListOf<String>(
        "ANIME RADIO",
        "RETRO FM",
        "HEART FM",
        "DOROZHNOE RADIO",
        "COUNTRY RADIO",
        "EUROPA PLUS",
        "RADIO SVOBODA",
        "NASHE RADIO",
        "KPOP RADIO",
        "RADIO ECHO",
        "RADIO DACHA",
        "RADIO SHANSON",
        "CLASSIC ROCK",
        "RADIO MAYAK"
    )

    var RADIOSTATIONS = mutableListOf<String>(
        "https://stream-14.zeno.fm/qpn8mkt8c4duv?zs=y1CDSZ6ASN-Kid1lgFBmvQ", // Аниме радио
        "http://retroserver.streamr.ru:8043/retro128", // Ретро ФМ
        "https://icecast.gtrk22.ru:8443/heartfm", // Харт ФМ, Барнаул 7 сек
        "http://dorognoe.hostingradio.ru:8000/radio", // Дорожное радио, Барнаул
        "http://ice10.outlaw.fm/KIEV2", // Кантри
        "http://ep256.hostingradio.ru:8052/europaplus256.mp3", // Европа Плюс
        "http://n03.radiojar.com/hcrb063nn3quv?rj-ttl=5&rj-tok=AAABhpdGOkoA-v_QbzlZsyJhdA", // Радио Свобода
        "http://nashe1.hostingradio.ru/nasheclassic.mp3", // Наше радио
        "https://antares.dribbcast.com/proxy/kpop?mp=/s", // K Pop
        "https://i1.echofm.online:8443/stream", // Эхо
        "https://nousethisserver717573.vdfm.ru:8000/dacha", // Радио Дача
        "http://chanson.hostingradio.ru:8041/chanson256.mp3", // Радио Шансон
        "https://0n-classicrock.radionetz.de/0n-classicrock.mp3", // Радио Classic Rock
        "http://icecast.vgtrk.cdnvideo.ru/mayakfm_mp3_192kbps", // Маяк
    )
}