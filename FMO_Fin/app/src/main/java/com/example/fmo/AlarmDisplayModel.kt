package com.example.fmo

data class AlarmDisplayModel(
    val hour: Int,
    val minute: Int,
    var onOff: Boolean
){
    val timeText: String
        get() {
            val h ="%02d".format(if (hour < 12) hour else if (hour==12) hour else hour - 12)
            val m ="%02d".format(minute)

            return "$h:$m"
        }

    val ampmText: String
        get() {
            return if(hour < 12) "AM" else "PM"
        }

    val onOffText: String
        get() {
            return if (onOff) "ON" else "OFF"
        }

    fun makeDataForDB(): String {
        return "$hour:$minute"
    }
}

data class AlarmDisplayModel2(
    val hour: Int,
    val minute: Int,
    var onOff: Boolean
){
    val timeText: String
        get() {
            val h ="%02d".format(if (hour < 12) hour else if (hour==12) hour else hour - 12)
            val m ="%02d".format(minute)

            return "$h:$m"
        }

    val ampmText: String
        get() {
            return if(hour < 12) "AM" else "PM"
        }

    val onOffText: String
        get() {
            return if (onOff) "ON" else "OFF"
        }

    fun makeDataForDB(): String {
        return "$hour:$minute"
    }
}