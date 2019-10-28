package com.company.adminapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {


//    level_up_id int(11) not null auto_increment primary key,
//    customer_id int(11) not null,
//    points int(11) not null,
//    member_date date not null

    private Integer levelUpId;
    private Integer customerId;
    private Integer points;
    @JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonSerialize(using=LocalDateSerializer.class)
    private LocalDate memberDate;

    public LevelUpViewModel() {
    }

    public LevelUpViewModel(Integer levelUpId, Integer customerId, Integer points, LocalDate memberDate) {
        this.levelUpId = levelUpId;
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public LevelUpViewModel(Integer customerId, Integer points, LocalDate memberDate) {
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public Integer getLevelUpId() {
        return levelUpId;
    }

    public void setLevelUpId(Integer levelUpId) {
        this.levelUpId = levelUpId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDate getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(LocalDate memberDate) {
        this.memberDate = memberDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelUpViewModel that = (LevelUpViewModel) o;
        return getCustomerId().equals(that.getCustomerId()) &&
                getPoints().equals(that.getPoints()) &&
                getMemberDate().equals(that.getMemberDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getPoints(), getMemberDate());
    }
}
