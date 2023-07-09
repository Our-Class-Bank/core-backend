package com.ourclassbank.modeldomain.user.pocketmoneyaccount

enum class PocketmoneyAccountHistoryType(
    val description: String
) {
    INCOME_SALARY("수입_월급"),
    INCOME_PRIZE_MONEY("수입_상금"),
    INCOME_ETC("수입_기타"),

    EXPENSE_MARKET("지출_마켓"),
    EXPENSE_FINE("지출_벌금"),
    EXPENSE_ETC("지출_기타"),
}
