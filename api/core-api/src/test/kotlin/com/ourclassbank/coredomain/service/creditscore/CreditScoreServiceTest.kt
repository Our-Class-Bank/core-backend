package com.ourclassbank.coredomain.service.creditscore

import com.ourclassbank.modeldomain.user.creditscore.CreditScoreUpdateVo
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreditScoreServiceTest(
    private val creditScoreService: CreditScoreService,
    private val creditScoreReadService: CreditScoreReadService
) : DescribeSpec({
    describe("신용평가 모델이 기록되고 조회 됩니다.") {
        val userId1 = "user001"

        context("회원id user001 의 신용평가 점수에 변동을 주면") {
            context("+2점") {
                CreditScoreUpdateVo(userId1, 2, "+2점").run {
                    creditScoreService.update(this)
                }

                it("changePoint == 2, score == 2") {
                    creditScoreReadService.findLastHistoryByUserLoginId(userId1).run {
                        changePoint shouldBe 2
                        score shouldBe 2
                    }
                }
            }

            context("+6점") {
                CreditScoreUpdateVo(userId1, 6, "+6점").run {
                    creditScoreService.update(this)
                }

                it("changePoint == 6, score == 8") {
                    creditScoreReadService.findLastHistoryByUserLoginId(userId1).run {
                        changePoint shouldBe 6
                        score shouldBe 8
                    }
                }
            }

            context("+4점") {
                CreditScoreUpdateVo(userId1, 4, "+4점").run {
                    creditScoreService.update(this)
                }

                it("changePoint == 4, score == 12") {
                    creditScoreReadService.findLastHistoryByUserLoginId(userId1).run {
                        changePoint shouldBe 4
                        score shouldBe 12
                    }
                }
            }

            context("-3점") {
                CreditScoreUpdateVo(userId1, -3, "-3점").run {
                    creditScoreService.update(this)
                }

                it("changePoint == -3, score == 9") {
                    creditScoreReadService.findLastHistoryByUserLoginId(userId1).run {
                        changePoint shouldBe -3
                        score shouldBe 9
                    }
                }
            }

            context("-5점") {
                CreditScoreUpdateVo(userId1, -5, "-5점").run {
                    creditScoreService.update(this)
                }

                it("changePoint == -5, score == 4") {
                    creditScoreReadService.findLastHistoryByUserLoginId(userId1).run {
                        changePoint shouldBe -5
                        score shouldBe 4
                    }
                }
            }
        }
    }
})
