package com.ourclassbank.coredomain.service.creditscore

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreditScoreServiceTest() : DescribeSpec({
    describe("신용평가 모델이 기록되고 조회 됩니다.") {
        context("회원id user001 의 신용평가 점수에 변동을 주면") {
            context("+2점") {
                it("changePoint == 2, score == 2") {

                }
            }

            context("+6점") {
                it("changePoint == 6, score == 8") {

                }
            }

            context("+4점") {
                it("changePoint == 4, score == 12") {

                }
            }

            context("-3점") {
                it("changePoint == -3, score == 9") {

                }
            }

            context("-5점") {
                it("changePoint == -5, score == 4") {

                }
            }
        }
    }
})
