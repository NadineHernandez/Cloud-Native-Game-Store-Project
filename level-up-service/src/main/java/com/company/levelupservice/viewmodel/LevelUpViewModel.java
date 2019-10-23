package com.company.levelupservice.viewmodel;

import java.time.LocalDate;
import java.util.Objects;

public class LevelUpViewModel {
    private int levelUpId;
    private Integer customerId;
    private Integer points;
    private LocalDate memberDate;

    public LevelUpViewModel(Integer customerId, Integer points, LocalDate memberDate) {
        this.customerId = customerId;
        this.points = points;
        this.memberDate = memberDate;
    }

    public LevelUpViewModel() {
    }

    public int getLevelUpId() {
        return levelUpId;
    }

    public void setLevelUpId(int levelUpId) {
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
        return levelUpId == that.levelUpId &&
                customerId.equals(that.customerId) &&
                points.equals(that.points) &&
                memberDate.equals(that.memberDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levelUpId, customerId, points, memberDate);
    }

    @Override
    public String toString() {
        return "LevelUpViewModel{" +
                "levelUpId=" + levelUpId +
                ", customerId=" + customerId +
                ", points=" + points +
                ", memberDate=" + memberDate +
                '}';
    }
}
