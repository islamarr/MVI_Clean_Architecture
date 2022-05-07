package com.move.islam.domain.entites


data class FinanceBudget(
    val budgetStatus: String, // DEFAULT
    val downPayment: Int, // 0
    val loanDuration: Int, // 60
    val localized: Localized,
    val netIncome: Int // 2000
)