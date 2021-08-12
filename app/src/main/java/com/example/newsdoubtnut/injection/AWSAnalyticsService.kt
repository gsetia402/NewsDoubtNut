package com.example.newsdoubtnut.injection

import android.content.Context
import com.amazonaws.mobile.auth.core.IdentityManager
import com.amazonaws.mobile.config.AWSConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import com.example.newsdoubtnut.model.AnalyticsService

class AWSAnalyticsService (context: Context): AnalyticsService {
    private var manager: PinpointManager? = null

    init {
        val awsConfiguration = AWSConfiguration(context)
        val identityManager = IdentityManager(context, awsConfiguration)
        val config = PinpointConfiguration(context, identityManager.credentialsProvider, awsConfiguration)
        manager = PinpointManager(config)
        manager?.sessionClient?.startSession()
        manager?.analyticsClient?.submitEvents()
    }

    /**
     * Record a custom event into the analytics stream
     *
     * @param name the custom event name
     * @param [attributes] a list of key-value pairs for recording string attributes
     * @param [metrics] a list of key-value pairs for recording numeric metrics
     */
    override fun recordEvent(name: String, attributes: Map<String, String>?, metrics: Map<String, Double>?) {
        manager?.let {
            val event = it.analyticsClient.createEvent(name)
            for ((k, v) in attributes.orEmpty()) {
                event.addAttribute(k, v)
            }
            for ((k,v) in metrics.orEmpty()) {
                event.addMetric(k, v)
            }
            it.analyticsClient.recordEvent(event)
            it.analyticsClient.submitEvents()
        }
    }
}