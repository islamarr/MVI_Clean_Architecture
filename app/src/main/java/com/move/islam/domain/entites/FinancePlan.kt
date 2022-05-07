package com.move.islam.domain.entites


data class FinancePlan(
    val budgetStatus: String, // DEFAULT
    val disclaimer: String, // Example credit of one of our mobile.de financing partners: 2/3 of all borrowers receive a representative rate of 2,39% APR, fixed rate of 2,36% p.a., total amount repayable of €20,123.65, monthly repayments of €335.39. For a case-specific credit calculation additional information is required.
    val downPayment: Int, // 0
    val fallback: Boolean, // false
    val interestRateEffective: Double, // 2.49
    val loanDuration: Int, // 60
    val localized: LocalizedX,
    val monthlyRate: Double, // 335.9783812373012
    val netAmount: Int, // 18950
    val showInGallery: Boolean, // false
    val type: String, // AO
    val url: String // https://www.mobile.de/finanzierung/route/outlink/1?adId=332199935&loanDuration=60&downPayment=0
)