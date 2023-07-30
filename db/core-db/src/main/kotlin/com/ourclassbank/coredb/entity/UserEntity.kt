package com.ourclassbank.coredb.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.Comment

@Table(
    name = "user",
    uniqueConstraints = [
        UniqueConstraint(
            name = "unique_attendance_number",
            columnNames = ["schoolName", "grade", "classNumber", "attendanceNumber"]
        )
    ]
)
@Entity
class UserEntity(
    @Comment("로그인ID")
    @Column(unique = true)
    val loginId: String,

    @Comment("비밀번호")
    var password: String,
    @Comment("이름")
    val name: String,
    @Comment("용돈계좌번호")
    val pocketMoneyAccountNo: String,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "user_id")
    val roles: List<UserRoleEntity>,

    @Embedded
    val userClass: UserClassEntity,
) : BaseEntity() {
    fun updatePassword(password: String) {
        this.password = password
    }
}
