package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.usecase.PocketmoneyQueryUsecase
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest

// todo : 테스트 구현
@Ignored
@SpringBootTest
class PocketmoneyAccountServiceTest(
    private val sut: PocketmoneyQueryUsecase,
) : DescribeSpec({
    describe("용돈계좌 기능 - 입금, 출금, 기록조회 by username=abc123") {
        context("기록조회") {
            it("balance=0L") {

            }
        }

        context("입금 - type=수입_월급, amount=10000L, description=1월 월급") {
            it("history count=1") {

            }

            it("last history - balance=10000L, type, amount, description 일치") {

            }
        }

        context("입금 - type=수입_상금, amount=8000L, description=교내 미술대회 상금") {
            it("history count=2") {

            }

            it("last history - balance=18000L, type, amount, description 일치") {

            }
        }

        context("출금 - type=지출_마켓, amount=50000L, description=출금한도 초과") {
            it("DomainException - 출금한도 초과") {

            }
        }

        context("출금 - type=지출_벌금, amount=3000L, description=아이스크림") {
            it("history count=3") {

            }

            it("last history - balance=15000L, type, amount, description 일치") {

            }
        }

        context("출금 - type=지출_기타, amount=9000L, description=미화용품 구매") {
            it("history count=4") {

            }

            it("last history - balance=6000L, type, amount, description 일치") {

            }
        }
    }
})
