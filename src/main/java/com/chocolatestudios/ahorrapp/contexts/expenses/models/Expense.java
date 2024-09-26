package com.chocolatestudios.ahorrapp.contexts.expenses.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "expenses")
public class Expense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description must be at most 100 characters")
    @Column
    private String description;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    @Column
    private BigDecimal amount;

    @NotNull(message = "Date and time cannot be null")
    @PastOrPresent(message = "Date and time must be in the past or present")
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull(message = "Profile ID cannot be null")
    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private Profile profile;

    public Long getId() {
        return id;
    }

    public Expense setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Expense setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Expense setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Expense setDateTime(LocalDateTime date) {
        this.dateTime = date;
        return this;
    }

    public Long getProfileId() {
        return profileId;
    }

    public Expense setProfileId(Long profileId) {
        this.profileId = profileId;
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public Expense setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
}
