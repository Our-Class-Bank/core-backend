package com.ourclassbank.coredomain.service.creditevaluation

import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class CreditEvaluationServiceTest(
    private val creditEvaluationService: CreditEvaluationService,
    private val creditEvaluationReadService: CreditEvaluationReadService
) : DescribeSpec({
    describe("신용평가 모델이 기록되고 조회 됩니다.") {
        val userId1 = "user001"
        val fromAt = LocalDateTime.now().minusHours(1)
        val toAt = LocalDateTime.now().plusHours(1)

        context("신용평가를 합니다. - 회원id == user001") {
            context("+2점") {
                CreditEvaluateVo(userId1, 2, "+2점").run {
                    creditEvaluationService.evaluate(this)
                }

                it("changePoint == 2, score == 2") {
                    creditEvaluationReadService.findLastHistoryByUser(userId1).run {
                        changePoint shouldBe 2
                        score shouldBe 2
                    }
                }

                it("historyCount == 1") {
                    creditEvaluationReadService.findAllHistoryByUser(userId1, fromAt, toAt).run {
                        size shouldBe 1
                    }
                }
            }

            context("+6점") {
                CreditEvaluateVo(userId1, 6, "+6점").run {
                    creditEvaluationService.evaluate(this)
                }

                it("changePoint == 6, score == 8") {
                    creditEvaluationReadService.findLastHistoryByUser(userId1).run {
                        changePoint shouldBe 6
                        score shouldBe 8
                    }
                }

                it("historyCount == 2") {
                    creditEvaluationReadService.findAllHistoryByUser(userId1, fromAt, toAt).run {
                        size shouldBe 2
                    }
                }
            }

            context("+4점") {
                CreditEvaluateVo(userId1, 4, "+4점").run {
                    creditEvaluationService.evaluate(this)
                }

                it("changePoint == 4, score == 12") {
                    creditEvaluationReadService.findLastHistoryByUser(userId1).run {
                        changePoint shouldBe 4
                        score shouldBe 12
                    }
                }

                it("historyCount == 3") {
                    creditEvaluationReadService.findAllHistoryByUser(userId1, fromAt, toAt).run {
                        size shouldBe 3
                    }
                }
            }

            context("-3점") {
                CreditEvaluateVo(userId1, -3, "-3점").run {
                    creditEvaluationService.evaluate(this)
                }

                it("changePoint == -3, score == 9") {
                    creditEvaluationReadService.findLastHistoryByUser(userId1).run {
                        changePoint shouldBe -3
                        score shouldBe 9
                    }
                }

                it("historyCount == 4") {
                    creditEvaluationReadService.findAllHistoryByUser(userId1, fromAt, toAt).run {
                        size shouldBe 4
                    }
                }
            }

            context("-5점") {
                CreditEvaluateVo(userId1, -5, "-5점").run {
                    creditEvaluationService.evaluate(this)
                }

                it("changePoint == -5, score == 4") {
                    creditEvaluationReadService.findLastHistoryByUser(userId1).run {
                        changePoint shouldBe -5
                        score shouldBe 4
                    }
                }

                it("historyCount == 5") {
                    creditEvaluationReadService.findAllHistoryByUser(userId1, fromAt, toAt).run {
                        size shouldBe 5
                    }
                }
            }
        }

        context("신용평가의 점수는 0 에서 100 사이 입니다.") {
            context("신용 평가의 결과가 0보다 작으면 ") {
                val exception = shouldThrow<DomainException> {
                    CreditEvaluateVo("ex001", -1, "-1 점").run {
                        creditEvaluationService.evaluate(this)
                    }
                }

                it("예외가 발생합니다.") {
                    exception.message shouldBe "신용평가 점수는 0 에서 100 사이 입니다."
                }
            }

            context("신용 평가의 결과가 100보다 작으면") {
                val exception = shouldThrow<DomainException> {
                    CreditEvaluateVo("ex002", 101, "101 점").run {
                        creditEvaluationService.evaluate(this)
                    }
                }
                it("예외가 발생합니다.") {
                    exception.message shouldBe "신용평가 점수는 0 에서 100 사이 입니다."
                }
            }
        }
    }
})
