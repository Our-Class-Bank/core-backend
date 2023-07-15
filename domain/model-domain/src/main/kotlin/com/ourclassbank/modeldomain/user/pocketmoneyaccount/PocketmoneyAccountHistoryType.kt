package com.ourclassbank.modeldomain.user.pocketmoneyaccount

enum class PocketmoneyAccountHistoryType(
    val description: String,
    val toAmountWithSign: (Long) -> Long
) {
    INCOME_SALARY("수입_월급", { amount -> amount }),
    INCOME_PRIZE_MONEY("수입_상금", { amount -> amount }),
    INCOME_ETC("수입_기타", { amount -> amount }),

    EXPENSE_MARKET("지출_마켓", { amount -> -amount }),
    EXPENSE_FINE("지출_벌금", { amount -> -amount }),
    EXPENSE_ETC("지출_기타", { amount -> -amount }),
}
