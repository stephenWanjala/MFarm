package com.wantech.mfarm.core.domain.model

import java.time.LocalDateTime

data class Evaluation(
    val farmer: String,
    val time: LocalDateTime,
    val quantity: Double,
    val status: String,
    val grossBilling: Double,

    )
