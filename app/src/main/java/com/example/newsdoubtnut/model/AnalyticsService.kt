package com.example.newsdoubtnut.model

interface AnalyticsService {

    fun recordEvent(eventName: String, attributes: Map<String,String>? = null, metrics: Map<String,Double>? = null)
}